package tr11.theater.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import tr11.theater.model.Performance;

import java.util.List;

public interface PerformanceRepository extends JpaRepository<Performance, Long> {
    @Query(value = "SELECT p.* " +
            "FROM actors a " +
            "JOIN performances_actors pa ON a.id = pa.actors_id " +
            "LEFT JOIN contracts c ON a.id = c.actor_id AND pa.performances_id = c.performance_id " +
            "join performances p on pa.performances_id = p.id " +
            "WHERE c.id IS NULL", nativeQuery = true)
    List<Performance> findAllLoading();
}
