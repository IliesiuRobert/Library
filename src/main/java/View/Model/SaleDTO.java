package View.Model;

import javafx.beans.property.*;

public class SaleDTO {
    private StringProperty bookTitle;
    private IntegerProperty quantity;
    private DoubleProperty price;

    public void setBookTitle(String bookTitle) {
        bookTitleProperty().set(bookTitle);
    }

    public String getBookTitle() {
        return bookTitleProperty().get();
    }

    public StringProperty bookTitleProperty() {
        if (bookTitle == null) {
            bookTitle = new SimpleStringProperty();
        }

        return bookTitle;
    }

    public void setQuantity(Integer quantity) {
        quantityProperty().set(quantity);
    }

    public Integer getQuantity() {
        return quantityProperty().get();
    }

    public IntegerProperty quantityProperty() {
        if (quantity == null) {
            quantity = new SimpleIntegerProperty();
        }

        return quantity;
    }

    public void setTotalPrice(Double price) {
        priceProperty().set(price);
    }

    public Double getTotalPrice() {
        return priceProperty().get();
    }

    public DoubleProperty priceProperty() {
        if (price == null) {
            price = new SimpleDoubleProperty();
        }

        return price;
    }
}
