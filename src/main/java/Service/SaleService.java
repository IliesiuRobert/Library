package Service;

import Model.Sale;

import java.util.*;

public interface SaleService {
    List<Sale> findAllSale();

    Sale findSaleByTitle(String title);

    boolean saveSale(Sale sale);

    boolean deleteSale(Sale sale);
}
