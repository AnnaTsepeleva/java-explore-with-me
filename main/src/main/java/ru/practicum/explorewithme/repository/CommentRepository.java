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

    @Query("SELECT c.event.id AS eventId, COUNT(c.id) AS commentCount " +
            "FROM Comment c " +
            "WHERE c.event.id IN :eventIds " +
            "GROUP BY c.event.id")
    List<Map<Long, Long>> countCommentsByEventIdsIn(@Param("eventIds") Collection<Long> eventIds);

    @Query("SELECT c " +
            "FROM Comment c " +
            "WHERE c.id =:commentId " +
            "AND c.author.id =:authorId ")
    Optional<Comment> findCommentByIdAndAuthorId(@Param("commentId") Long eventId, @Param("authorId") Long authorId);

    @Query("select c" +
            " from Comment c" +
            " where c.author.id = :authorId")
    List<Comment> getCommentsByAuthorId(@Param("authorId") Long userId, Pageable pageable);

    @Query("select c" +
            " from Comment c" +
            " where c.author.id = :authorId" +
            " and c.event.id = :eventId")
    List<Comment> getUserCommentsByEventId(@Param("eventId") Long userId, @Param("authorId") Long eventId, Pageable pageable);
}