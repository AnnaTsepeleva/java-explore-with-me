package ru.practicum.explorewithme;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResponseHitDto {
    private String app;
    private String uri;
    private Long hits;
}
