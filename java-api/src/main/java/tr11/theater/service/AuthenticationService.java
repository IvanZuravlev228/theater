package tr11.theater.service;

import tr11.theater.model.User;

public interface AuthenticationService {
    User register(User user);

    User login(String login, String password) throws RuntimeException;
}