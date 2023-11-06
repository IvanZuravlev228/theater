package tr11.theater.controller;

import java.util.List;
import java.util.stream.Collectors;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tr11.theater.dto.actor.ActorRequestDto;
import tr11.theater.dto.actor.ActorResponseDto;
import tr11.theater.model.Actor;
import tr11.theater.service.ActorService;
import tr11.theater.service.mapper.RequestResponseMapper;

@RequiredArgsConstructor
@RestController
@RequestMapping("/actors")
public class ActorController {
    private final ActorService actorService;
    private final RequestResponseMapper<Actor, ActorRequestDto, ActorResponseDto> actorMapper;

    @GetMapping("/{id}")
    public ResponseEntity<ActorResponseDto> getById(@PathVariable Long id) {
        return new ResponseEntity<>(actorMapper.toDto(actorService.getById(id)), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<ActorResponseDto>> getAll(Pageable pageable) {
        return new ResponseEntity<>(actorService.getAll(pageable)
                .stream()
                .map(actorMapper::toDto)
                .collect(Collectors.toList()), HttpStatus.OK);
    }

    @GetMapping("/performance/{id}")
    public ResponseEntity<List<ActorResponseDto>> getAllByPerformanceId(@PathVariable Long id,
                                                                        @RequestParam(defaultValue = "true") Boolean hasContracts) {
        List<Actor> actors = hasContracts ? actorService.findAllWithContractWithPerformance(id) : actorService.findAllWithoutContractWithPerformance(id);
        return new ResponseEntity<>(actors
                .stream()
                .map(actorMapper::toDto)
                .collect(Collectors.toList()), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<ActorResponseDto> save(@RequestBody @Valid ActorRequestDto requestEntity) {
        return new ResponseEntity<>(actorMapper.toDto(actorService.save(actorMapper.toModel(requestEntity))), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ActorResponseDto> update(@PathVariable Long id,
                                                   @RequestBody @Valid ActorRequestDto newEntity) {
        return new ResponseEntity<>(actorMapper.toDto(actorService.update(id, actorMapper.toModel(newEntity))), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        actorService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
