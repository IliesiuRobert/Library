package Repository;

import Model.Sale;

import java.util.*;

public class SaleRepositoryMock implements SaleRepository {
    private final List<Sale> sales;

    public SaleRepositoryMock() {
        sales = new ArrayList<>();
    }

    public List<Sale> findAllSale() {
        return sales;
    }

    public Optional<Sale> findSaleByTitle(String title) {
        return sales.parallelStream()
                .filter(it -> it.getBookTitle().equals(title))
                .findFirst();
    }

    public boolean saveSale(Sale sale) {
        return sales.add(sale);
    }

    public boolean deleteSale(Sale sale) {
        return sales.remove(sale);
    }

    public void removeAllSale() {
        sales.clear();
    }
}
