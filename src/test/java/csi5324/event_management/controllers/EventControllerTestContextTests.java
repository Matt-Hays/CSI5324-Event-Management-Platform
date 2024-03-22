package csi5324.event_management.controllers;

import csi5324.event_management.domain.Event;
import csi5324.event_management.services.EventService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

/**
 * The TestContext framework provides the Spring Boot Context during testing.
 * <p>
 * The @SpringBootTest annotation, from the TestContext framework, loads the full Spring context.
 */
@SpringBootTest
public class EventControllerTestContextTests {
    @Autowired
    EventService eventService;

    @Test
    public void createEvent_Success() {
        Event e = new Event();
        e.setName("Test Event");
        e.setDateHeld(LocalDate.parse("2024-01-01"));
        e.setDateRegistrationBegins(LocalDate.parse("2023-11-01"));
        e.setDescription("Test Description");
        e.setCapacity(50L);
        e.setStartTime(LocalDateTime.parse("2024-01-01T09:00:00"));
        e.setEndTime(LocalDateTime.parse("2024-01-01T18:00:00"));
        e.setAgeRestricted(true);
        e.setMinimumAge(18);

        Event saved = eventService.createEvent(e);

        assertNotNull(saved.getId());
        assertEquals("Test Event", e.getName());
        assertEquals(LocalDate.parse("2024-01-01"), e.getDateHeld());
        assertEquals(LocalDate.parse("2023-11-01"), e.getDateRegistrationBegins());
        assertEquals("Test Description", e.getDescription());
        assertEquals(50L, e.getCapacity());
        assertEquals(LocalDateTime.parse("2024-01-01T09:00:00"), e.getStartTime());
        assertEquals(LocalDateTime.parse("2024-01-01T18:00:00"), e.getEndTime());
        assertTrue(e.getAgeRestricted());
        assertEquals(18, e.getMinimumAge());
    }
}
