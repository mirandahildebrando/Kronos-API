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
import com.Kronos.Kronos.dtos.SaleDTO;
import com.Kronos.Kronos.entity.Sale;
import com.Kronos.Kronos.service.SaleService;



@RestController
@RequestMapping("/sales")
public class SaleController {

    private final SaleService saleService;

    public SaleController(SaleService saleService) {
        this.saleService = saleService;
    }

    @PostMapping
    public ResponseEntity<APIResponse<SaleDTO>> createSale(@RequestBody SaleDTO saleDTO) {
        SaleDTO sale = saleService.createSale(saleDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(new APIResponse<>(sale, "Venda criada com sucesso"));
    }

    @GetMapping
    public ResponseEntity<APIResponse<List<SaleDTO>>> getAllSales() {
        return ResponseEntity.ok(new APIResponse<>(saleService.getAllSales(), "Lista de vendas retornada com sucesso"));
    }

    @GetMapping("/{id}")
    public ResponseEntity<APIResponse<SaleDTO>> getSaleById(@PathVariable Long id) {
        SaleDTO sale = saleService.getSaleById(id);
        return ResponseEntity.ok(new APIResponse<>(sale, "Venda retornada com sucesso"));
    }

    @PutMapping("/{id}")
    public ResponseEntity<APIResponse<SaleDTO>> updateSale(@PathVariable Long id, @RequestBody SaleDTO dto) {
        SaleDTO updatedSale = saleService.updateSale(id, dto);
        return ResponseEntity.ok(new APIResponse<>(updatedSale, "Venda atualizada com sucesso"));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<APIResponse<Void>> deleteSale(@PathVariable Long id) {
        saleService.deleteSale(id);
        return ResponseEntity.ok(new APIResponse<>(null, "Venda deletada com sucesso"));
    }

}
