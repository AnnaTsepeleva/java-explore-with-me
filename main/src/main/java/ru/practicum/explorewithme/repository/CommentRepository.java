package ru.practicum.explorewithme.repository;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.practicum.explorewithme.model.Comment;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface CommentRepository extends JpaRepository<Comment, Long> {

    @Query("select c" +
            " from Comment c" +
            " where c.event.id = :eventId")
    List<Comment> getCommentsByEventId(@Param("eventId") Long eventId, Pageable pageable);

    @Query("SELECT c " +
            "FROM Comment c " +
            "WHERE c.id =:commentId " +
            "AND c.author.id =:authorId ")
    Optional<Comment> findCommentByIdAndAuthorId(@Param("commentId") Long commentId, @Param("authorId") Long authorId);

    @Query("select c" +
            " from Comment c" +
            " where c.author.id = :authorId")
    List<Comment> getCommentsByAuthorId(@Param("authorId") Long userId, Pageable pageable);

    @Query("select c" +
            " from Comment c" +
            " where c.author.id = :authorId" +
            " and c.event.id = :eventId")
    List<Comment> getUserCommentsByEventId(@Param("authorId") Long userId, @Param("eventId") Long eventId, Pageable pageable);
}