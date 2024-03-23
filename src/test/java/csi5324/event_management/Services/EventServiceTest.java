package csi5324.event_management.Services;
import csi5324.event_management.domain.Event;
import csi5324.event_management.services.EventService;
import csi5324.event_management.repositories.EventRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class EventServiceTest {

    @Mock
    private EventRepository eventRepository;

    @InjectMocks
    private EventService eventService;

    private Event event;

    @Test
    void createEvent_Success() {
        when(eventRepository.save(any(Event.class))).thenReturn(event);

        Event createdEvent = eventService.createEvent(event);

        assertNotNull(createdEvent, "The created event should not be null.");
        assertEquals("Test Event", createdEvent.getName(), "The event name should match the input.");
        assertEquals("This is a test event.", createdEvent.getDescription(), "The event description should match the input.");
        assertEquals(100L, createdEvent.getCapacity(), "The event capacity should match the input.");
        assertFalse(createdEvent.getAgeRestricted(), "The event should not be age restricted.");
    }

    @BeforeEach
    void setUp() {
        event = Event.builder()
                .id(UUID.randomUUID())
                .name("Test Event")
                .description("This is a test event.")
                .dateHeld(LocalDate.now().plusDays(1))
                .startTime(LocalDateTime.now().plusDays(1).withHour(10))
                .endTime(LocalDateTime.now().plusDays(1).withHour(12))
                .capacity(100L)
                .ageRestricted(false)
                .build();
    }
}