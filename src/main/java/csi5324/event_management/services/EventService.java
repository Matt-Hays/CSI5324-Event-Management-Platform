package csi5324.event_management.services;

import csi5324.event_management.domain.Event;
import csi5324.event_management.domain.repositories.EventRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

/**
 * Provides CRUD functionality for the Event domain entity.
 *
 * @author Matthew Hays
 * @see Event
 * @see EventRepository
 * @see EventController
 * @since 0.0.1
 */
@Service
@RequiredArgsConstructor
public class EventService {
    private final EventRepository eventRepository;

    /**
     * Creates an event entity and persists it within the database.
     *
     * @param e Event to create.
     * @return The created and persisted event.
     */
    public Event createEvent(Event e) {
        return eventRepository.save(e);
    }

    /**
     * Creates a batch of events.
     *
     * @param events Iterable&lt;Event&gt; The set of events to create.
     * @return Iterable&lt;Event&gt; The created and persisted set of events.
     */
    public Iterable<Event> createEventBatch(Iterable<Event> events) {
        return eventRepository.saveAll(events);
    }

    /**
     * Gets an event by id.
     *
     * @param e The event to find. Must contain a valid id.
     * @return Event The found event.
     * @throws EntityNotFoundException If no event is found by the given id.
     */
    public Event getEvent(Event e) throws EntityNotFoundException {
        return eventRepository
                .findById(e.getId())
                .orElseThrow(EntityNotFoundException::new);
    }

    /**
     * Get multiple events by id.
     *
     * @param events The events to find by id. Each event must contain a valid id.
     * @return Iterable&lt;Event&gt; The events found. Empty if no events found by the given ids.
     */
    public Iterable<Event> getEvents(Iterable<Event> events) {
        return eventRepository.findAllById(
                StreamSupport.stream(events.spliterator(), false)
                        .map(Event::getId)
                        .collect(Collectors.toList())
        );
    }

    /**
     * Gets an event by event name if the name is contained within the event name attribute.
     *
     * @param nameContaining A String value of the name partial to search using. Case-insensitive.
     * @return Iterable&lt;Event&gt; A list of events that match the query.
     */
    public Iterable<Event> getEventsByNameContaining(String nameContaining) {
        return eventRepository.getEventsByNameContainingIgnoreCase(nameContaining);
    }

    /**
     * Posts an event. This is the POST method which replaces existing data.
     *
     * @param e The event to POST.
     * @return The posted and persisted event.
     */
    public Event postEvent(Event e) {
        return eventRepository.save(e);
    }

    /**
     * Patches an event. This is the PATCH method which updates existing data in-place.
     *
     * @param e The event to PATCH.
     * @return The patched and persisted event.
     */
    public Event patchEvent(Event e) {
        Event patched = new Event();
        if (e.getId() == null) return null;
        patched.setId(e.getId());
        if (e.getName() != null && !e.getName().isBlank()) patched.setName(e.getName());
        if (e.getDateHeld() != null && !e.getDateHeld().isBefore(LocalDate.now())) patched.setDateHeld(e.getDateHeld());
        if (e.getDateRegistrationBegins() != null && !e.getDateRegistrationBegins().isBefore(LocalDate.now()) && !e.getDateRegistrationBegins().isAfter(e.getDateHeld()))
            patched.setDateRegistrationBegins(e.getDateRegistrationBegins());
        if (e.getDescription() != null && !e.getDescription().isBlank()) patched.setDescription(e.getDescription());
        if (e.getCapacity() != null && e.getCapacity() > 0) patched.setCapacity(e.getCapacity());
        if (e.getStartTime() != null && e.getStartTime().isBefore(e.getEndTime()) && e.getStartTime().isAfter(e.getDateHeld() != null ? e.getDateHeld().atStartOfDay() : null))
            patched.setStartTime(e.getStartTime());
        if (e.getEndTime() != null && e.getEndTime().isAfter(e.getStartTime()) && e.getEndTime().isAfter(e.getDateHeld() != null ? e.getDateHeld().atStartOfDay() : null))
            patched.setEndTime(e.getEndTime());
        if (e.getAgeRestricted() != null) patched.setAgeRestricted(e.getAgeRestricted());
        if (e.getMinimumAge() != null) patched.setMinimumAge(e.getMinimumAge());
        if (e.getLocation() != null) patched.setLocation(e.getLocation());
        if (e.getPerformer() != null) patched.setPerformer(e.getPerformer());
        return eventRepository.save(patched);
    }

    /**
     * Deletes an event.
     *
     * @param e The event to delete.
     */
    public void deleteEvent(Event e) {
        eventRepository.delete(e);
    }

    /**
     * Deletes a batch of events. Batch operation.
     *
     * @param events The Iterable&lt;Event&gt; to delete.
     */
    public void deleteEventBatch(Iterable<Event> events) {
        eventRepository.deleteAll(events);
    }
}
