package Service.User;

import Model.Builder.UserBuilder;
import Model.Role;
import Model.User;
import Model.Validator.Notification;
import Model.Validator.UserValidator;
import Repository.Security.RightsRolesRepository;
import Repository.User.UserRepository;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.util.Collections;

import static Database.Constants.Roles.*;

public class AuthentificationServiceImpl implements AuthenticationService {
    private final UserRepository userRepository;

    private final RightsRolesRepository rightsRolesRepository;

    public AuthentificationServiceImpl(UserRepository userRepository, RightsRolesRepository rightsRolesRepository) {
        this.userRepository = userRepository;
        this.rightsRolesRepository = rightsRolesRepository;
    }

    @Override
    public Notification<Boolean> register(String username, String password) {
        Notification<Boolean> userRegisterNotification = new Notification<>();

        if (userRepository.existsByUsername(username)) {
            userRegisterNotification.addError("An account with this username already exists!");
            userRegisterNotification.setResult(Boolean.FALSE);
            return userRegisterNotification;
        }

        Role customerRole = rightsRolesRepository.findRoleByTitle(CUSTOMER);

        User user = new UserBuilder()
                .setUsername(username)
                .setPassword(password)
                .setRoles(Collections.singletonList(customerRole))
                .build();

        UserValidator userValidator = new UserValidator(user);
        boolean userValid = userValidator.validate();
        //Notification<Boolean> userRegisterNotification = new Notification<>();

        if (!userValid){
            userValidator.getErrors().forEach(userRegisterNotification::addError);
            userRegisterNotification.setResult(Boolean.FALSE);
        } else {
            user.setPassword(hashPassword(password));
            userRegisterNotification.setResult(userRepository.save(user));
        }

        return userRegisterNotification;
    }

    @Override
    public Notification<Boolean> registerEmployee(String username, String password){
        Notification<Boolean> userRegisterNotification = new Notification<>();
        if(userRepository.existsByUsername(username)) {
            userRegisterNotification.addError(username + " already exists");
            userRegisterNotification.setResult(Boolean.FALSE);
            return userRegisterNotification;
        }

        Role role = rightsRolesRepository.findRoleByTitle(EMPLOYEE);

        User user = new UserBuilder()
                .setUsername(username)
                .setPassword(password)
                .setRoles(Collections.singletonList(role))
                .build();

        UserValidator userValidator = new UserValidator(user);

        boolean userValid = userValidator.validate();


        if (!userValid){
            userValidator.getErrors().forEach(userRegisterNotification::addError);
            userRegisterNotification.setResult(Boolean.FALSE);
        } else {
            user.setPassword(hashPassword(password));
            userRegisterNotification.setResult(userRepository.save(user));
        }

        return userRegisterNotification;

    }

    @Override
    public Notification<User> login(String username, String password) {
        return userRepository.findByUsernameAndPassword(username, hashPassword(password));
    }

    @Override
    public boolean logout(User user) {
        return false;
    }

    @Override
    public boolean deleteUser(User user) {
        return userRepository.deleteUser(user);
    }

    private String hashPassword(String password) {
        try {
            // Sercured Hash Algorithm - 256
            // 1 byte = 8 biți
            // 1 byte = 1 char
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(password.getBytes(StandardCharsets.UTF_8));
            StringBuilder hexString = new StringBuilder();

            for (byte b : hash) {
                String hex = Integer.toHexString(0xff & b);
                if (hex.length() == 1) hexString.append('0');
                hexString.append(hex);
            }

            return hexString.toString();
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }
}
