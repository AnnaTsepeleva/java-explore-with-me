package ru.practicum.explorewithme.dtomain.event;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.practicum.explorewithme.dtomain.category.CategoryDto;
import ru.practicum.explorewithme.dtomain.location.LocationDtoCoordinates;
import ru.practicum.explorewithme.dtomain.user.UserShortDto;
import ru.practicum.explorewithme.model.enums.EventStatus;

import java.time.LocalDateTime;

import static ru.practicum.explorewithme.constant.Constants.DATE_TIME_FORMAT;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class EventFullDto {
    private Long id;

    private String annotation;

    private CategoryDto category;

    private Long confirmedRequests;

    @JsonFormat(pattern = DATE_TIME_FORMAT)
    private LocalDateTime createdOn;

    private String description;

    @JsonFormat(pattern = DATE_TIME_FORMAT)
    private LocalDateTime eventDate;

    private UserShortDto initiator;

    private LocationDtoCoordinates location;

    private Boolean paid;

    private Long participantLimit;

    @JsonFormat(pattern = DATE_TIME_FORMAT)
    private LocalDateTime publishedOn;

    private Boolean requestModeration;

    private EventStatus published;

    private String title;

    private Long views;

    private EventStatus state;
}
