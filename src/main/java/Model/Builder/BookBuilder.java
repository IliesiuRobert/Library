package Model.Builder;

import Model.Book;

import java.time.LocalDate;

public class BookBuilder {
    private Book book;

    public BookBuilder() {
        book = new Book();
    }

    public BookBuilder setId(Long id) {
        book.setId(id);
        return this;
    }

    public BookBuilder setTitle(String title) {
        book.setTitle(title);
        return this;
    }

    public BookBuilder setAuthor(String author) {
        book.setAuthor(author);
        return this;
    }

    public BookBuilder setPublishedDate(LocalDate publishedDate) {
        book.setPublishedDate(publishedDate);
        return this;
    }

    public BookBuilder setAmount(int amount) {
        book.setAmount(amount);
        return this;
    }

    public BookBuilder setPrice(double price) {
        book.setPrice(price);
        return this;
    }

    public Book build() {
        return book;
    }
}
