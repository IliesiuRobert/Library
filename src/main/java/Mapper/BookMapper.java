package Mapper;

import Model.Book;
import Model.Builder.BookBuilder;
import View.Model.BookDTO;
import View.Model.Builder.BookDTOBuilder;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

public class BookMapper {
    public static BookDTO convertBookToBookDTO(Book book) {
        return new BookDTOBuilder().setAuthor(book.getAuthor()).setTitle(book.getTitle()).setAmount(book.getAmount()).setPrice(book.getPrice()).build();
    }

    public static Book convertBookDTOToBook(BookDTO bookDTO) {
        return new BookBuilder().setAuthor(bookDTO.getAuthor()).setTitle(bookDTO.getTitle()).setPublishedDate(LocalDate.of(2010, 10, 02)).setAmount(bookDTO.getAmount()).setPrice(bookDTO.getPrice()).build();
    }

    public static List<Book> convertBookDTOToBookList(List<BookDTO> bookDTOS) {
        return bookDTOS.parallelStream()
                .map(BookMapper::convertBookDTOToBook)
                .collect(Collectors.toList());
    }

    public static List<BookDTO> convertBookListToBookDTOList(List<Book> books) {
        return books.parallelStream()
                .map(BookMapper::convertBookToBookDTO)
                .collect(Collectors.toList());
    }
}
