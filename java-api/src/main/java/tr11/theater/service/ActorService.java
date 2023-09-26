package tr11.theater.service;

import tr11.theater.model.Actor;

import java.util.List;

public interface ActorService {
    Actor save(Actor actor);

    Actor getById(Long id);

    List<Actor> getAll();

    Actor update(Long prevActorId, Actor actorForUpdate);

    void delete(Long id);
}
