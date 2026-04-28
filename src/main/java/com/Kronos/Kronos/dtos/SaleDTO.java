package com.Kronos.Kronos.dtos;

public record SaleDTO(
        String paymentMethod,
        Double totalValue,
        java.util.List<OrderItemDTO> items
) {

}
