package com.nexuspos.nexuspos.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nexuspos.nexuspos.model.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, String> {
    // Custom query to fetch items with stock below a certain number
    List<Product> findByStockQuantityLessThan(int threshold);
}