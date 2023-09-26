package tr11.theater.controller;

import java.util.List;
import java.util.stream.Collectors;
import com.sun.net.httpserver.HttpsServer;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tr11.theater.dto.actor.ActorRequestDto;
import tr11.theater.dto.actor.ActorResponseDto;
import tr11.theater.model.Actor;
import tr11.theater.service.ActorService;
import tr11.theater.service.mapper.RequestResponseMapper;

@RequiredArgsConstructor
@RestController
@RequestMapping("/actors")
public class ActorController implements DefaultCrudController<ActorRequestDto, ActorResponseDto> {
    private final ActorService actorService;
    private final RequestResponseMapper<Actor, ActorRequestDto, ActorResponseDto> actorMapper;

    @PostMapping
    @Override
    public ResponseEntity<ActorResponseDto> save(@RequestBody ActorRequestDto requestEntity) {
        return new ResponseEntity<>(actorMapper.toDto(actorService.save(actorMapper.toModel(requestEntity))), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @Override
    public ResponseEntity<ActorResponseDto> getById(@PathVariable Long id) {
        return new ResponseEntity<>(actorMapper.toDto(actorService.getById(id)), HttpStatus.OK);
    }

    @GetMapping
    @Override
    public ResponseEntity<List<ActorResponseDto>> getAll() {
        return new ResponseEntity<>(actorService.getAll()
                .stream()
                .map(actorMapper::toDto)
                .collect(Collectors.toList()), HttpStatus.OK);
    }

    @PutMapping("/{prevId}")
    @Override
    public ResponseEntity<ActorResponseDto> update(@PathVariable Long prevId, @RequestBody ActorRequestDto newEntity) {
        return new ResponseEntity<>(actorMapper.toDto(
                actorService.update(prevId, actorMapper.toModel(newEntity))), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @Override
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        actorService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
