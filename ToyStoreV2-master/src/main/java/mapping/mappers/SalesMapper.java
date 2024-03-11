package mapping.mappers;

import mapping.dtos.SalesDTO;
import model.Sales;

public class SalesMapper {
    public static SalesDTO mapFromodel(Sales sales){
        return new  SalesDTO(sales.getInvoice_number(), sales.getDate());
    }
    public static Sales mapFromDTO(SalesDTO salesDTO){
        return Sales.builder()
                .invoice_number(salesDTO.invoice_number())
                .date(salesDTO.date())
                .build();
    }
}
