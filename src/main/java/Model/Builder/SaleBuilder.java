package Model.Builder;

import Model.Book;
import Model.Sale;

public class SaleBuilder {
    private Sale sale;

    public SaleBuilder() {
        sale = new Sale();
    }

    public SaleBuilder setId(Long id) {
        sale.setId(id);
        return this;
    }

    public SaleBuilder setBookTitle(String title) {
        sale.setBookTitle(title);
        return this;
    }

    public SaleBuilder setQuantity(int quantity) {
        sale.setQuantity(quantity);
        return this;
    }

    public SaleBuilder setTotalPrice(double price) {
        sale.setTotalPrice(price);
        return this;
    }

    public SaleBuilder setTimesTamp(String timesTamp) {
        sale.setTimesTamp(timesTamp);
        return this;
    }

    public SaleBuilder setUserId(long userId) {
        sale.setUserId(userId);
        return this;
    }

    public Sale build() {
        return sale;
    }
}
