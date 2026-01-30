package com.Kronos.Kronos.Product.service;

import java.util.List;

import org.springframework.stereotype.Service;

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

    public List<Product> getallProducts() {
        return productRepository.findAll();
    }

    public Product getProductById(Long id) {
        return productRepository.findById(id).orElse(null);
    }

    public Product updateProduct(Long id, Product productDetails) {
        return productRepository.findById(id)
        .map(product -> {
            product.setName(productDetails.getName());
            product.setPrice(productDetails.getPrice());
            product.setQuantity(productDetails.getQuantity());
            return productRepository.save(product);
        }).orElse(null);
    }

    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }

}
