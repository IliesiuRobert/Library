package Repository;

import Model.Book;

import java.util.*;

public interface BookRepository {
    List<Book> findAll();

    Optional<Book> findByTitle(String title);

    Optional<Book> findById(Long id);

    boolean updateAmount(String title, int amount);

    boolean save(Book book);

    boolean delete(Book book);

    void removeAll();
}
