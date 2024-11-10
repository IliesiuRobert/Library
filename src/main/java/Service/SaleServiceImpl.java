package Service;

import Model.Sale;
import Repository.SaleRepository;

import java.util.List;

public class SaleServiceImpl implements SaleService{
    private final SaleRepository saleRepository;

    public SaleServiceImpl(SaleRepository saleRepository) {
        this.saleRepository = saleRepository;
    }

    @Override
    public boolean saveSale(Sale sale) {
        return saleRepository.saveSale(sale);
    }

    @Override
    public boolean deleteSale(Sale sale) {
        return saleRepository.deleteSale(sale);
    }

    @Override
    public List<Sale> findAllSale() {
        return saleRepository.findAllSale();
    }

    @Override
    public Sale findSaleByTitle(String title) {
        return saleRepository.findSaleByTitle(title)
                .orElseThrow(() -> new IllegalArgumentException("Sale with title: %s was not found".formatted(title)));
    }
}
