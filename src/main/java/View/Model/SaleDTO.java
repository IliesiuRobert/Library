package View.Model;

import javafx.beans.property.*;

public class SaleDTO {
    private StringProperty bookTitle;
    private IntegerProperty quantity;
    private DoubleProperty price;
    private StringProperty timestamp;
    private LongProperty userId;

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

    public void setTimestamp(String timestamp) {
        timestampProperty().set(timestamp);
    }

    public String getTimestamp() {
        return timestampProperty().get();
    }

    public StringProperty timestampProperty() {
        if (timestamp == null) {
            timestamp = new SimpleStringProperty();
        }

        return timestamp;
    }

    public void setUserId(Long userId) {
        userIdProperty().set(userId);
    }

    public Long getUserId() {
        return userIdProperty().get();
    }

    public LongProperty userIdProperty() {
        if (userId == null) {
            userId = new SimpleLongProperty();
        }

        return userId;
    }
}
