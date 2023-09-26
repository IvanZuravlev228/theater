package tr11.theater.service.mapper.impl;

import org.springframework.stereotype.Component;
import tr11.theater.dto.prizes.PrizesRequestDto;
import tr11.theater.dto.prizes.PrizesResponseDto;
import tr11.theater.model.Prizes;
import tr11.theater.service.mapper.RequestResponseMapper;

@Component
public class PrizesMapper implements RequestResponseMapper<Prizes, PrizesRequestDto, PrizesResponseDto> {
    @Override
    public Prizes toModel(PrizesRequestDto dto) {
        Prizes model = new Prizes();
        model.setName(dto.getName());
        model.setCoefficient(dto.getCoefficient());
        return model;
    }

    @Override
    public PrizesResponseDto toDto(Prizes model) {
        PrizesResponseDto dto = new PrizesResponseDto();
        dto.setId(model.getId());
        dto.setName(model.getName());
        dto.setCoefficient(model.getCoefficient());
        return dto;
    }
}
