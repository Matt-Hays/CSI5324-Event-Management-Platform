package csi5324.event_management.domain.repositories;

import csi5324.event_management.domain.Location;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.swing.text.html.Option;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface LocationRepository extends CrudRepository<Location, UUID> {
    Iterable<Location> getLocationsByIdentifierContainingIgnoreCase(String locationIdentifier);
}
