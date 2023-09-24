package ru.practicum.explorewithme.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.NullValueMappingStrategy;
import ru.practicum.explorewithme.dtomain.comment.FullCommentDto;
import ru.practicum.explorewithme.model.Comment;

@Mapper(componentModel = "spring", uses = {UserMapper.class},
        nullValueMappingStrategy = NullValueMappingStrategy.RETURN_DEFAULT)
public interface CommentMapper {
    FullCommentDto toFullCommentDto(Comment comment);
}