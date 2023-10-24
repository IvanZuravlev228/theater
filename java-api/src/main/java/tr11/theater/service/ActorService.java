package tr11.theater.service;

import org.springframework.data.domain.Pageable;
import tr11.theater.model.Actor;

import java.util.List;

public interface ActorService {
    Actor save(Actor actor);

    Actor getById(Long id);

    List<Actor> getAll(Pageable pageable);

//    List<Actor> getAllWithoutContract(Pageable pageable);

    Actor update(Long prevActorId, Actor actorForUpdate);

    void delete(Long id);

    List<Actor> getAllByPerformanceId(Long perId);

    List<Actor> findAllWithContractWithPerformance(Long id);

    List<Actor> findAllWithoutContractWithPerformance(Long id);
}
