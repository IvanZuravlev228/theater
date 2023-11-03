package tr11.theater.service;

import tr11.theater.model.User;

public interface UserService {
    User create(User user);

    User getByEmail(String email);
}
