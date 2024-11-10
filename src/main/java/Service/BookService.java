package Service;

import Model.Book;

import java.util.*;

public interface BookService {
    List<Book> findAll();

    Book findByTitle(String title);

    Book findById(Long id);

    boolean updateAmount(String title, int amount);

    boolean save(Book book);

    boolean delete(Book book);

    int getAgeOfBook(Long id);
}
