package csi5324.event_management.Services;


import csi5324.event_management.domain.Location;
import csi5324.event_management.repositories.LocationRepository;
import csi5324.event_management.services.LocationService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class LocationServiceTest {

    @Mock
    private LocationRepository locationRepository;

    @InjectMocks
    private LocationService locationService;

    private Location location;

    @BeforeEach
    void setUp() {
        location = Location.builder()
                .id(UUID.randomUUID())
                .name("Convention Center")
                .identifier("CONV-001")
                .description("A large convention center with multiple halls.")
                .build();
    }

    @Test
    void createLocation_Success() {
        when(locationRepository.save(any(Location.class))).thenReturn(location);

        Location createdLocation = locationService.createLocation(location);

        assertNotNull(createdLocation, "The created location should not be null.");
        assertEquals("Convention Center", createdLocation.getName(), "The location's name should match the input.");
        assertEquals("CONV-001", createdLocation.getIdentifier(), "The location's identifier should match the input.");
        assertEquals("A large convention center with multiple halls.", createdLocation.getDescription(), "The location's description should match the input.");
    }
}
