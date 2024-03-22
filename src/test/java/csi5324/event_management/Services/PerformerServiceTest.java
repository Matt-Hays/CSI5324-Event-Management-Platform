package csi5324.event_management.Services;

import csi5324.event_management.domain.Performer;
import csi5324.event_management.domain.PerformerCategory;
import csi5324.event_management.repositories.PerformerRepository;
import csi5324.event_management.services.PerformerService;

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
public class PerformerServiceTest {

    @Mock
    private PerformerRepository performerRepository;

    @InjectMocks
    private PerformerService performerService;

    private Performer performer;

    @BeforeEach
    void setUp() {
        performer = Performer.builder()
                .id(UUID.randomUUID())
                .name("John Doe")
                .category(PerformerCategory.MUSIC)
                .build();
    }

    @Test
    void createPerformer_Success() {
        when(performerRepository.save(any(Performer.class))).thenReturn(performer);

        Performer createdPerformer = performerService.createPerformer(performer);

        assertNotNull(createdPerformer, "The created performer should not be null.");
        assertEquals("John Doe", createdPerformer.getName(), "The performer's name should match the input.");
        assertEquals(PerformerCategory.MUSIC, createdPerformer.getCategory(), "The performer's category should match the input.");
    }
}
