package Controller;

import Mapper.BookMapper;
import Mapper.SaleMapper;
import Model.Book;
import Service.BookService;
import Service.SaleService;
import View.BookView;
import View.Model.BookDTO;
import View.Model.Builder.BookDTOBuilder;
import View.Model.Builder.SaleDTOBuilder;
import View.Model.SaleDTO;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

public class BookController {
    private final BookView bookView;
    private final BookService bookService;
    private final SaleService saleService;

    public BookController(BookView bookView, BookService bookService, SaleService saleService) {
        this.bookView = bookView;
        this.bookService = bookService;
        this.saleService = saleService;

        this.bookView.addSaveButtonListener(new SaveButtonListener());
        this.bookView.addSelectionTableListener(new SelectionTableListener());
        this.bookView.addDeleteButtonListener(new DeleteButtonListener());
        this.bookView.addSaleButtonListener(new SaleButtonListener());
        this.bookView.addOrderButtonListener(new SaveOrderButtonListener());
        //this.bookView.addSelectionTableOrderListener(new SelectionTableOrderListener());
    }

    private class SaveButtonListener implements EventHandler<ActionEvent> {

        @Override
        public void handle(ActionEvent event) {
            String title = bookView.getTitle();
            String author = bookView.getAuthor();

            Integer amount = bookView.getAmount();
            Double price = bookView.getPrice();

            if (title.isEmpty() || author.isEmpty()){
                bookView.displayAlertMessage("Save Error", "Problem at Title or Author fields", "Can not have empty Author or Title fields. Please fill in the fields before submitting Save!");
                bookView.getBooksObservableList().get(0).setTitle("No Name");
            } else {
                BookDTO bookDTO = new BookDTOBuilder().setAuthor(author).setTitle(title).setAmount(amount).setPrice(price).build();
                boolean savedBook = bookService.save(BookMapper.convertBookDTOToBook(bookDTO));

                if (savedBook) {
                    bookView.displayAlertMessage("Save Successful", "Book Added", "Book was successfully added to the database.");
                    bookView.addBookToObservableList(bookDTO);
                } else {
                    bookView.displayAlertMessage("Save Not Successful", "Book was not added", "There was a problem at adding the book into the database.");
                }
            }
        }
    }

    private class SelectionTableListener implements ChangeListener {

        @Override
        public void changed(ObservableValue observable, Object oldValue, Object newValue) {
            BookDTO selectedBookDTO = (BookDTO) newValue;

            if (selectedBookDTO != null) {
                System.out.println("Book Author: " + selectedBookDTO.getAuthor() + " Title: " + selectedBookDTO.getTitle());
            }
        }
    }

//    private class SelectionTableOrderListener implements ChangeListener {
//
//        @Override
//        public void changed(ObservableValue observable, Object oldValue, Object newValue) {
//            SaleDTO selectedSaleDTO = (SaleDTO) newValue;
//
//            if (selectedSaleDTO != null) {
//                System.out.println("Sale Tile: " + selectedSaleDTO.getBookTitle() + " Quantity: " + selectedSaleDTO.getQuantity());
//            }
//        }
//    }

    private class DeleteButtonListener implements EventHandler<ActionEvent> {

        @Override
        public void handle(ActionEvent event) {
            BookDTO bookDTO = (BookDTO) bookView.getBookTableView().getSelectionModel().getSelectedItem();
            if (bookDTO != null){
                boolean deletionSuccessfull = bookService.delete(BookMapper.convertBookDTOToBook(bookDTO));
                if (deletionSuccessfull){
                    bookView.displayAlertMessage("Delete Successful", "Book Deleted", "Book was successfully deleted from database.");
                    bookView.removeBookFromObservableList(bookDTO);
                } else {
                    bookView.displayAlertMessage("Deletion not successful", "Deletion Process", "There was a problem in the deletion process. Please restart the application and try again!");
                }
            } else {
                bookView.displayAlertMessage("Deletion not successful", "Deletion Process", "You need to select a row from table before pressing the delete button!");
            }
        }
    }

    private class SaveOrderButtonListener implements EventHandler<ActionEvent> {
        @Override
        public void handle(ActionEvent event) {
            String bookTitle = bookView.getSaleTitleBook();
            Integer quantity = bookView.getSaleQuantity();

            if (bookTitle.isEmpty() || quantity == null ){
                bookView.displayAlertMessage("Save Error", "Problem at fields title, quantity, price", "Can not have empty Title, 0 quantity or null price. Please fill in the fields before submitting!");
                bookView.getSaleObservableList().get(0).setBookTitle("No Name");
            } else {
                Book book = bookService.findByTitle(bookTitle);

                if (book != null){
                    SaleDTO saleDTO = new SaleDTOBuilder().setBookTitle(bookTitle).setQuantity(quantity).setTotalPrice(quantity * book.getPrice()).build();
                    boolean savedSale = saleService.saveSale(SaleMapper.convertSaleDTOToSale(saleDTO));

                    if (savedSale) {
                        bookView.displayAlertMessage("Save Successful", "Order Added", "Order was successfully added to the database.");
                        bookView.addSaleToObservableList(saleDTO);
                    } else {
                        bookView.displayAlertMessage("Save Not Successful", "Order was not added", "There was a problem at adding the order into the database.");
                    }
                } else {
                    bookView.displayAlertMessage("Save Not Successful", "Order was not added", "Title can not find in books table!");
                }
            }
        }
    }

    private class SaleButtonListener implements EventHandler<ActionEvent> {

        @Override
        public void handle(ActionEvent event) {
            BookDTO bookDTO = (BookDTO) bookView.getBookTableView().getSelectionModel().getSelectedItem();
            SaleDTO saleDTO = (SaleDTO) bookView.getSaleTableView().getSelectionModel().getSelectedItem();

            if (bookDTO != null && saleDTO != null && bookDTO.getTitle().equals(saleDTO.getBookTitle())){
                int value = bookDTO.getAmount() - saleDTO.getQuantity();
                bookDTO.sellBook(saleDTO.getQuantity());
                bookService.updateAmount(bookDTO.getTitle(), value);
                boolean deletionSuccessfull = saleService.deleteSale(SaleMapper.convertSaleDTOToSale(saleDTO));

                if (deletionSuccessfull){
                    bookView.displayAlertMessage("Order Finish", "Order was procesed", "Order was procesesd from database.");
                    bookView.removeSaleFromObservableList(saleDTO);

                    if (bookDTO.getAmount() == 0) {
                        boolean succ = bookService.delete(BookMapper.convertBookDTOToBook(bookDTO));
                        if (succ) {
                            bookView.displayAlertMessage("Book eliminated", "Book Deleted", "Book was deleted from database because of amount value (amount = 0)!");
                            bookView.removeBookFromObservableList(bookDTO);
                        }
                    }
                } else {
                    bookView.displayAlertMessage("Deletion not successful", "Deletion Process", "There was a problem in the deletion process. Please restart the application and try again!");
                }
            } else {
                bookView.displayAlertMessage("Deletion not successful", "Deletion Process", "You need to select a row from table before pressing the delete button!");
            }
        }
    }
}
