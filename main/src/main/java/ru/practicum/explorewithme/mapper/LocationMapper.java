package ru.practicum.explorewithme.mapper;

import org.mapstruct.Mapper;
import ru.practicum.explorewithme.dtomain.location.LocationDtoCoordinates;
import ru.practicum.explorewithme.model.Location;

@Mapper(componentModel = "spring")
public interface LocationMapper {
    Location toLocation(LocationDtoCoordinates locationDtoCoordinates);

}
