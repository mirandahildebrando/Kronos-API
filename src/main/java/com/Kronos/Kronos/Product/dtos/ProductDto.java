package com.Kronos.Kronos.Product.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductDto {

    @NotBlank
    @Size(min = 5, max = 30)
    private String name;
    @NotBlank
    @Size(min = 0, max = 30)
    private Double price;
    @NotBlank
    @Size(min = 5)
    private Integer quantity;

}
