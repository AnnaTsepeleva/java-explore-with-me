package ru.practicum.explorewithme.controller.comment;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.practicum.explorewithme.dtomain.comment.NewCommentDto;
import ru.practicum.explorewithme.log.ToLog;
import ru.practicum.explorewithme.dtomain.comment.FullCommentDto;
import ru.practicum.explorewithme.dtomain.comment.UpdateCommentDto;
import ru.practicum.explorewithme.service.CommentService;
import ru.practicum.explorewithme.util.OffsetBasedPageRequest;

import javax.validation.Valid;
import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;
import java.util.List;

import static ru.practicum.explorewithme.constant.Constants.PAGE_DEFAULT_FROM;
import static ru.practicum.explorewithme.constant.Constants.PAGE_DEFAULT_SIZE;

@RestController
@RequestMapping("/users/{userId}")
@RequiredArgsConstructor
@Validated
@ToLog
@Slf4j
public class PrivateCommentController {
    private final CommentService commentService;

    @PostMapping("/comments")
    @ResponseStatus(HttpStatus.CREATED)
    public FullCommentDto saveComment(@Positive @PathVariable(value = "userId") Long userId,
                                      @Valid @RequestBody NewCommentDto dto) {
        return commentService.saveComment(userId, dto);
    }

    @DeleteMapping("/comments/{commentId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteCommentAddedUser(@PathVariable(value = "commentId") Long commentId,
                                              @PathVariable(value = "userId") Long userId) {
        commentService.deleteCommentAddedUser(commentId, userId);
    }

    @PatchMapping("/comments")
    public FullCommentDto updateCommentByAuthor(@Positive @PathVariable(value = "userId") Long userId,
                                                @Valid @RequestBody UpdateCommentDto dto) {
        return commentService.updateCommentByAuthor(userId, dto);
    }

    @GetMapping("/comments/{commentId}")
    public FullCommentDto getCommentById(@PathVariable(value = "userId") Long userId,
                                         @PathVariable(value = "commentId") Long commentId) {
        return commentService.getCommentById(commentId, userId);
    }

    @GetMapping("/comments")
    public List<FullCommentDto> getCommentsByUser(@PathVariable(value = "userId") Long userId,
                                                  @RequestParam(defaultValue = PAGE_DEFAULT_FROM)
                                                  @PositiveOrZero Integer from,
                                                  @RequestParam(defaultValue = PAGE_DEFAULT_SIZE)
                                                  @Positive Integer size) {
        Pageable page = new OffsetBasedPageRequest(from, size);
        return commentService.getCommentsByAuthorId(userId, page);
    }

    @GetMapping("/events/{eventId}/comments")
    public List<FullCommentDto> getCommentsByEventId(@PathVariable(value = "userId") Long userId,
                                                     @PathVariable(value = "eventId") Long eventId,
                                                     @RequestParam(defaultValue = PAGE_DEFAULT_FROM)
                                                     @PositiveOrZero Integer from,
                                                     @RequestParam(defaultValue = PAGE_DEFAULT_SIZE)
                                                     @Positive Integer size) {
        Pageable page = new OffsetBasedPageRequest(from, size);
        return commentService.getUserCommentsByEventId(userId, eventId, page);
    }
}