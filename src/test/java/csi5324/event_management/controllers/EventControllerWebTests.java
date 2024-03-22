package csi5324.event_management.controllers;

import csi5324.event_management.domain.Event;
import csi5324.event_management.repositories.EventRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class EventControllerWebTests {
    private final WebTestClient webTestClient = WebTestClient.bindToServer()
            .baseUrl("http://localhost:8080")
            .build();

    String eventJson;

    @BeforeEach
    public void setup() {
        eventJson = "{" +
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
    }

    @Test
    public void postEvent_Success_WebClientTest() {
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

    @Test
    public void getEvents_Success_WebTestClient() {
        webTestClient.get()
                .uri("/api/events")
                .exchange()
                .expectStatus().isOk()
                .expectHeader().contentType(MediaType.APPLICATION_JSON)
                .expectBody().jsonPath("$[0].name").isEqualTo("Test Event");
    }
}
