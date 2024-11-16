package Launcher;

//Singleton implementare:
// 1) O singura instanta: sa genereze o clasa cu o singura instanta
// 2) Punct de acces global: ofera metoda statica getInstance care permite accesarea acestei instante unice
// 3) Control asupra instantei: foloseste in constructor privat care previne crearea de noi instante
// 4) Lazy initialization: instanta este creata doar atunci cand e nevoie, adica la primul apel al lui getInstance
// 5) Theead safety: ofera siguranta la executie paralela

import Controller.BookController;
import Database.DatabaseConnectionFactory;
import Mapper.BookMapper;
import Mapper.SaleMapper;
import Repository.BookRepository;
import Repository.BookRepositoryMySQL;
import Service.BookService;
import Service.BookServiceImpl;
import View.BookView;
import View.Model.BookDTO;
import View.Model.SaleDTO;
import javafx.stage.Stage;

import java.sql.Connection;
import java.util.*;

public class ComponentFactory {
    private final BookView bookView;
    private final BookController bookController;
    private final BookRepository bookRepository;
    private final BookService bookService;
    private static ComponentFactory instance;

    // Metoda statica ca sa obtinem instanta unica
    public static synchronized ComponentFactory getInstance(Boolean componentsForTest, Stage stage) {
        if (instance == null) {
            instance = new ComponentFactory(componentsForTest, stage);
        }

        return instance;
    }

    // Constructor privat
    private ComponentFactory(Boolean componentsForTest, Stage stage) {
        Connection connection = DatabaseConnectionFactory.getConnectionWrapper(componentsForTest).getConnection();
        this.bookRepository = new BookRepositoryMySQL(connection);

        this.bookService = new BookServiceImpl(bookRepository);

        List<BookDTO> bookDTOs = BookMapper.convertBookListToBookDTOList(this.bookService.findAll());
        List<SaleDTO> saleDTOs = SaleMapper.convertSaleListToSaleDTOList(this.bookService.findAllSale());

        this.bookView = new BookView(stage, bookDTOs, saleDTOs);
        this.bookController = new BookController(bookView, bookService);
    }

    // Getter pentru componentele interne
    public BookView getBookView() {
        return bookView;
    }

    public BookController getBookController() {
        return bookController;
    }

    public BookRepository getBookRepository() {
        return bookRepository;
    }

    public BookService getBookService() {
        return bookService;
    }
}
