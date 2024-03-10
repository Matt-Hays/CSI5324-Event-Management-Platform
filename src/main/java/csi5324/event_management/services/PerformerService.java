package csi5324.event_management.services;

import csi5324.event_management.controllers.PerformerController;
import csi5324.event_management.domain.Performer;
import csi5324.event_management.repositories.PerformerRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.BadRequestException;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

/**
 * Provides basic CRUD functionality for the Performer entity.
 *
 * @author Matthew Hays
 * @see Performer
 * @see PerformerRepository
 * @see PerformerController
 * @since 0.0.1
 */
@Service
@RequiredArgsConstructor
public class PerformerService {
    private final PerformerRepository performerRepository;

    /**
     * Create a new performer.
     *
     * @param p The performer to create.
     * @return The created performer.
     */
    public Performer createPerformer(Performer p) {
        return performerRepository.save(p);
    }

    /**
     * Create new performers batch operation.
     *
     * @param performers The Iterable&lt;Performer&gt; performers to create.
     * @return The Iterable&lt;Performer&gt; created performers.
     */
    public Iterable<Performer> createPerformersBatch(Iterable<Performer> performers) {
        return performerRepository.saveAll(performers);
    }

    /**
     * Get a performer by id.
     *
     * @param p The performer to get. Must contain a valid id.
     * @return The found performer.
     * @throws EntityNotFoundException If no performer is found by the given id.
     */
    public Performer getPerformer(Performer p) throws EntityNotFoundException {
        return performerRepository.findById(p.getId()).orElseThrow(EntityNotFoundException::new);
    }

    /**
     * Get a batch of performers by id.
     *
     * @param performers The Iterable&lt;Performer&gt; performers to find by id. All performers must contain a valid id.
     * @return The Iterable&lt;Performer&gt; performers found.
     */
    public Iterable<Performer> getPerformers(Iterable<Performer> performers) {
        return performerRepository.findAllById(
                StreamSupport.stream(performers.spliterator(), false)
                        .map(Performer::getId)
                        .collect(Collectors.toList())
        );
    }

    /**
     * Get performers by name containing.
     *
     * @param nameContaining The name to search by. Case-insensitive.
     * @return The Iterable&lt;Performer&gt; performers found containing the given name.
     */
    public Iterable<Performer> getPerformersByNameContaining(String nameContaining) {
        return performerRepository.getPerformersByNameContainingIgnoreCase(nameContaining);
    }

    /**
     * Post an existing performer. POST method replaces all eisting data.
     *
     * @param p The performer to post.
     * @return The posted performer.
     */
    public Performer postPerformer(Performer p) {
        return performerRepository.save(p);
    }

    /**
     * Patch an existing performer. PATCH updates existing data in-place.
     *
     * @param p The performer to patch.
     * @return The patched performer.
     */
    public Performer patchPerformer(Performer p) throws BadRequestException {
        if (p.getId() == null) throw new BadRequestException();
        Performer patched = new Performer();
        patched.setId(p.getId());
        if (p.getName() != null && !p.getName().isBlank()) patched.setName(p.getName());
        if (p.getCategory() != null) patched.setCategory(p.getCategory());
        if (p.getEvents() != null && !p.getEvents().isEmpty()) patched.setEvents(p.getEvents());
        return performerRepository.save(patched);
    }

    /**
     * Delete a performer.
     *
     * @param p The performer to delete.
     */
    public void deletePerformer(Performer p) {
        performerRepository.delete(p);
    }

    /**
     * Delete performers batch operation.
     *
     * @param performers The Iterable&lt;Performer&gt; performers to delete.
     */
    public void deletePerformersBatch(Iterable<Performer> performers) {
        performerRepository.deleteAll(performers);
    }
}
