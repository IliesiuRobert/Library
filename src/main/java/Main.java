import Database.DatabaseConnectionFactory;
import Model.Book;
import Model.Builder.BookBuilder;
import Repository.Book.BookRepository;
import Repository.Book.BookRepositoryCacheDecorator;
import Repository.Book.BookRepositoryMySQL;
import Repository.Book.Cache;
import Service.Book.BookService;
import Service.Book.BookServiceImpl;

import java.sql.Connection;
import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {
//        System.out.println("Hello World!\n");

        Book book = new BookBuilder()
                .setTitle("Ion")
                .setAuthor("Liviu Rebreanu")
                .setPublishedDate(LocalDate.of(1918, 8, 20))
                .build();

//        System.out.println(book);
//
//        BookRepository bookRepository = new BookRepositoryMock();
//        bookRepository.save(book);
//        bookRepository.save(new BookBuilder()
//                .setAuthor("Ion Slavici")
//                .setTitle("Moara cu nororc")
//                .setPublicationDate(LocalDate.of(1950, 2, 10)).build());
//
//        System.out.println(bookRepository.findAll());
//
//        bookRepository.removeAll();
//        System.out.println(bookRepository.findAll());

//        Connection connection = DatabaseConnectionFactory.getConnectionWrapper(false).getConnection();
//        BookRepository bookRepository = new BookRepositoryMySQL(connection);
//        BookService bookService = new BookServiceImpl(bookRepository);
//
//        bookService.save(book);
//        System.out.println(bookService.findAll());
//        Book bookMoaraCuNoroc = new BookBuilder().setAuthor("Ion Slavici").setTitle("Moara cu nororc").setPublishedDate(LocalDate.of(1950, 2, 10)).build();
//        bookService.save(bookMoaraCuNoroc);
//        System.out.println(bookService.findAll());
//
//        bookService.delete(bookMoaraCuNoroc);
//        bookService.save(book);
//        bookService.delete(book);
//        System.out.println(bookService.findAll());
//
        Connection connection = DatabaseConnectionFactory.getConnectionWrapper(false).getConnection();
        BookRepository bookRepository = new BookRepositoryCacheDecorator(new BookRepositoryMySQL(connection), new Cache<>(), new Cache<>());
        BookService bookService = new BookServiceImpl(bookRepository);

        bookService.save(book);
        System.out.println(bookService.findAll());
        System.out.println(bookService.findAll());
    }
}
