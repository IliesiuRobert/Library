package Service;

import Model.Book;
import Model.Sale;

import java.util.*;

public interface BookService {
    List<Book> findAll();

    List<Sale> findAllSale();

    boolean ifBookIsPresent(String title);

    Book findByTitle(String title);

    Book findById(Long id);

    boolean updateAmount(String title, int amount, int quantity, double price);

    boolean save(Book book);

    boolean delete(Book book);

    int getAgeOfBook(Long id);

    //boolean sellBook(String title, int amount, double price);
}
