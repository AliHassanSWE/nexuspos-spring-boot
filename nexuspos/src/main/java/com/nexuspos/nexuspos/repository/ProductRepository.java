package com.nexuspos.nexuspos.repository;

import com.nexuspos.nexuspos.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, String> {
    // Spring Boot automatically provides database methods here
}