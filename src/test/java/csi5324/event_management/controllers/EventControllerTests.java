package csi5324.event_management.controllers;

import csi5324.event_management.domain.Event;
import csi5324.event_management.services.EventService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.time.LocalDateTime;

import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.when;

/**
 * The {@literal @WebMvcTest} annotation loads the Web layer of the Spring context as a subset of the Spring context and is used for
 * performing web layer testing.
 * <p>
 * The {@literal @MockBean} annotation is used to mock dependencies of the tested controller (in this case, EventService) to isolate the
 * controller and perform unit testing. Mocked responses must be provided for the mocked bean so that testing can occur.
 */
@WebMvcTest(controllers = EventController.class)
public class EventControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private EventService eventService;

    Event mockedEvent;

    @Test
    public void postEvent_Success() throws Exception {
        String eventJson = "{" +
                "\"name\": \"Test Event\"," +
                "\"dateHeld\": \"2024-01-01\"," +
                "\"dateRegistrationBegins\": \"2023-11-01\"," +
                "\"description\": \"Test Description\"," +
                "\"capacity\": 50," +
                "\"startTime\": \"2024-01-01T09:00:00\"," +
                "\"endTime\": \"2024-01-01T18:00:00\"," +
                "\"ageRestricted\": true," +
                "\"minimumAge\": 18" +
                "}";

        when(eventService.createEvent(any(Event.class))).thenReturn(mockedEvent);


        mockMvc.perform(post("/api/events")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(eventJson))
                .andExpect(status().isOk())
                .andExpect(content()
                        .contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.name", is("Test Event")));
    }

    @Test
    public void postEvent_Fail_On_Start_Time_Null() throws Exception {
        String eventJson = "{" +
                "\"name\": \"Test Event\"," +
                "\"dateHeld\": \"2024-01-01\"," +
                "\"dateRegistrationBegins\": \"2023-11-01\"," +
                "\"description\": \"Test Description\"," +
                "\"capacity\": 50," +
                "\"endTime\": \"2024-01-01T18:00:00\"," +
                "\"ageRestricted\": true," +
                "\"minimumAge\": 18" +
                "}";
        mockedEvent.setStartTime(null);
        when(eventService.createEvent(mockedEvent)).thenThrow();

        mockMvc.perform(post("/api/events")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(eventJson))
                .andExpect(status().isBadRequest())
                .andExpect(content().string(""));
    }

    @BeforeEach
    public void setup() {
        mockedEvent = new Event();
        mockedEvent.setName("Test Event");
        mockedEvent.setDateHeld(LocalDate.parse("2024-01-01"));
        mockedEvent.setDateRegistrationBegins(LocalDate.parse("2023-11-01"));
        mockedEvent.setDescription("Test Description");
        mockedEvent.setCapacity(50L);
        mockedEvent.setStartTime(LocalDateTime.parse("2024-01-01T09:00:00"));
        mockedEvent.setEndTime(LocalDateTime.parse("2024-01-01T18:00:00"));
        mockedEvent.setAgeRestricted(true);
        mockedEvent.setMinimumAge(18);
    }
}
