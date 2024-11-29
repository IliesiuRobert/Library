package Mapper;

import Model.Builder.SaleBuilder;
import Model.Sale;
import View.Model.Builder.SaleDTOBuilder;
import View.Model.SaleDTO;

import java.util.List;
import java.util.stream.Collectors;

public class SaleMapper {
    public static SaleDTO convertSaleToSaleDTO(Sale sale) {
        return new SaleDTOBuilder().setBookTitle(sale.getBookTitle()).setQuantity(sale.getQuantity())
                .setTotalPrice(sale.getTotalPrice()).setTimpsTamp(sale.getTimesTamp())
                .setUserId(sale.getUserId()).build();
    }

    public static Sale convertSaleDTOToSale(SaleDTO saleDTO) {
        return new SaleBuilder().setBookTitle(saleDTO.getBookTitle()).setQuantity(saleDTO.getQuantity())
                .setTotalPrice(saleDTO.getTotalPrice()).setTimesTamp(saleDTO.getTimestamp())
                .setUserId(saleDTO.getUserId()).build();
    }

    public static List<Sale> convertSaleDTOToSaleList(List<SaleDTO> saleDTOS) {
        return saleDTOS.parallelStream()
                .map(SaleMapper::convertSaleDTOToSale)
                .collect(Collectors.toList());
    }

    public static List<SaleDTO> convertSaleListToSaleDTOList(List<Sale> sales) {
        return sales.parallelStream()
                .map(SaleMapper::convertSaleToSaleDTO)
                .collect(Collectors.toList());
    }
}
