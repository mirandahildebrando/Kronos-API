package com.Kronos.Kronos.repository;

import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends org.springframework.data.jpa.repository.JpaRepository<com.Kronos.Kronos.entity.Product, Long> {

}
