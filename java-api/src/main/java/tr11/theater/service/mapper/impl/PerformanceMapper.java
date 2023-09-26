package tr11.theater.service.mapper.impl;

import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import tr11.theater.dto.performance.PerformanceRequestDto;
import tr11.theater.dto.performance.PerformancesResponseDto;
import tr11.theater.dto.prizes.PrizesRequestDto;
import tr11.theater.model.Actor;
import tr11.theater.model.Performance;
import tr11.theater.service.ActorService;
import tr11.theater.service.mapper.RequestResponseMapper;

@Component
@RequiredArgsConstructor
public class PerformanceMapper implements RequestResponseMapper<Performance, PerformanceRequestDto, PerformancesResponseDto> {
    private final ActorService actorService;

    @Override
    public Performance toModel(PerformanceRequestDto dto) {
        Performance model = new Performance();
        model.setName(dto.getName());
        model.setDescription(dto.getDescription());
        model.setBudget(dto.getBudget());
        model.setActors(dto.getActorIds()
                .stream()
                .map(actorService::getById)
                .collect(Collectors.toList()));
        return model;
    }

    @Override
    public PerformancesResponseDto toDto(Performance model) {
        PerformancesResponseDto dto = new PerformancesResponseDto();
        dto.setId(model.getId());
        dto.setName(model.getName());
        dto.setDescription(model.getDescription());
        dto.setBudget(model.getBudget());
        dto.setActorIds(model.getActors()
                .stream()
                .map(Actor::getId)
                .collect(Collectors.toList()));
        return dto;
    }
}
