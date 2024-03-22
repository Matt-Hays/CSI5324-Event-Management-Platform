package csi5324.event_management.domain;

import csi5324.event_management.repositories.EventRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;


@DataJpaTest
public class EventTests {
    @Autowired
    private EventRepository eventRepository;

    @Test
    @Rollback
    public void event_Success() {
        Event e = new Event();
        e.setName("Test Event");
        e.setDateHeld(LocalDate.of(2020, 1, 1));
        e.setDateRegistrationBegins(LocalDate.of(2019, 11, 1));
        e.setDescription("Test Event");
        e.setCapacity(20L);
        e.setAgeRestricted(false);
        e.setMinimumAge(0);

        Event saved = eventRepository.save(e);

        assertNotNull(saved.getId());
        assertNotNull(saved.getVersion());
        assertEquals(saved.getName(), "Test Event");
        assertEquals(LocalDate.of(2020, 1, 1), saved.getDateHeld());
        assertEquals(LocalDate.of(2019, 11, 1), saved.getDateRegistrationBegins());
        assertEquals("Test Event", saved.getDescription());
        assertEquals(20L, saved.getCapacity());
        assertEquals(false, saved.getAgeRestricted());
        assertEquals(0, saved.getMinimumAge());
    }
}
