package ru.practicum.explorewithme.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.practicum.explorewithme.dtomain.request.ParticipationRequestDto;
import ru.practicum.explorewithme.model.Request;

@Mapper(componentModel = "spring", uses = {EventMapper.class})
public interface RequestMapper {

    @Mapping(target = "event", source = "event.id")
    @Mapping(target = "requester", source = "requester.id")
    ParticipationRequestDto toParticipationRequestDto(Request request);

}