package com.Kronos.Kronos.PDV.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.Kronos.Kronos.PDV.dtos.OrderItemDTO;
import com.Kronos.Kronos.PDV.dtos.SaleDT;
import com.Kronos.Kronos.PDV.model.ItemSale;
import com.Kronos.Kronos.PDV.model.Sale;
import com.Kronos.Kronos.PDV.repository.SaleRepository;
import com.Kronos.Kronos.Product.repository.ProductRepository;

@Service
public class SaleService {

    private final SaleRepository saleRepository;

    private final ProductRepository productRepository;


    public SaleService(SaleRepository saleRepository, ProductRepository productRepository) {
        this.saleRepository = saleRepository;
        this.productRepository = productRepository;
    }

    @Transactional
public Sale finalSale(SaleDT saleDTO) {
    Sale sale = new Sale();
    sale.setPaymentMethod(saleDTO.paymentMethod());
    sale.setTotalValue(0.0);
    sale = saleRepository.save(sale);

    Double totalValue = 0.0;

    for (OrderItemDTO itemDTO : saleDTO.getItens()) {
        var product = productRepository.findById(itemDTO.productId())
                .orElseThrow(() -> new RuntimeException("Produto não encontrado: ID " + itemDTO.productId()));

        // 1. REGRA DE ESTOQUE: Não podemos vender o que não temos
        if (product.getStock() < itemDTO.quantity()) {
            throw new RuntimeException("Estoque insuficiente para o produto: " + product.getName());
        }

        // 2. ATUALIZAÇÃO DO ESTOQUE: Diminuir o saldo no banco
        product.setStock(product.getStock() - itemDTO.quantity());
        productRepository.save(product); // Aqui o estoque é atualizado de fato

        var itemSale = new ItemSale();
        itemSale.setSale(sale);
        itemSale.setProduct(product);
        itemSale.setQuantity(itemDTO.quantity());
        itemSale.setUnitPrice(product.getPrice());
        
        // 3. SALVAR O ITEM: Você precisa de um ItemSaleRepository para persistir cada item
        itemSaleRepository.save(itemSale);

        totalValue += product.getPrice() * itemDTO.quantity();
    }

    // 4. ATUALIZAR O TOTAL: Salvar o valor calculado da venda
    sale.setTotalValue(totalValue);
    return saleRepository.save(sale);
}
}