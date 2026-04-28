package com.Kronos.Kronos.dtos;

public record SaleDTO(
        String paymentMethod,
        java.util.List<OrderItemDTO> items
) {

}
