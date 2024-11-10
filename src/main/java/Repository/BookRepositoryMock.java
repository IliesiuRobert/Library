package Repository;

import Model.Book;

import java.util.*;

public class BookRepositoryMock implements BookRepository {
    private final List<Book> books;

    public BookRepositoryMock() {
        books = new ArrayList<>();
    }

    @Override
    public List<Book> findAll() {
        return books;
    }

    public Optional<Book> findByTitle(String title) {
        return books.parallelStream()
                .filter(it -> it.getTitle().equals(title))
                .findFirst();
    }

    @Override
    public Optional<Book> findById(Long id) {
        return books.parallelStream()
                .filter(it -> it.getId().equals(id))
                .findFirst();
    }

    @Override
    public boolean updateAmount(String title, int newAmount) {
        Optional<Book> bookFind = books.parallelStream()
                .filter(It -> It.getTitle().equals(title))
                .findFirst();

        if (bookFind.isPresent()) {
            Book book = bookFind.get();
            book.setAmount(newAmount);
            return true;
        }

        return false;
    }

    @Override
    public boolean save(Book book) {
        return books.add(book);
    }

    @Override
    public boolean delete(Book book) {
        return books.remove(book);
    }

    @Override
    public void removeAll() {
        books.clear();
    }
}
