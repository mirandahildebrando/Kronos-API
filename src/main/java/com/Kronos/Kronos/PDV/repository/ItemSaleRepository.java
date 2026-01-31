package com.Kronos.Kronos.PDV.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.Kronos.Kronos.PDV.model.ItemSale;

@Repository
public interface ItemSaleRepository extends JpaRepository<ItemSale, Long> {

}
