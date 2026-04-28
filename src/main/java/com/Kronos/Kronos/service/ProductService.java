package com.Kronos.Kronos.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;


import com.Kronos.Kronos.dtos.ProductDto;
import com.Kronos.Kronos.entity.Product;
import com.Kronos.Kronos.repository.ProductRepository;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public ProductDto createProduct(ProductDto dto) {
        Product product = new Product();
        product.setName(dto.name());
        product.setPrice(dto.price());
        product.setQuantity(dto.quantity());

        Product savedProduct = productRepository.save(product);
        return toDTO(savedProduct);
    }

    public List<ProductDto> getAllProducts() {
        Sort sort = Sort.by("name").ascending();

        return productRepository.findAll(sort)
                .stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    public ProductDto getProductById(Long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Produto não encontrado"));

        return toDTO(product);
    }

    public ProductDto updateProduct(Long id, ProductDto dto) {

        Product product = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Produto não encontrado"));

        // NÃO mexe no ID
        product.setName(dto.name());
        product.setPrice(dto.price());
        product.setQuantity(dto.quantity());

        Product updated = productRepository.save(product);

        return toDTO(updated);
    }

    public void deleteProduct(Long id) {
        if (!productRepository.existsById(id)) {
            throw new RuntimeException("Produto não encontrado");
        }

        productRepository.deleteById(id);
    }

    private ProductDto toDTO(Product product) {
        return new ProductDto(
                product.getId(),
                product.getName(),
                product.getPrice(),
                product.getQuantity()
        );
    }
}