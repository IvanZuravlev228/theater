package tr11.theater.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tr11.theater.model.Performance;

public interface PerformanceRepository extends JpaRepository<Performance, Long> {
}
