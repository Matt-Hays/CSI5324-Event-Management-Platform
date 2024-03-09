package csi5324.event_management.domain.repositories;

import csi5324.event_management.domain.Performer;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface PerformerRepository extends CrudRepository<Performer, UUID> {
}
