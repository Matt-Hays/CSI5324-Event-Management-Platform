package csi5324.event_management.services;

import csi5324.event_management.domain.Location;
import csi5324.event_management.domain.repositories.LocationRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

/**
 * Provides basic CRUD functionality for the Location entity.
 *
 * @author Matthew Hays
 * @see Location
 * @see LocationRepository
 * @see LocationController
 * @since 0.0.1
 */
@Service
@RequiredArgsConstructor
public class LocationService {
    private final LocationRepository locationRepository;

    /**
     * Create and persist a new location.
     *
     * @param l The location to save.
     * @return The saved location.
     */
    public Location createLocation(Location l) {
        return locationRepository.save(l);
    }

    /**
     * Create and persist a batch of locations.
     *
     * @param locations Iterable&lt;Location&gt; The locations to save.
     * @return Iterable&lt;Location&gt; The saved locations.
     */
    public Iterable<Location> createLocationsBatch(Iterable<Location> locations) {
        return locationRepository.saveAll(locations);
    }

    /**
     * Get a location by id.
     *
     * @param l The location to retrieve. Must contain a valid id.
     * @return The found location.
     * @throws EntityNotFoundException If no location is found with the given id.
     */
    public Location getLocation(Location l) throws EntityNotFoundException {
        return locationRepository.findById(l.getId()).orElseThrow(EntityNotFoundException::new);
    }

    /**
     * Get an Iterable&lt;Location&gt; by ids.
     *
     * @param locations The Iterable&lt;Location&gt; to find. All locations must contain a valid id.
     * @return The Iterable&lt;Location&gt; locations found.
     */
    public Iterable<Location> getLocationBatch(Iterable<Location> locations) {
        return locationRepository.findAllById(
                StreamSupport.stream(locations.spliterator(), false)
                        .map(Location::getId)
                        .collect(Collectors.toList())
        );
    }

    /**
     * Post an updated location. POST method replaces all existing data.
     *
     * @param l The location to post.
     * @return The posted location.
     */
    public Location postLocation(Location l) {
        return locationRepository.save(l);
    }

    /**
     * Patch a location. PATCH method updates a location in-place.
     *
     * @param l The location to patch.
     * @return The patched location.
     */
    public Location patchLocation(Location l) {
        if (l.getId() == null) return null;
        Location patched = new Location();
        patched.setId(l.getId());
        if (l.getName() != null && !l.getName().isBlank()) patched.setName(l.getName());
        if (l.getIdentifier() != null && !l.getIdentifier().isBlank()) patched.setIdentifier(l.getIdentifier());
        if (l.getDescription() != null && !l.getDescription().isBlank()) patched.setDescription(l.getDescription());
        if (l.getEvents() != null && !l.getEvents().isEmpty()) patched.setEvents(l.getEvents());
        return locationRepository.save(patched);
    }

    /**
     * Delete a location.
     *
     * @param l The location to delete.
     */
    public void deleteLocation(Location l) {
        locationRepository.delete(l);
    }

    /**
     * Delete locations batch operation.
     *
     * @param locations The Iterable&lt;Location&gt; containing locations to delete.
     */
    public void deleteLocationsBatch(Iterable<Location> locations) {
        locationRepository.deleteAll(locations);
    }
}
