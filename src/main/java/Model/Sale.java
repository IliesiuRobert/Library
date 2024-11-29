package Model;

public class Sale {
    private long id;
    private String bookTitle;
    private int quantity;
    private double price;
    private String timesTamp;
    private long userId;

    public long getId() {
        return id;
    }

    public double getTotalPrice() {
        return price;
    }

    public String getBookTitle() {
        return bookTitle;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setTotalPrice(double price) {
        this.price = price;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setBookTitle(String bookTitle) {
        this.bookTitle = bookTitle;
    }

    public String getTimesTamp() {
        return timesTamp;
    }

    public void setTimesTamp(String timesTamp) {
        this.timesTamp = timesTamp;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "Id= " + id +" bookTitle= " + bookTitle + " quantity= " + quantity + " price= " + price;
    }
}
