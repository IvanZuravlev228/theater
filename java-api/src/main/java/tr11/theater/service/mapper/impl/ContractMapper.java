package tr11.theater.service.mapper.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import tr11.theater.dto.contract.ContractRequestDto;
import tr11.theater.dto.contract.ContractResponseDto;
import tr11.theater.model.Contract;
import tr11.theater.service.ActorService;
import tr11.theater.service.mapper.RequestResponseMapper;

@Component
@RequiredArgsConstructor
public class ContractMapper implements RequestResponseMapper<Contract, ContractRequestDto, ContractResponseDto> {
    private final ActorService actorService;

    @Override
    public Contract toModel(ContractRequestDto dto) {
        Contract contract = new Contract();
        contract.setActor(actorService.getById(dto.getActorId()));
        contract.setSalary(dto.getSalary());
        return contract;
    }

    @Override
    public ContractResponseDto toDto(Contract model) {
        ContractResponseDto dto = new ContractResponseDto();
        dto.setId(model.getId());
        dto.setSalary(model.getSalary());
        dto.setActorId(model.getActor().getId());
        return dto;
    }
}
