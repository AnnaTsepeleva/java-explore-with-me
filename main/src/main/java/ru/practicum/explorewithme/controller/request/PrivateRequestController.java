package ru.practicum.explorewithme.controller.request;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.practicum.explorewithme.dtomain.request.ParticipationRequestDto;
import ru.practicum.explorewithme.log.ToLog;
import ru.practicum.explorewithme.mapper.RequestMapper;
import ru.practicum.explorewithme.model.Request;
import ru.practicum.explorewithme.service.RequestService;

import javax.validation.constraints.Positive;
import java.util.Collection;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/users/{userId}/requests")
@RequiredArgsConstructor
@Validated
@ToLog
public class PrivateRequestController {
    private final RequestService requestService;
    private final RequestMapper requestMapper;

    @GetMapping
    public Collection<ParticipationRequestDto> getRequestsToParticipateInOtherEvents(@Positive @PathVariable Long userId) {
        return requestService.getRequestsToParticipateInOtherEvents(userId)
                .stream()
                .map(requestMapper::toParticipationRequestDto)
                .collect(Collectors.toList());
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ParticipationRequestDto saveUserRequest(@Positive @PathVariable Long userId,
                                                   @Positive @RequestParam Long eventId) {
        Request request = requestService.saveUserRequest(userId, eventId);
        return requestMapper.toParticipationRequestDto(request);
    }

    @PatchMapping("/{requestId}/cancel")
    public ParticipationRequestDto cancelOwnEvent(@Positive @PathVariable Long userId,
                                                  @Positive @PathVariable Long requestId) {
        Request request = requestService.cancelOwnEvent(userId, requestId);
        return requestMapper.toParticipationRequestDto(request);
    }
}
