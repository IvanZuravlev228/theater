package tr11.theater.service.impl;

import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import tr11.theater.dto.actor.ActorRequestDto;
import tr11.theater.dto.actor.ActorResponseDto;
import tr11.theater.exception.NotFoundException;
import tr11.theater.model.Actor;
import tr11.theater.repository.ActorRepository;
import tr11.theater.service.ActorService;
import tr11.theater.service.mapper.RequestResponseMapper;

@Service
@RequiredArgsConstructor
public class ActorServiceImpl implements ActorService {
    private final ActorRepository actorRepository;

    @Override
    public Actor save(Actor actor) {
        return actorRepository.save(actor);
    }

    @Override
    public Actor getById(Long id) {
        return actorRepository.findById(id).orElseThrow(() ->
                new NotFoundException("Can't find actor by id: " + id));
    }

    @Override
    public List<Actor> getAll(Pageable pageable) {
        return actorRepository.findAll(pageable)
                .stream()
                .collect(Collectors.toList());
    }

    @Override
    public Actor update(Long previousActorId, Actor actorForUpdate) {
        Actor prevActor = getById(previousActorId);
        actorForUpdate.setId(prevActor.getId());
        return actorRepository.save(actorForUpdate);
    }

    @Override
    public void delete(Long id) {
        actorRepository.deleteById(id);
    }

    @Override
    public List<Actor> findAllWithContractWithPerformance(Long id) {
        return actorRepository.findAllWithContractWithPerformance(id);
    }

    @Override
    public List<Actor> findAllWithoutContractWithPerformance(Long id) {
        return actorRepository.findAllWithoutContractWithPerformance(id);
    }
}
