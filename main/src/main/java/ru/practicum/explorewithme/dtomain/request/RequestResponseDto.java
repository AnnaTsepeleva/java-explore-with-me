package ru.practicum.explorewithme.dtomain.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.practicum.explorewithme.dtomain.event.EventFullDto;
import ru.practicum.explorewithme.model.enums.EventStatus;

import java.time.LocalDateTime;

import static ru.practicum.explorewithme.constant.Constants.DATE_TIME_FORMAT;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class RequestResponseDto {

    @JsonFormat(pattern = DATE_TIME_FORMAT)
    private LocalDateTime created;

    private EventFullDto eventResponseDto;

    private Long id;

    private Long requester;

    private EventStatus status;
}
