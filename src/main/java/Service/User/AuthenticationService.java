package Service.User;

import Model.User;
import Model.Validator.Notification;

public interface AuthenticationService {
    Notification<Boolean> register (String username, String password);

    Notification<Boolean> registerEmployee (String username, String password);

    Notification<User> login (String username, String password);

    boolean logout (User user);

    boolean deleteUser (User user);
}
