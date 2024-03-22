package csi5324.event_management.controllers;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.hamcrest.Matchers.is;

/**
 * Integration tests that utilize the Test Context framework as well as the Spring MVC test framework.
 * <p>
 * Performs testing of controllers against a mock servlet.
 * <p>
 * {@literal @SpringBootTest} is an annotation from the Test Context framework which bootstraps the entire application context and
 * loads the application configuration.
 * <p>
 * {@literal @AutoConfigureMockMvc} is an annotation from the Spring MVC Test framework which allows us to perform requests against
 * our controllers in a simulated environment without the need to start a full HTTP server.
 */
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
@AutoConfigureMockMvc
public class EventControllerTests {

    @Autowired
    private MockMvc mockMvc;

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

        mockMvc.perform(post("/api/events")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(eventJson))
                .andExpect(status().isBadRequest())
                .andExpect(content().string(""));
    }


}
