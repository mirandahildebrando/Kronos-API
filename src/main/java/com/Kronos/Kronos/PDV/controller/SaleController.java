package com.Kronos.Kronos.PDV.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Kronos.Kronos.PDV.dtos.SaleDTO;
import com.Kronos.Kronos.PDV.model.Sale;
import com.Kronos.Kronos.PDV.service.SaleService;

import io.swagger.v3.oas.annotations.parameters.RequestBody;

@RestController
@RequestMapping("/sales")
public class SaleController {

    private final SaleService saleService;

    public SaleController(SaleService saleService) {
        this.saleService = saleService;
    }

    @PostMapping
    public ResponseEntity<Sale> finalSale(@RequestBody SaleDTO saleDTO) {
        Sale sale = saleService.finalSale(saleDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(sale);
    }

    @GetMapping
    public ResponseEntity<List<Sale>> getAllSales() {
        return ResponseEntity.ok(saleService.getAllSales());
    }

    @GetMapping("/{id}")
    public Sale getSaleBydId(@PathVariable Long id) {
        return saleService.getSaleById(id);
    }

    @PutMapping("/{id}")
    public SaleDTO updateSale(@PathVariable Long id, @RequestBody SaleDTO dto) {
        return saleService.updateSale(id, dto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSale(@PathVariable Long id) {
        saleService.deleteSale(id);
        return ResponseEntity.noContent().build();
    }

}
