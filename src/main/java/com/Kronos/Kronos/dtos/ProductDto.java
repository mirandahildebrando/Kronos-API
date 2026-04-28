package com.Kronos.Kronos.dtos;

public record ProductDto(
    Long id,
    String name,
    Double price,
    Integer quantity
) {

}
