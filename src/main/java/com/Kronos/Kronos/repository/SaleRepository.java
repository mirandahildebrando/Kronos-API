package com.Kronos.Kronos.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.Kronos.Kronos.entity.Sale;


@Repository
public interface SaleRepository extends JpaRepository<Sale, Long> {

    public Sale findById(long id);
    public Sale  deleteById(long id);

}
