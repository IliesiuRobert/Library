package View.Model.Builder;

import View.Model.SaleDTO;

public class SaleDTOBuilder {
    private SaleDTO saleDTO;

    public SaleDTOBuilder() {
        saleDTO = new SaleDTO();
    }

    public SaleDTOBuilder setBookTitle(String title) {
        saleDTO.setBookTitle(title);
        return this;
    }

    public SaleDTOBuilder setQuantity(Integer quantity) {
        saleDTO.setQuantity(quantity);
        return this;
    }

    public SaleDTOBuilder setTotalPrice(Double price) {
        saleDTO.setTotalPrice(price);
        return this;
    }

    public SaleDTOBuilder setTimpsTamp(String timpsTamp) {
        saleDTO.setTimestamp(timpsTamp);
        return this;
    }

    public SaleDTOBuilder setUserId(Long userId) {
        saleDTO.setUserId(userId);
        return this;
    }

    public SaleDTO build() {
        return saleDTO;
    }

}
