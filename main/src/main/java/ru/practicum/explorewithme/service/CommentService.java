package ru.practicum.explorewithme.service;

import org.springframework.data.domain.Pageable;
import ru.practicum.explorewithme.dtomain.comment.FullCommentDto;
import ru.practicum.explorewithme.dtomain.comment.NewCommentDto;
import ru.practicum.explorewithme.dtomain.comment.UpdateCommentDto;

import java.util.Collection;
import java.util.List;

public interface CommentService {
    FullCommentDto saveComment(Long userId, NewCommentDto newCommentDto, Long eventId);

    void deleteCommentByAdmin(Long commentId);

    void deleteCommentAddedCurrentUser(Long commentId, Long authorId);

    Collection<FullCommentDto> getCommentsByEventId(Long eventId, Pageable pageable);

    FullCommentDto updateCommentByAuthor(Long commentId, Long authorId, UpdateCommentDto dto);

    List<FullCommentDto> getCommentsByAuthorId(Long userId, Pageable pageable);

    List<FullCommentDto> getUserCommentsByEventId(Long userId, Long eventId, Pageable page);

    FullCommentDto getCommentById(Long commentId, Long userId);
}