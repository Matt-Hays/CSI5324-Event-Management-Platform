package csi5324.event_management.repositories;

import csi5324.event_management.domain.Event;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface EventRepository extends CrudRepository<Event, UUID> {
    Iterable<Event> getEventsByNameContainingIgnoreCase(String nameContaining);
}
