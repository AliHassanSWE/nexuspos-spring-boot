package com.nexuspos.nexuspos.service;

import com.nexuspos.nexuspos.model.Product;
import com.nexuspos.nexuspos.repository.ProductRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ProductService {

    private final ProductRepository productRepository;
    
    // DSA Application: Fast in-memory lookup cache
    private final Map<String, Product> productCache = new HashMap<>();

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    // @PostConstruct tells Spring Boot to run this method automatically on startup
    @PostConstruct
    public void initializeDatabaseAndCache() {
        // 1. If the MySQL database is empty, add our starting inventory
        if (productRepository.count() == 0) {
            productRepository.save(new Product("101", "Wireless Mouse", 25.99, 50));
            productRepository.save(new Product("102", "Mechanical Keyboard", 89.50, 30));
            productRepository.save(new Product("103", "27-inch Monitor", 299.99, 15));
            productRepository.save(new Product("104", "USB-C Cable", 12.00, 100));
        }

        // 2. Load all products from MySQL into the HashMap cache for O(1) search speeds
        List<Product> allProducts = productRepository.findAll();
        for (Product product : allProducts) {
            productCache.put(product.getId(), product);
        }
    }

    public Product getProductById(String id) {
        return productCache.get(id); 
    }

    public List<Product> getAllProducts() {
        return new ArrayList<>(productCache.values());
    }
}