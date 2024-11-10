package Repository;

import Model.Sale;

import java.util.*;

public interface SaleRepository {
    List<Sale> findAllSale();

    Optional<Sale> findSaleByTitle(String title);

    boolean saveSale(Sale sale);

    boolean deleteSale(Sale sale);

    void removeAllSale();
}
