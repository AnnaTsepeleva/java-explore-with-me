package ru.practicum.explorewithme.controller.comment;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.practicum.explorewithme.log.ToLog;
import ru.practicum.explorewithme.service.CommentService;

import javax.validation.constraints.Positive;


@RestController
@RequestMapping("/admin/comments")
@RequiredArgsConstructor
@Validated
@ToLog
public class AdminCommentController {
    private final CommentService commentService;

    @DeleteMapping()
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteCommentByAdmin(@Positive @RequestParam Long commentId) {
        commentService.deleteCommentByAdmin(commentId);
    }
}