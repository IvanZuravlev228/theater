package tr11.theater.service.impl;

import java.util.NoSuchElementException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import tr11.theater.exception.InvalidCredentialsException;
import tr11.theater.model.User;
import tr11.theater.repository.UserRepository;
import tr11.theater.service.AuthenticationService;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public User register(User user) {
        user.setRole(User.Role.ADMIN);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    @Override
    public User login(String login, String password) throws RuntimeException {
        User user = userRepository.getUserByEmail(login).orElseThrow(() ->
                new NoSuchElementException("Can't find user by login: " + login));
        if (!passwordEncoder.matches(password, user.getPassword())) {
            throw new InvalidCredentialsException("Incorrect username or password");
        }
        return user;
    }
}
