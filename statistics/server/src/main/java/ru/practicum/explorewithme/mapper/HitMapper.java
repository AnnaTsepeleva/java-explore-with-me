package ru.practicum.explorewithme.mapper;

import lombok.experimental.UtilityClass;
import ru.practicum.explorewithme.RequestHitDto;
import ru.practicum.explorewithme.model.HitEntity;


@UtilityClass
public class HitMapper {
    public HitEntity toHitEntityFromRequestHitDto(RequestHitDto hitDto) {
        return HitEntity.builder()
                .app(hitDto.getApp())
                .uri(hitDto.getUri())
                .ip(hitDto.getIp())
                .timestamp(hitDto.getTimestamp())
                .build();
    }
}
