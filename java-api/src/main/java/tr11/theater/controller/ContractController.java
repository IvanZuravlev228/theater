package tr11.theater.controller;

import java.util.List;
import java.util.stream.Collectors;

import jakarta.validation.Valid;
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
import tr11.theater.dto.contract.ContractRequestDto;
import tr11.theater.dto.contract.ContractResponseDto;
import tr11.theater.model.Contract;
import tr11.theater.service.ContractService;
import tr11.theater.service.mapper.RequestResponseMapper;

@RequiredArgsConstructor
@RestController
@RequestMapping("/contracts")
public class ContractController {
    private final ContractService contractService;
    private final RequestResponseMapper<Contract, ContractRequestDto, ContractResponseDto> contractMapper;

    @GetMapping("/{id}")
    public ResponseEntity<ContractResponseDto> getById(@PathVariable Long id) {
        return new ResponseEntity<>(contractMapper.toDto(contractService.getById(id)), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<ContractResponseDto>> getAll() {
        return new ResponseEntity<>(contractService.getAll()
                .stream()
                .map(contractMapper::toDto)
                .collect(Collectors.toList()), HttpStatus.OK);
    }

    @GetMapping("/actor/{actorId}/performance/{perId}")
    public ResponseEntity<ContractResponseDto> getByActorAndPerformanceId(@PathVariable Long actorId,
                                                                          @PathVariable Long perId) {
        return new ResponseEntity<>(contractMapper.toDto(contractService.getByActorAndPerformanceId(actorId, perId)), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<ContractResponseDto> save(@RequestBody @Valid ContractRequestDto requestEntity) {
        return new ResponseEntity<>(contractMapper.toDto(
                contractService.save(contractMapper.toModel(requestEntity))), HttpStatus.CREATED);
    }

    @PutMapping("/{prevId}")
    public ResponseEntity<ContractResponseDto> update(@PathVariable Long prevId,
                                                      @RequestBody @Valid ContractRequestDto newEntity) {
        return new ResponseEntity<>(contractMapper.toDto(
                contractService.update(prevId, contractMapper.toModel(newEntity))), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        contractService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
