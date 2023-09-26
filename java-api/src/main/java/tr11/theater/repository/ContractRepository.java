package tr11.theater.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import tr11.theater.model.Contract;

public interface ContractRepository extends JpaRepository<Contract, Long> {
    Optional<Contract> findByActorId(Long id);
}
