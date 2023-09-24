package ru.practicum.explorewithme.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.practicum.explorewithme.model.Request;
import ru.practicum.explorewithme.model.enums.RequestStatus;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

public interface RequestRepository extends JpaRepository<Request, Long> {
    List<Request> findByRequesterId(Long userId);

    Long countAllByEventIdAndStatus(Long eventId, RequestStatus status);

    List<Request> findAllByEventIdAndEventInitiatorId(Long eventId, Long userId);

    List<Request> findAllByStatusAndEventIdIn(RequestStatus status, Collection<Long> eventIds);

    List<Request> findAllByEventIdAndEventInitiatorIdAndIdIn(Long eventId, Long userId, Collection<Long> requestsId);

    @Query ("select r" +
            " from Request r" +
            " where r.event.id = :eventId" +
            " and r.requester.id = :requesterId")
    Optional<Request> findByEventIdAndRequesterId(@Param("eventId") Long eventId, @Param("requesterId")Long requesterId);


}