package csi5324.event_management.controllers;

import csi5324.event_management.domain.Event;
import csi5324.event_management.services.EventService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/events", produces = "application/json")
@RequiredArgsConstructor
public class EventController {
    private final EventService eventService;

    @PostMapping(consumes = "application/json")
    public Event createEvent(@Validated Event event) {
        return eventService.createEvent(event);
    }

    @PostMapping(params = "batch", consumes = "application/json")
    public Iterable<Event> createEventsBatch(@Validated Iterable<Event> events) {
        return eventService.createEventBatch(events);
    }

    @GetMapping(consumes = "application/json")
    public ResponseEntity<Event> getEvent(Event event) {
        try {
            return ResponseEntity.ok(eventService.getEvent(event));
        } catch (EntityNotFoundException ex) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping(params = "batch", consumes = "application/json")
    public Iterable<Event> getEvents(Iterable<Event> events) {
        return eventService.getEvents(events);
    }

    @GetMapping(params = "name", consumes = "application/json")
    public Iterable<Event> getEventsByNameContaining(@RequestParam(name = "name") String name) {
        return eventService.getEventsByNameContaining(name);
    }

    @PostMapping(params = "post", consumes = "application/json")
    public Event postEvent(@Validated Event event) {
        return eventService.postEvent(event);
    }

    @PatchMapping(consumes = "application/json")
    public Event patchEvent(Event event) {
        return eventService.patchEvent(event);
    }

    @DeleteMapping(consumes = "application/json")
    public void deleteEvent(Event event) {
        eventService.deleteEvent(event);
    }

    @DeleteMapping(params = "batch", consumes = "application/json")
    public void deleteEventsBatch(Iterable<Event> events) {
        eventService.deleteEventBatch(events);
    }
}
