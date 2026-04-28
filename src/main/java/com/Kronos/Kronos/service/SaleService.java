package com.Kronos.Kronos.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.Kronos.Kronos.dtos.OrderItemDTO;
import com.Kronos.Kronos.dtos.SaleDTO;
import com.Kronos.Kronos.entity.ItemSale;
import com.Kronos.Kronos.entity.Product;
import com.Kronos.Kronos.entity.Sale;
import com.Kronos.Kronos.repository.ItemSaleRepository;
import com.Kronos.Kronos.repository.ProductRepository;
import com.Kronos.Kronos.repository.SaleRepository;

@Service
public class SaleService {

    private final SaleRepository saleRepository;
    private final ProductRepository productRepository;
    private final ItemSaleRepository itemSaleRepository;

    public SaleService(SaleRepository saleRepository,
                       ProductRepository productRepository,
                       ItemSaleRepository itemSaleRepository) {
        this.saleRepository = saleRepository;
        this.productRepository = productRepository;
        this.itemSaleRepository = itemSaleRepository;
    }

    @Transactional
    public SaleDTO createSale(SaleDTO dto) {

        Sale sale = new Sale();
        sale.setPaymentMethod(dto.paymentMethod());
        sale.setTotalValue(0.0);

        sale = saleRepository.save(sale);

        double totalValue = 0.0;

        for (OrderItemDTO itemDTO : dto.items()) {

            Product product = productRepository.findById(itemDTO.productId())
                    .orElseThrow(() -> new RuntimeException(
                            "Produto não encontrado: ID " + itemDTO.productId()));

            if (product.getQuantity() < itemDTO.quantity()) {
                throw new RuntimeException(
                        "Estoque insuficiente para o produto: " + product.getName());
            }

            // baixa estoque
            product.setQuantity(product.getQuantity() - itemDTO.quantity());
            productRepository.save(product);

            ItemSale itemSale = new ItemSale();
            itemSale.setSale(sale);
            itemSale.setProduct(product);
            itemSale.setQuantity(itemDTO.quantity());
            itemSale.setUnitPrice(product.getPrice());

            itemSaleRepository.save(itemSale);

            totalValue += product.getPrice() * itemDTO.quantity();
        }

        sale.setTotalValue(totalValue);
        saleRepository.save(sale);

        return toDTO(sale);
    }

    public List<SaleDTO> getAllSales() {
        return saleRepository.findAll()
                .stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    public SaleDTO getSaleById(Long id) {
        Sale sale = saleRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Venda não encontrada"));

        return toDTO(sale);
    }

    public SaleDTO updateSale(Long id, SaleDTO dto) {

        Sale sale = saleRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Venda não encontrada"));

        sale.setPaymentMethod(dto.paymentMethod());
        sale.setTotalValue(dto.totalValue());

        sale = saleRepository.save(sale);

        return toDTO(sale);
    }

    public void deleteSale(Long id) {
        saleRepository.deleteById(id);
    }

    private SaleDTO toDTO(Sale sale) {

        List<OrderItemDTO> items = sale.getItems() != null
                ? sale.getItems().stream()
                    .map(item -> new OrderItemDTO(
                            item.getProduct().getId(),
                            item.getQuantity()
                    ))
                    .collect(Collectors.toList())
                : List.of();

        return new SaleDTO(
                sale.getPaymentMethod(),
                sale.getTotalValue(),
                items
        );
    }
}