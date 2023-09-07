package ru.practicum.explorewithme.service;

import ru.practicum.explorewithme.RequestHitDto;
import ru.practicum.explorewithme.ResponseHitDto;

import java.time.LocalDateTime;
import java.util.List;

public interface StatisticService {

    void createHit(RequestHitDto hitDto);

    List<ResponseHitDto> getStats(LocalDateTime start, LocalDateTime end, List<String> uris, boolean unique);
}
