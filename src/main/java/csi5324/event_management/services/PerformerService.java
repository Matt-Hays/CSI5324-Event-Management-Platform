package csi5324.event_management.services;

import csi5324.event_management.domain.repositories.PerformerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PerformerService {
    private final PerformerRepository performerRepository;
}
