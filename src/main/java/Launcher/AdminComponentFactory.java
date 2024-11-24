package Launcher;

import Controller.AdminController;
import Database.DatabaseConnectionFactory;
import Mapper.BookMapper;
import Mapper.SaleMapper;
import Mapper.UserMapper;
import Repository.Book.BookRepository;
import Repository.Book.BookRepositoryMySQL;
import Repository.Security.RightsRolesRepository;
import Repository.Security.RightsRolesRepositoryMySQL;
import Repository.User.UserRepository;
import Repository.User.UserRepositoryMySQL;
import Service.Book.BookService;
import Service.Book.BookServiceImpl;
import Service.User.AuthenticationService;
import Service.User.AuthentificationServiceImpl;
import View.AdminView;
import View.Model.BookDTO;
import View.Model.SaleDTO;
import View.Model.UserDTO;
import javafx.stage.Stage;

import java.sql.Connection;
import java.util.List;

public class AdminComponentFactory {
    private static AdminComponentFactory instance;
    private final AdminView adminView;
    private final AdminController adminController;
    private final AuthenticationService authenticationService;
    private final UserRepository userRepository;
    private final RightsRolesRepository rightsRolesRepository;
    private final BookRepository bookRepository;
    private final BookService bookService;

    public static AdminComponentFactory getInstance(Boolean componentForTests, Stage stage) {
        if (instance == null) {
            synchronized (AdminComponentFactory.class) {
                if (instance == null) {
                    instance = new AdminComponentFactory(componentForTests, stage);
                }
            }
        }

        return instance;
    }

    private AdminComponentFactory(Boolean componentForTests, Stage stage) {
        Connection connection = DatabaseConnectionFactory.getConnectionWrapper(componentForTests).getConnection();

        this.bookRepository = new BookRepositoryMySQL(connection);
        this.bookService = new BookServiceImpl(bookRepository);

        this.rightsRolesRepository = new RightsRolesRepositoryMySQL(connection);
        this.userRepository = new UserRepositoryMySQL(connection, rightsRolesRepository);
        this.authenticationService = new AuthentificationServiceImpl(userRepository, rightsRolesRepository);

        List<BookDTO> bookDTOs = BookMapper.convertBookListToBookDTOList(this.bookService.findAll());
        List<SaleDTO> saleDTOs = SaleMapper.convertSaleListToSaleDTOList(this.bookService.findAllSale());
        List<UserDTO> userDTOs = UserMapper.convertUserListToUserDTOList(this.userRepository.findAll());

        this.adminView = new AdminView(stage, bookDTOs, saleDTOs, userDTOs);
        this.adminController = new AdminController(adminView, bookService, authenticationService);
    }

    public AdminView getAdminView() {
        return adminView;
    }

    public AdminController getAdminController() {
        return adminController;
    }

    public AuthenticationService getAuthenticationService() {
        return authenticationService;
    }

    public UserRepository getUserRepository() {
        return userRepository;
    }

    public RightsRolesRepository getRightsRolesRepository() {
        return rightsRolesRepository;
    }

    public BookRepository getBookRepository() {
        return bookRepository;
    }

    public BookService getBookService() {
        return bookService;
    }
}
