package tr11.theater.controller;

import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tr11.theater.dto.performance.PerformanceRequestDto;
import tr11.theater.dto.performance.PerformancesResponseDto;
import tr11.theater.model.Performance;
import tr11.theater.service.PerformanceService;
import tr11.theater.service.PrizesService;
import tr11.theater.service.mapper.RequestResponseMapper;

@RequiredArgsConstructor
@RestController
@RequestMapping("/performances")
public class PerformanceController implements DefaultCrudController<PerformanceRequestDto, PerformancesResponseDto> {
    private final PerformanceService performanceService;
    private final RequestResponseMapper<Performance, PerformanceRequestDto, PerformancesResponseDto> performanceMapper;

    @PostMapping
    @Override
    public ResponseEntity<PerformancesResponseDto> save(@RequestBody PerformanceRequestDto requestEntity) {
        return new ResponseEntity<>(performanceMapper.toDto(
                performanceService.save(performanceMapper.toModel(requestEntity))), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @Override
    public ResponseEntity<PerformancesResponseDto> getById(@PathVariable Long id) {
        return new ResponseEntity<>(performanceMapper.toDto(performanceService.getById(id)), HttpStatus.OK);
    }

    @GetMapping
    @Override
    public ResponseEntity<List<PerformancesResponseDto>> getAll() {
        return new ResponseEntity<>(performanceService.getAll()
                .stream()
                .map(performanceMapper::toDto)
                .collect(Collectors.toList()), HttpStatus.OK);
    }

    @PutMapping("/{prevId}")
    @Override
    public ResponseEntity<PerformancesResponseDto> update(@PathVariable Long prevId,
                                                          @RequestBody PerformanceRequestDto newEntity) {
        return new ResponseEntity<>(performanceMapper.toDto(
                performanceService.update(prevId, performanceMapper.toModel(newEntity))), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Void> deleteById(Long id) {
        performanceService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
