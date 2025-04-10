package Controller;

import Launcher.AdminComponentFactory;
import Launcher.EmployeeComponentFactory;
import Launcher.LoginComponentFactory;
import Model.Session;
import Model.User;
import Model.Validator.Notification;
import Service.User.AuthenticationService;
import View.LoginView;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

import static Database.Constants.Roles.ADMINISTRATOR;

public class LoginController {

    private final LoginView loginView;
    private final AuthenticationService authenticationService;


    public LoginController(LoginView loginView, AuthenticationService authenticationService) {
        this.loginView = loginView;
        this.authenticationService = authenticationService;

        this.loginView.addLoginButtonListener(new LoginButtonListener());
        this.loginView.addRegisterButtonListener(new RegisterButtonListener());
    }

    private class LoginButtonListener implements EventHandler<ActionEvent> {

        @Override
        public void handle(javafx.event.ActionEvent event) {
            String username = loginView.getUsername();
            String password = loginView.getPassword();

            Notification<User> loginNotification = authenticationService.login(username, password);

            if (loginNotification.hasErrors()){
                loginView.setActionTargetText(loginNotification.getFormattedErrors());
            } else {
                User loggedIn = loginNotification.getResult();
                loginView.setActionTargetText("LogIn Successfull!");

                Session.setLoggedInUser(loggedIn.getId());

                boolean isAdmin = loggedIn.getRoles().stream()
                        .anyMatch(role -> ADMINISTRATOR.equals(role.getRole()));

                if (isAdmin) {
                    AdminComponentFactory.getInstance(LoginComponentFactory.getComponentsForTests(), LoginComponentFactory.getStage());
                } else {
                    EmployeeComponentFactory.getInstance(LoginComponentFactory.getComponentsForTests(), LoginComponentFactory.getStage());
                }
            }
        }
    }

    private class RegisterButtonListener implements EventHandler<ActionEvent> {

        @Override
        public void handle(ActionEvent event) {
            String username = loginView.getUsername();
            String password = loginView.getPassword();

            Notification<Boolean> registerNotification = authenticationService.register(username, password);

            if (registerNotification.hasErrors()) {
                loginView.setActionTargetText(registerNotification.getFormattedErrors());
            } else {
                loginView.setActionTargetText("Register successful!");
            }
        }
    }
}
