package csi5324.event_management.controllers;

import csi5324.event_management.domain.Location;
import csi5324.event_management.repositories.LocationRepository;
import csi5324.event_management.services.LocationService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.BadRequestException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Location REST Controller. Provides basic CRUD functionality for the Location domain.
 *
 * @author Matthew Hays
 * @see Location
 * @see LocationService
 * @see LocationRepository
 * @since 0.0.1
 */
@RestController
@RequestMapping(path = "/api/locations", produces = "application/json", consumes = "application/json")
@RequiredArgsConstructor
public class LocationController {
    private final LocationService locationService;

    /**
     * Create a single new location.
     *
     * @param location The location to create.
     * @return The created and persisted location.
     */
    @PostMapping
    public Location createLocation(@Valid @RequestBody Location location) {
        return locationService.createLocation(location);
    }

    /**
     * Create multiple locations in a batch.
     *
     * @param locations The Iterable&lt;Location&gt; locations to create.
     * @return The Iterable&lt;Location&gt; locations created and persisted.
     */
    @PostMapping(params = "batch")
    public Iterable<Location> createLocationsBatch(@Valid @RequestBody Iterable<Location> locations) {
        return locationService.createLocationsBatch(locations);
    }

    /**
     * Get a location by id.
     *
     * @param location The location to get. Must contain a valid id.
     * @return The found location.
     */
    @GetMapping
    public ResponseEntity<Location> getLocation(@RequestBody Location location) {
        try {
            return ResponseEntity.ok(locationService.getLocation(location));
        } catch (EntityNotFoundException ex) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    /**
     * Get a batch of locations by id.
     *
     * @param locations The Iterable&lt;Location&gt; locations to find. All locations must contain a valid id.
     * @return The Iterable&lt;Location&gt; locations found.
     */
    @GetMapping(params = "batch")
    public Iterable<Location> getLocationsBatch(@RequestBody Iterable<Location> locations) {
        return locationService.getLocationBatch(locations);
    }

    /**
     * Get a location by location identifier.
     *
     * @param locationIdentifier The internal location identifier of the location to retrieve.
     * @return The location with the matching location identifier.
     */
    @GetMapping(params = "locationIdentifier")
    public Iterable<Location> getLocationByIdentifier(@RequestParam(name = "locationIdentifier") String locationIdentifier) {
        return locationService.getLocationByIdentifier(locationIdentifier);
    }

    /**
     * Update an existing location using the POST method. The POST method replaces all existing data.
     *
     * @param location The location to update. Must contain a valid id.
     * @return The updated location.
     */
    @PostMapping(params = "update")
    public Location postLocation(@Valid @RequestBody Location location) {
        return locationService.postLocation(location);
    }

    /**
     * Update an existing location using the PATCH method. The PATCH method updates a location in-place.
     *
     * @param location The location to patch. Must contain a valid id.
     * @return The updated location.
     */
    @PatchMapping
    public ResponseEntity<Location> patchLocation(@RequestBody Location location) {
        try {
            return ResponseEntity.ok(locationService.patchLocation(location));
        } catch (BadRequestException ex) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * Delete a location.
     *
     * @param location The location to delete.
     */
    @DeleteMapping
    public void deleteLocation(@RequestBody Location location) {
        locationService.deleteLocation(location);
    }

    /**
     * Delete a batch of locations.
     *
     * @param locations The Iterable&lt;Location&gt; locations to delete.
     */
    @DeleteMapping(params = "batch")
    public void deleteLocationsBatch(@RequestBody Iterable<Location> locations) {
        locationService.deleteLocationsBatch(locations);
    }
}