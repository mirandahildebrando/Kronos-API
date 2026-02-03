package com.Kronos.Kronos.PDV.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.Kronos.Kronos.PDV.dtos.OrderItemDTO;
import com.Kronos.Kronos.PDV.dtos.SaleDTO;
import com.Kronos.Kronos.PDV.model.ItemSale;
import com.Kronos.Kronos.PDV.model.Sale;
import com.Kronos.Kronos.PDV.repository.ItemSaleRepository;
import com.Kronos.Kronos.PDV.repository.SaleRepository;
import com.Kronos.Kronos.Product.repository.ProductRepository;

@Service
public class SaleService { 

    private final SaleRepository saleRepository;

    private final ProductRepository productRepository;

    private final ItemSaleRepository itemSaleRepository;


    public SaleService(SaleRepository saleRepository, ProductRepository productRepository, ItemSaleRepository itemSaleRepository) {
        this.saleRepository = saleRepository;
        this.productRepository = productRepository;
        this.itemSaleRepository = itemSaleRepository;
    }

    public List<Sale> getAllSales() {
        return saleRepository.findAll();
    }

    public Sale getSaleById(Long id) {
        return saleRepository.findById(id).orElseThrow(() -> new RuntimeException("Venda não encontrada"));

    }

    public SaleDTO updateSale(Long id, SaleDTO dto) {
        Sale sale = saleRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Venda não encontrada"));

        sale.setPaymentMethod(dto.paymentMethod());
        sale.setTotalValue(sale.getTotalValue());


        saleRepository.save(sale);

        return dto;
    }

    public void deleteSale(Long id) {
        saleRepository.deleteById(id);
    }

    @Transactional
    public Sale finalSale(SaleDTO saleDTO) {
    Sale sale = new Sale();
    sale.setPaymentMethod(saleDTO.paymentMethod());
    sale.setTotalValue(0.0);
    sale = saleRepository.save(sale);

    Double totalValue = 0.0;

    for (OrderItemDTO itemDTO : saleDTO.items()) {
        var product = productRepository.findById(itemDTO.productId())
                .orElseThrow(() -> new RuntimeException("Produto não encontrado: ID " + itemDTO.productId()));

       
        if (product.getQuantity() < itemDTO.quantity()) {
            throw new RuntimeException("Estoque insuficiente para o produto: " + product.getName());
        }

        
        product.setQuantity(product.getQuantity() - itemDTO.quantity());
        productRepository.save(product); 

        var itemSale = new ItemSale();
        itemSale.setSale(sale);
        itemSale.setProduct(product);
        itemSale.setQuantity(itemDTO.quantity());
        itemSale.setUnitPrice(product.getPrice());
        
        
        itemSaleRepository.save(itemSale);

        totalValue += product.getPrice() * itemDTO.quantity();
    }

    
    sale.setTotalValue(totalValue);
    return saleRepository.save(sale);

    
}
}