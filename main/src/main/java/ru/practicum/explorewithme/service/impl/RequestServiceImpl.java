package ru.practicum.explorewithme.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.practicum.explorewithme.exceptions.NotAvailableException;
import ru.practicum.explorewithme.exceptions.NotFoundException;
import ru.practicum.explorewithme.exceptions.ValidationException;
import ru.practicum.explorewithme.model.Event;
import ru.practicum.explorewithme.model.Request;
import ru.practicum.explorewithme.model.User;
import ru.practicum.explorewithme.model.enums.EventStatus;
import ru.practicum.explorewithme.model.enums.RequestStatus;
import ru.practicum.explorewithme.repository.EventRepository;
import ru.practicum.explorewithme.repository.RequestRepository;
import ru.practicum.explorewithme.repository.UserRepository;
import ru.practicum.explorewithme.service.RequestService;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Objects;

@Service
@RequiredArgsConstructor
@Transactional
public class RequestServiceImpl implements RequestService {
    private final RequestRepository requestRepository;
    private final UserRepository userRepository;
    private final EventRepository eventRepository;

    @Override
    @Transactional(readOnly = true)
    public Collection<Request> getRequestsToParticipateInOtherEvents(Long userId) {
        userRepository.findById(userId).orElseThrow(() ->
                new NotFoundException(String.format("User %s not found", userId)));

        return requestRepository.findByRequesterId(userId);
    }

    @Override
    public Request saveUserRequest(Long userId, Long eventId) {
        User requester = userRepository.findById(userId).orElseThrow(() ->
                new NotFoundException(String.format("User %s not found", userId)));

        Event event = eventRepository.findById(eventId).orElseThrow(() ->
                new NotFoundException(String.format("Event %s not found", eventId)));

        if (event.getInitiator().getId().equals(requester.getId())) {
            throw new NotAvailableException("Event initiator cannot add a request to participate in their event");
        }
        if (!event.getState().equals(EventStatus.PUBLISHED)) {
            throw new NotAvailableException("Cannot participate in an unpublished event");
        }

        for (Request requests: requestRepository.findByRequesterId(userId)){
            if (Objects.equals(requests.getEvent().getId(), eventId)){
                throw new NotFoundException("Request from you already exists.");
            }
        }

        Long confirmedRequests = requestRepository.countAllByEventIdAndStatus(eventId,
                RequestStatus.CONFIRMED);

        if (event.getParticipantLimit() <= confirmedRequests && event.getParticipantLimit() != 0) {
            throw new NotAvailableException(("Limit of requests for participation has been exceeded"));
        }

        Request request = Request.builder()
                .created(LocalDateTime.now())
                .event(event)
                .requester(requester)
                .status(!event.getRequestModeration() || event.getParticipantLimit() == 0
                        ? RequestStatus.CONFIRMED
                        : RequestStatus.PENDING)
                .build();

        return requestRepository.save(request);
    }

    @Override
    public Request cancelOwnEvent(Long userId, Long requestId) {
        userRepository.findById(userId).orElseThrow(() ->
                new NotFoundException(String.format("User %s not found", userId)));

        Request request = requestRepository.findById(requestId).orElseThrow(() ->
                new NotFoundException(String.format("Request %s not found", requestId)));

        if (!request.getRequester().getId().equals(userId)) {
            throw new ValidationException(
                    String.format("User %s didn't apply for participation %s", userId, requestId));
        }
        request.setStatus(RequestStatus.CANCELED);

        return requestRepository.save(request);
    }
}
