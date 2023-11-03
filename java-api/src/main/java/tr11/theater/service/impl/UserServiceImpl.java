package tr11.theater.service.impl;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import tr11.theater.model.User;
import tr11.theater.repository.UserRepository;
import tr11.theater.service.UserService;

@AllArgsConstructor
@Service
public class UserServiceImpl implements UserService {
    private UserRepository userRepository;

    @Override
    public User create(User user) {
        return userRepository.save(user);
    }

    @Override
    public User getByEmail(String email) {
        return userRepository.getUserByEmail(email).orElseThrow(() -> {
            throw new RuntimeException("Can't find user by email: " + email);
        });
    }
}
