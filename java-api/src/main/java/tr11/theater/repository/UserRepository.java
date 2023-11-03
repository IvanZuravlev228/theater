package tr11.theater.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import tr11.theater.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> getUserByEmail(String email);
}
