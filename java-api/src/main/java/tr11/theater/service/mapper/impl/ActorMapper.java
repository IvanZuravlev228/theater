package tr11.theater.service.mapper.impl;

import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import tr11.theater.dto.actor.ActorRequestDto;
import tr11.theater.dto.actor.ActorResponseDto;
import tr11.theater.model.Actor;
import tr11.theater.model.Prizes;
import tr11.theater.service.PrizesService;
import tr11.theater.service.mapper.RequestResponseMapper;

@Component
@RequiredArgsConstructor
public class ActorMapper implements RequestResponseMapper<Actor, ActorRequestDto, ActorResponseDto> {
    private final PrizesService prizesService;

    @Override
    public Actor toModel(ActorRequestDto dto) {
        Actor actor = new Actor();
        actor.setName(dto.getName());
        actor.setLastname(dto.getLastname());
        actor.setExperience(dto.getExperience());
        actor.setPrizes(dto.getPrizeIds()
                .stream()
                .map(prizesService::getById)
                .collect(Collectors.toList()));
        return actor;
    }

    @Override
    public ActorResponseDto toDto(Actor model) {
        ActorResponseDto dto = new ActorResponseDto();
        dto.setId(model.getId());
        dto.setName(model.getName());
        dto.setLastname(model.getLastname());
        dto.setExperience(model.getExperience());
        dto.setPrizeIds(model.getPrizes()
                .stream()
                .map(Prizes::getId)
                .collect(Collectors.toList()));
        return dto;
    }
}
