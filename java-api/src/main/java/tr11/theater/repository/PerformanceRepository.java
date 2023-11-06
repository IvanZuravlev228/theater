package tr11.theater.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import tr11.theater.model.Performance;

import java.util.List;

public interface PerformanceRepository extends JpaRepository<Performance, Long> {
}
