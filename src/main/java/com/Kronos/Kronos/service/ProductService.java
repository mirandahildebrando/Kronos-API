package com.Kronos.Kronos.service;

import java.util.List;

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

        ProductDto responseDto = new ProductDto(
                savedProduct.getName(),
                savedProduct.getPrice(),
                savedProduct.getQuantity()
        );
        return responseDto;
    }

    public List<Product> getAllProducts() {
        Sort sort = Sort.by("name").ascending();
        return productRepository.findAll(sort);
    }

    public Product getProductById(Long id) {
        return productRepository.findById(id).orElseThrow(() -> new RuntimeException("Produto não encontrado"));
    }

    public ProductDto updateProduct(Long id, ProductDto dto) {
    Product product = productRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Produto não encontrado"));

    product.setName(dto.name());
    product.setPrice(dto.price());
    product.setQuantity(dto.quantity());

    productRepository.save(product);

    return dto;
}


    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }

}
