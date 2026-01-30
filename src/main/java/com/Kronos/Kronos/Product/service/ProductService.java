package com.Kronos.Kronos.Product.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.Kronos.Kronos.Login.dtos.UserRequestDTO;
import com.Kronos.Kronos.Product.dtos.ProductDto;
import com.Kronos.Kronos.Product.model.Product;
import com.Kronos.Kronos.Product.repository.ProductRepository;

@Service

public class ProductService {

    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public ProductDto createProduct(ProductDto dto) {
        Product product = new Product();
        product.setName(dto.getName());
        product.setPrice(dto.getPrice());
        product.setQuantity(dto.getQuantity());

        Product savedProduct = productRepository.save(product);

        ProductDto responseDto = new ProductDto();
        responseDto.setName(savedProduct.getName());
        responseDto.setPrice(savedProduct.getPrice());
        responseDto.setQuantity(savedProduct.getQuantity());
        return responseDto;
    }

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public Product getProductById(Long id) {
        return productRepository.findById(id).orElse(null);
    }

    public ProductDto updateProduct(Long id, ProductDto dto) {
    Product product = productRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Product not found"));

    product.setName(dto.getName());
    product.setPrice(dto.getPrice());
    product.setQuantity(dto.getQuantity());

    productRepository.save(product);

    return dto;
}


    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }

}
