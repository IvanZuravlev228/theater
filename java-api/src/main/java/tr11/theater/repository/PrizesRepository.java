package tr11.theater.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tr11.theater.model.Prizes;

public interface PrizesRepository extends JpaRepository<Prizes, Long> {
}
