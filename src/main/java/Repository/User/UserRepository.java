package Repository.User;

import Model.User;
import Model.Validator.Notification;

import java.util.*;

public interface UserRepository {
    List<User> findAll();

    Notification<User> findByUsernameAndPassword(String username, String password);

    boolean save(User user);

    void removeAll();

    boolean deleteUser(User user);

    boolean existsByUsername(String username);
}
