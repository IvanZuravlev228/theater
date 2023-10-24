package tr11.theater.controller;

import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.apache.commons.collections4.Get;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tr11.theater.dto.performance.PerformanceRequestDto;
import tr11.theater.dto.performance.PerformancesResponseDto;
import tr11.theater.model.Actor;
import tr11.theater.model.Performance;
import tr11.theater.repository.PerformanceRepository;
import tr11.theater.service.ActorService;
import tr11.theater.service.PerformanceService;
import tr11.theater.service.PrizesService;
import tr11.theater.service.mapper.RequestResponseMapper;

@RequiredArgsConstructor
@RestController
@RequestMapping("/performances")
public class PerformanceController {
    private final PerformanceService performanceService;
    private final RequestResponseMapper<Performance, PerformanceRequestDto, PerformancesResponseDto> performanceMapper;
    private final PerformanceRepository performanceRepository;
    private final ActorService actorService;

    @PostMapping
    public ResponseEntity<PerformancesResponseDto> save(@RequestBody PerformanceRequestDto requestEntity) {
        return new ResponseEntity<>(performanceMapper.toDto(
                performanceService.save(performanceMapper.toModel(requestEntity))), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PerformancesResponseDto> getById(@PathVariable Long id) {
        return new ResponseEntity<>(performanceMapper.toDto(performanceService.getById(id)), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<PerformancesResponseDto>> getAll() {
        return new ResponseEntity<>(performanceService.getAll()
                .stream()
                .map(performanceMapper::toDto)
                .collect(Collectors.toList()), HttpStatus.OK);
    }

    @PutMapping("/{prevId}")
    public ResponseEntity<PerformancesResponseDto> update(@PathVariable Long prevId,
                                                          @RequestBody PerformanceRequestDto newEntity) {
        return new ResponseEntity<>(performanceMapper.toDto(
                performanceService.update(prevId, performanceMapper.toModel(newEntity))), HttpStatus.OK);
    }

    @PutMapping("/actors/{prevId}")
    public ResponseEntity<PerformancesResponseDto> addActors(@PathVariable Long prevId,
                                                             @RequestBody List<Long> actorIds) {
        actorIds.stream().map(acId -> performanceService.addActorToPerformance(acId, prevId)).collect(Collectors.toList());
        return new ResponseEntity<>(performanceMapper.toDto(performanceService.getById(prevId)), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        performanceService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/contract/loading")
    public ResponseEntity<List<PerformancesResponseDto>> getLoading() {
        return new ResponseEntity<>(performanceRepository.findAllLoading()
                .stream()
                .map(performanceMapper::toDto)
                .collect(Collectors.toList()), HttpStatus.OK);
    }

    @DeleteMapping("/{perId}/actor/{actorId}")
    public ResponseEntity<Void> deleteActorFromPerformance(@PathVariable Long perId,
                                                           @PathVariable Long actorId) {
        Performance performance = performanceService.getById(perId);
        Actor actor = actorService.getById(actorId);
        performance.setActors(performance.getActors()
                .stream()
                .filter(ac -> ac.getId().equals(actor.getId()))
                .collect(Collectors.toList()));
        performanceService.save(performance);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
