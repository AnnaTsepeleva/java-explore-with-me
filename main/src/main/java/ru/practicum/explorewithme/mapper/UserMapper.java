package ru.practicum.explorewithme.mapper;

import org.mapstruct.Mapper;
import ru.practicum.explorewithme.dtomain.user.NewUserRequestDto;
import ru.practicum.explorewithme.dtomain.user.UserDto;
import ru.practicum.explorewithme.model.User;

@Mapper(componentModel = "spring")
public interface UserMapper {
    User toUser(NewUserRequestDto newUserRequestDto);

    UserDto toUserDto(User user);
}