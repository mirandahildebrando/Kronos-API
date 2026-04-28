package com.Kronos.Kronos.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Kronos.Kronos.dtos.APIResponse;
import com.Kronos.Kronos.dtos.ProductDto;
import com.Kronos.Kronos.service.ProductService;

import io.swagger.v3.oas.models.responses.ApiResponse;

@RestController
@RequestMapping("/products")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping
    public ResponseEntity<APIResponse<ProductDto>> createProduct(@RequestBody ProductDto dto) {
        ProductDto createProduct = productService.createProduct(dto);
        return ResponseEntity.ok(
        new APIResponse<>(createProduct, "Produto criado com sucesso")
);
    }

    @GetMapping
    public ResponseEntity<APIResponse<List<ProductDto>>> getAllProducts() {
        List<ProductDto> products = productService.getAllProducts();
        return ResponseEntity.ok(
            new APIResponse<>(products, "Lista de produtos retornada com sucesso")
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<APIResponse<ProductDto>> getProductById(@PathVariable Long id) {
        ProductDto product = productService.getProductById(id);
        return ResponseEntity.ok(
            new APIResponse<>(product, "Produto retornado com sucesso")
        );
    }

    @PutMapping("/{id}")
    public ResponseEntity<APIResponse<ProductDto>> updateProduct(@PathVariable Long id, @RequestBody ProductDto dto) {
        ProductDto updateProduct = productService.updateProduct(id, dto);
        return ResponseEntity.ok(
            new APIResponse<>(updateProduct, "Produto atualizado com sucesso")
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<APIResponse<Void>> deleteProduct(@PathVariable Long id) {
        productService.deleteProduct(id);
        return ResponseEntity.ok(new APIResponse<>(null, "Produto deletado com sucesso"));
    }

}
