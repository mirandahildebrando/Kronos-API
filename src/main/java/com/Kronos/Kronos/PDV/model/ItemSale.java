package com.Kronos.Kronos.PDV.model;

import com.Kronos.Kronos.Product.model.Product;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ItemSale {

    private Long id;
    @ManyToOne
    private Sale sale;
    @ManyToOne
    private Product product;
    private Integer quantity;
    private Double unitPrice;

}
