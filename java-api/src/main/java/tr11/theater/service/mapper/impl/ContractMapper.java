package tr11.theater.service.mapper.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import tr11.theater.dto.contract.ContractRequestDto;
import tr11.theater.dto.contract.ContractResponseDto;
import tr11.theater.model.Contract;
import tr11.theater.service.ActorService;
import tr11.theater.service.PerformanceService;
import tr11.theater.service.mapper.RequestResponseMapper;

@Component
@RequiredArgsConstructor
public class ContractMapper implements RequestResponseMapper<Contract, ContractRequestDto, ContractResponseDto> {
    private final ActorService actorService;
    private final PerformanceService performanceService;

    @Override
    public Contract toModel(ContractRequestDto dto) {
        Contract contract = new Contract();
        contract.setActor(actorService.getById(dto.getActorId()));
        contract.setSalary(dto.getSalary());
        contract.setRole(dto.getRole());
        contract.setPerformance(performanceService.getById(dto.getPerformanceId()));
        return contract;
    }

    @Override
    public ContractResponseDto toDto(Contract model) {
        ContractResponseDto dto = new ContractResponseDto();
        dto.setId(model.getId());
        dto.setSalary(model.getSalary());
        dto.setActorId(model.getActor().getId());
        dto.setRole(model.getRole());
        dto.setPerformanceId(model.getPerformance().getId());
        return dto;
    }
}
