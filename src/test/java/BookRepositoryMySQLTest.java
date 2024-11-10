import Database.DatabaseConnectionFactory;
import Model.Book;
import Model.Builder.BookBuilder;
import Repository.BookRepository;
import Repository.BookRepositoryMySQL;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.sql.*;
import java.time.LocalDate;
import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class BookRepositoryMySQLTest {
    private static BookRepository bookRepository;

    @BeforeAll
    public static void setupClass() {
        Connection connection = DatabaseConnectionFactory.getConnectionWrapper(false).getConnection();
        bookRepository = new BookRepositoryMySQL(connection);
    }

    @Test
    public void removeAll() {
        bookRepository.removeAll();
        List<Book> books = bookRepository.findAll();
        assertEquals(0, books.size());
    }

    @Test
    public void save() {
        assertTrue(bookRepository.save(new BookBuilder()
                .setAuthor("Ion Slavici")
                .setTitle("Moara cu nororc")
                .setPublishedDate(LocalDate.of(1950, 2, 10))
                .setAmount(20)
                .setPrice(20.99)
                .build()));
    }

    @Test
    public void delete() {
        Book book = new BookBuilder()
                .setAuthor("Mihai Eminescu")
                .setTitle("Luceafarul")
                .setPublishedDate(LocalDate.of(1883, 1, 15))
                .setAmount(30)
                .setPrice(11.99)
                .build();

        bookRepository.save(book);

        assertEquals(1, bookRepository.findAll().size());
        bookRepository.delete(book);

        assertEquals(0, bookRepository.findAll().size());
    }
}
