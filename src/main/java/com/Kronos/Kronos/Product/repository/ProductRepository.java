package com.Kronos.Kronos.Product.repository;

import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends org.springframework.data.jpa.repository.JpaRepository<com.Kronos.Kronos.Product.model.Product, Long> {

}
