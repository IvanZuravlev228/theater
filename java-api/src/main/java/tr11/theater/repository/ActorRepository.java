package tr11.theater.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import tr11.theater.model.Actor;

public interface ActorRepository extends JpaRepository<Actor, Long> {
    @Query(value = "SELECT a.* " +
            "FROM actors a " +
            "JOIN performances_actors pa ON a.id = pa.actors_id " +
            "JOIN performances p ON pa.performances_id = p.id " +
            "WHERE p.id = :performanceId", nativeQuery = true)
    List<Actor> findActorsByPerformanceId(@Param("performanceId") Long performanceId);

    @Query(value = "SELECT DISTINCT a.* " +
            "FROM actors a " +
            "JOIN performances_actors pa ON a.id = pa.actors_id " +
            "LEFT JOIN contracts c ON a.id = c.actor_id AND pa.performances_id = c.performance_id " +
            "WHERE c.id IS NULL;", nativeQuery = true)
    List<Actor> findAllWithoutContract();

    @Query(value = "SELECT a.* " +
            "FROM actors a " +
            "JOIN performances_actors pa ON a.id = pa.actors_id " +
            "LEFT JOIN contracts c ON a.id = c.actor_id AND pa.performances_id = c.performance_id " +
            "WHERE pa.performances_id = :performanceId AND c.id IS NULL", nativeQuery = true)
    List<Actor> findAllWithoutContractWithPerformance(@Param("performanceId") Long id);

    @Query(value = "SELECT a.* " +
            "FROM actors a " +
            "JOIN performances_actors pa ON a.id = pa.actors_id " +
            "LEFT JOIN contracts c ON a.id = c.actor_id AND pa.performances_id = c.performance_id " +
            "WHERE pa.performances_id = :performanceId AND c.id IS not NULL", nativeQuery = true)
    List<Actor> findAllWithContractWithPerformance(@Param("performanceId") Long id);
}
