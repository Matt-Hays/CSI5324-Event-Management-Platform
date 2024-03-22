package csi5324.event_management.controllers;

import csi5324.event_management.domain.Event;
import csi5324.event_management.repositories.EventRepository;
import csi5324.event_management.services.EventService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.BadRequestException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * REST controller for the event domain. Provides basic CRUD functionality.<br/>
 * Endpoint: /api/events <br/>
 * Accepts: application/json <br/>
 * Produces: application/json <br/>
 *
 * @author Matthew Hays
 * @see Event
 * @see EventService
 * @see EventRepository
 * @since 0.0.1
 */
@RestController
@RequestMapping(path = "/api/events", produces = "application/json")
@CrossOrigin("http://localhost:8080")
@RequiredArgsConstructor
public class EventController {
    private final EventService eventService;

    /**
     * Create and persist a single event.
     *
     * @param event The event to be created.
     * @return The persisted event.
     */
    @PostMapping(consumes = "application/json")
    public ResponseEntity<Event> createEvent(@RequestBody Event event) {
        try {
            return ResponseEntity.ok(eventService.createEvent(event));
        } catch (Exception ex) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * Create and persist a batch of events.
     *
     * @param events The Iterable&lt;Event&gt; events to create in batch processing.
     * @return The Iterable&lt;Event&gt; events persisted.
     */
    @PostMapping(params = "batch", consumes = "application/json")
    public Iterable<Event> createEventsBatch(@RequestBody Iterable<Event> events) {
        return eventService.createEventBatch(events);
    }

    /**
     * Get a single event. Searching is performed by id.
     *
     * @param event The event to find. Must contain a valid id.
     * @return The found event or null if no event is found by the given id.
     */
    @GetMapping(consumes = "application/json")
    public ResponseEntity<Event> getEvent(@RequestBody Event event) {
        try {
            return ResponseEntity.ok(eventService.getEvent(event));
        } catch (EntityNotFoundException ex) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    /**
     * Search for multiple events in batch processing.
     *
     * @param events The Iterable&lt;Event&gt; events to search by. Each individual event in the Iterable must contain
     *               a valid id.
     * @return An Iterable&lt;Event&gt; events found. The Iterable will be empty if no events are found.
     */
    @GetMapping(params = "batch", consumes = "application/json")
    public Iterable<Event> getEvents(@RequestBody Iterable<Event> events) {
        return eventService.getEvents(events);
    }

    /**
     * Get an Iterable&lt;Event&gt; of events found that contain a certain string partial in its name attribute. <br/>
     * The search is case-insensitive.
     *
     * @param name The String name to search using.
     * @return Iterable&lt;Event&gt; events found containing the string partial given.
     */
    @GetMapping(params = "name", consumes = "application/json")
    public Iterable<Event> getEventsByNameContaining(@RequestParam(name = "name") String name) {
        return eventService.getEventsByNameContaining(name);
    }

    /**
     * Update an existing event using the POST method. The POST method replaces existing data in its entirety.
     *
     * @param event The event to update. Must contain all required data fields including id.
     * @return The updated event.
     */
    @PostMapping(params = "post", consumes = "application/json")
    public Event postEvent(@RequestBody Event event) {
        return eventService.postEvent(event);
    }

    /**
     * Update an existing event using the PATCH method. The PATCH method updates existing data in-place without override.
     *
     * @param event The event to update. Must contain a valid id for the event to be updated and modify fields must be set.
     * @return The updated event.
     */
    @PatchMapping(consumes = "application/json")
    public ResponseEntity<Event> patchEvent(@RequestBody Event event) {
        try {
            System.out.println("Hello: " + event);
            return ResponseEntity.ok(eventService.patchEvent(event));
        } catch (BadRequestException ex) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * Delete a specified, single, event.
     *
     * @param event The event to delete
     */
    @DeleteMapping(consumes = "application/json")
    public void deleteEvent(@RequestBody Event event) {
        eventService.deleteEvent(event);
    }

    /**
     * Deletes a batch of events.
     *
     * @param events The Iterable&lt;Event&gt; events to be deleted.
     */
    @DeleteMapping(params = "batch", consumes = "application/json")
    public void deleteEventsBatch(@RequestBody Iterable<Event> events) {
        eventService.deleteEventBatch(events);
    }
}
