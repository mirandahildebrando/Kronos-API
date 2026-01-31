package com.Kronos.Kronos.PDV.dtos;

public record SaleDTO(
        String paymentMethod,
        java.util.List<OrderItemDTO> items
) {

}
