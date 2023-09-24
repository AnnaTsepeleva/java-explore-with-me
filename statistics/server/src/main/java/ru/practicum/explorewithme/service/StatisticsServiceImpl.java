package ru.practicum.explorewithme.service;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;
import ru.practicum.explorewithme.RequestHitDto;
import ru.practicum.explorewithme.ResponseHitDto;
import ru.practicum.explorewithme.mapper.HitMapper;
import ru.practicum.explorewithme.repository.StatisticsRepository;


import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class StatisticsServiceImpl implements StatisticService {
    private final StatisticsRepository repository;

    @Override
    public void createHit(RequestHitDto hitDto) {
        repository.save(HitMapper.toHitEntityFromRequestHitDto(hitDto));
    }

    @Override
    @Transactional(readOnly = true)
    public List<ResponseHitDto> getStats(LocalDateTime start, LocalDateTime end, List<String> uris, boolean unique) {
        if (start.isAfter(end)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "End cannot be early then start");
        }

        return repository.getStats(start, end, uris, unique);
    }
}
