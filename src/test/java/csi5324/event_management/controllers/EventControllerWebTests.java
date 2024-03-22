package csi5324.event_management.controllers;

import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;

public class EventControllerWebTests {
    private final WebTestClient webTestClient = WebTestClient.bindToServer()
            .baseUrl("http://localhost:8080")
            .build();

    @Test
    public void postEvent_Success_WebClientTest() {
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

        webTestClient.post()
                .uri("/api/events")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(eventJson)
                .exchange()
                .expectStatus().isOk()
                .expectHeader().contentType(MediaType.APPLICATION_JSON)
                .expectBody()
                .jsonPath("$.name").isEqualTo("Test Event");
    }
}
