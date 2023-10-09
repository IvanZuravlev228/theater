package tr11.theater.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import tr11.theater.model.Contract;

public interface ContractRepository extends JpaRepository<Contract, Long> {
    @Query(value = "select * from contracts where actor_id = :actorId and performance_id = :perId", nativeQuery = true)
    Optional<Contract> findByActorAndPerformanceId(@Param("actorId") Long actorId,
                                                   @Param("perId") Long perId);
}
