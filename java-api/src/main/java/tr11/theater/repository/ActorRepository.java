package tr11.theater.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tr11.theater.model.Actor;

public interface ActorRepository extends JpaRepository<Actor, Long> {
}
