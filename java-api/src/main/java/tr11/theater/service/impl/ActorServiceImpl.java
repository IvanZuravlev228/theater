package tr11.theater.service.impl;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import tr11.theater.model.Actor;
import tr11.theater.repository.ActorRepository;
import tr11.theater.service.ActorService;

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
            new RuntimeException("Can't find actor by id: " + id));
    }

    @Override
    public List<Actor> getAll() {
        return actorRepository.findAll();
    }

    @Override
    public List<Actor> getAllWithoutContract() {
        return actorRepository.findAllWithoutContract();
    }

    @Override
    public Actor update(Long prevActorId, Actor actorForUpdate) {
        Actor prevActor = getById(prevActorId);
        actorForUpdate.setId(prevActor.getId());
        return actorRepository.save(actorForUpdate);
    }

    @Override
    public void delete(Long id) {
        actorRepository.deleteById(id);
    }

    @Override
    public List<Actor> getAllByPerformanceId(Long perId) {
        return actorRepository.findActorsByPerformanceId(perId);
    }
}
