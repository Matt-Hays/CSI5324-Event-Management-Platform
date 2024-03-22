package csi5324.event_management.controllers;

import csi5324.event_management.domain.Performer;
import csi5324.event_management.services.PerformerService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.BadRequestException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/api/performers", produces = "application/json", consumes = "application/json")
@CrossOrigin("http://localhost:8080")
@RequiredArgsConstructor
public class PerformerController {
    private final PerformerService performerService;

    @PostMapping
    public Performer createPerformer(@RequestBody Performer performer) {
        return performerService.createPerformer(performer);
    }

    @PostMapping(params = "batch")
    public Iterable<Performer> createPerformersBatch(@RequestBody Iterable<Performer> performers) {
        return performerService.createPerformersBatch(performers);
    }

    @GetMapping
    public ResponseEntity<Performer> getPerformer(Performer performer) {
        try {
            return ResponseEntity.ok(performerService.getPerformer(performer));
        } catch (EntityNotFoundException ex) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping(params = "batch")
    public Iterable<Performer> getPerformersBatch(@RequestBody Iterable<Performer> performers) {
        return performerService.getPerformers(performers);
    }

    @GetMapping(params = "name")
    public Iterable<Performer> getPerformerByNameContaining(@RequestParam(name = "name") String name) {
        return performerService.getPerformersByNameContaining(name);
    }

    @PostMapping(params = "update")
    public Performer postPerformer(@RequestBody Performer performer) {
        return performerService.postPerformer(performer);
    }

    @PatchMapping
    public ResponseEntity<Performer> patchPerformer(@RequestBody Performer performer) {
        try {
            return ResponseEntity.ok(performerService.patchPerformer(performer));
        } catch (BadRequestException ex) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping
    public void deletePerformer(@RequestBody Performer performer) {
        performerService.deletePerformer(performer);
    }

    @DeleteMapping(params = "batch")
    public void deletePerformerBatch(@RequestBody Iterable<Performer> performers) {
        performerService.deletePerformersBatch(performers);
    }
}
