package ru.practicum.explorewithme;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

import static ru.practicum.explorewithme.constant.Constants.DATE_TIME_FORMAT;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class RequestHitDto {

    @NotBlank
    @Size(max = 255)
    private String app;

    @NotBlank
    @Size(max = 255)
    private String uri;

    @NotBlank
    @Size(max = 255)
    private String ip;

    @NotNull
    @JsonFormat(pattern = DATE_TIME_FORMAT)
    private LocalDateTime timestamp;
}
