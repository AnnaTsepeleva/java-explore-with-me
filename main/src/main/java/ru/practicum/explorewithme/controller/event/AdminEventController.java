package ru.practicum.explorewithme.controller.event;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.practicum.explorewithme.dtomain.event.EventFullDto;
import ru.practicum.explorewithme.dtomain.request.UpdateEventAdminRequestDto;
import ru.practicum.explorewithme.log.ToLog;
import ru.practicum.explorewithme.model.enums.EventStatus;
import ru.practicum.explorewithme.service.EventService;
import ru.practicum.explorewithme.util.OffsetBasedPageRequest;

import javax.validation.Valid;
import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;
import java.util.Set;

import static ru.practicum.explorewithme.constant.Constants.*;

@RestController
@RequestMapping("/admin/events")
@RequiredArgsConstructor
@Validated
@ToLog
public class AdminEventController {
    private final EventService eventService;

    @GetMapping
    public Collection<EventFullDto> getEventsByAdmin(@RequestParam(defaultValue = PAGE_DEFAULT_FROM) @PositiveOrZero Integer from,
                                                     @RequestParam(defaultValue = PAGE_DEFAULT_SIZE) @Positive Integer size,
                                                     @RequestParam(required = false) @DateTimeFormat(pattern = DATE_TIME_FORMAT) LocalDateTime rangeStart,
                                                     @RequestParam(required = false) @DateTimeFormat(pattern = DATE_TIME_FORMAT) LocalDateTime rangeEnd,
                                                     @RequestParam(required = false) List<EventStatus> states,
                                                     @RequestParam(required = false) Set<Long> users,
                                                     @RequestParam(required = false) Set<Long> categories) {
        Pageable page = new OffsetBasedPageRequest(from, size);
        return eventService.getEventsByAdmin(users, categories, states, rangeStart, rangeEnd, page);
    }

    @PatchMapping("/{eventId}")
    public EventFullDto updateEventByAdmin(@Positive @PathVariable Long eventId,
                                           @Valid @RequestBody UpdateEventAdminRequestDto dto) {
        return eventService.updateEventByAdmin(eventId, dto);
    }
}
