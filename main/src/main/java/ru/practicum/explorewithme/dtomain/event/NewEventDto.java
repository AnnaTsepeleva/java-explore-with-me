package ru.practicum.explorewithme.dtomain.event;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;
import ru.practicum.explorewithme.dtomain.location.LocationDtoCoordinates;
import ru.practicum.explorewithme.validation.EventDateValidator;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;
import java.time.LocalDateTime;

import static ru.practicum.explorewithme.constant.Constants.DATE_TIME_FORMAT;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class NewEventDto {

    @NotBlank
    @Length(min = 20, max = 2000)
    private String annotation;

    @NotNull
    private Long category;

    @NotBlank
    @Length(min = 20, max = 7000)
    private String description;

    @JsonFormat(pattern = DATE_TIME_FORMAT)
    @NotNull
    @EventDateValidator
    private LocalDateTime eventDate;

    @Valid
    @NotNull
    private LocationDtoCoordinates location;

    private boolean paid;

    @PositiveOrZero
    private long participantLimit;

    private boolean requestModeration = true;

    @NotBlank
    @Length(min = 3, max = 120)
    private String title;
}
