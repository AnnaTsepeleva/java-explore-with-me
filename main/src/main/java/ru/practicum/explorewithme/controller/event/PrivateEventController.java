package ru.practicum.explorewithme.controller.event;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.practicum.explorewithme.dto.event.EventFullDto;
import ru.practicum.explorewithme.dto.event.EventShortDto;
import ru.practicum.explorewithme.dto.event.NewEventDto;
import ru.practicum.explorewithme.dto.request.EventRequestStatusUpdateRequestDto;
import ru.practicum.explorewithme.dto.request.EventRequestStatusUpdateResultDto;
import ru.practicum.explorewithme.dto.request.ParticipationRequestDto;
import ru.practicum.explorewithme.dto.request.UpdateEventUserRequestDto;
import ru.practicum.explorewithme.log.ToLog;
import ru.practicum.explorewithme.service.EventService;
import ru.practicum.explorewithme.util.OffsetBasedPageRequest;

import javax.validation.Valid;
import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;
import java.util.Collection;

import static ru.practicum.explorewithme.constant.Constants.PAGE_DEFAULT_FROM;
import static ru.practicum.explorewithme.constant.Constants.PAGE_DEFAULT_SIZE;

@RestController
@RequestMapping("/users/{userId}/events")
@RequiredArgsConstructor
@Validated
@ToLog
public class PrivateEventController {
    private final EventService eventService;

    @GetMapping
    public Collection<EventShortDto> getEventsAddedByCurrentUser(@Positive @PathVariable Long userId,
                                                                 @RequestParam(defaultValue = PAGE_DEFAULT_FROM) @PositiveOrZero Integer from,
                                                                 @RequestParam(defaultValue = PAGE_DEFAULT_SIZE) @Positive Integer size) {
        Pageable page = new OffsetBasedPageRequest(from, size);
        return eventService.getEventsAddedByCurrentUser(userId, page);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public EventFullDto saveEvent(@Positive @PathVariable Long userId,
                                  @Valid @RequestBody NewEventDto dto) {
        return eventService.saveEvent(userId, dto);
    }

    @GetMapping("/{eventId}")
    public EventFullDto getEventAddedCurrentUser(@Positive @PathVariable Long userId,
                                                 @Positive @PathVariable Long eventId) {
        return eventService.getEventAddedCurrentUser(userId, eventId);
    }

    @PatchMapping("/{eventId}")
    public EventFullDto changeEventAddedCurrentUser(@Positive @PathVariable Long userId,
                                                    @Positive @PathVariable Long eventId,
                                                    @Valid @RequestBody UpdateEventUserRequestDto dto) {
        return eventService.changeEventAddedCurrentUser(userId, eventId, dto);
    }

    @GetMapping("/{eventId}/requests")
    public Collection<ParticipationRequestDto> getRequestsByCurrentUser(@Positive @PathVariable Long userId,
                                                                        @Positive @PathVariable Long eventId) {
        return eventService.getRequestsByCurrentUser(userId, eventId);
    }

    @PatchMapping("/{eventId}/requests")
    public EventRequestStatusUpdateResultDto changeStatusOfRequestsByCurrentUser(@Positive @PathVariable Long userId,
                                                                                 @Positive @PathVariable Long eventId,
                                                                                 @Valid @RequestBody EventRequestStatusUpdateRequestDto dto) {
        return eventService.changeStatusOfRequestsByCurrentUser(userId, eventId, dto);
    }
}
