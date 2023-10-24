package tr11.theater.controller;

import java.util.List;
import java.util.stream.Collectors;
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
import tr11.theater.dto.prizes.PrizesIdsRequestDto;
import tr11.theater.dto.prizes.PrizesRequestDto;
import tr11.theater.dto.prizes.PrizesResponseDto;
import tr11.theater.model.Prizes;
import tr11.theater.service.PrizesService;
import tr11.theater.service.mapper.RequestResponseMapper;

@RequiredArgsConstructor
@RestController
@RequestMapping("/prizes")
public class PrizesController {
    private final PrizesService prizesService;
    private final RequestResponseMapper<Prizes, PrizesRequestDto, PrizesResponseDto> prizesMapper;

    @PostMapping
    public ResponseEntity<PrizesResponseDto> save(@RequestBody PrizesRequestDto requestEntity) {
        return new ResponseEntity<>(prizesMapper.toDto(
                prizesService.save(prizesMapper.toModel(requestEntity))), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PrizesResponseDto> getById(@PathVariable Long id) {
        return new ResponseEntity<>(prizesMapper.toDto(prizesService.getById(id)), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<PrizesResponseDto>> getAll() {
        return new ResponseEntity<>(prizesService.getAll()
                .stream()
                .map(prizesMapper::toDto)
                .collect(Collectors.toList()), HttpStatus.OK);
    }

    @PutMapping("/{prevId}")
    public ResponseEntity<PrizesResponseDto> update(@PathVariable Long prevId,
                                                    @RequestBody PrizesRequestDto newEntity) {
        return new ResponseEntity<>(prizesMapper.toDto(
                prizesService.update(prevId, prizesMapper.toModel(newEntity))), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        prizesService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PostMapping("/by-actor")
    public ResponseEntity<List<PrizesResponseDto>> getByActorId(@RequestBody PrizesIdsRequestDto idsDto) {
        return new ResponseEntity<>(prizesService.getAllByIds(idsDto.getPrizesIds())
                .stream()
                .map(prizesMapper::toDto)
                .collect(Collectors.toList()), HttpStatus.OK);
    }
}
