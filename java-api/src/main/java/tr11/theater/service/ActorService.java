package tr11.theater.service;

import org.springframework.data.domain.Pageable;
import tr11.theater.model.Actor;

import java.util.List;

public interface ActorService {
    Actor save(Actor actor);

    Actor getById(Long id);

    List<Actor> getAll(Pageable pageable);

    Actor update(Long previousActorId, Actor actorForUpdate);

    void delete(Long id);

    List<Actor> findAllWithContractWithPerformance(Long id);

    List<Actor> findAllWithoutContractWithPerformance(Long id);
}
