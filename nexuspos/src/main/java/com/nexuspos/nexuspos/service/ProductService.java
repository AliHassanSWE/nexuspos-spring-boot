package com.nexuspos.nexuspos.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.nexuspos.nexuspos.model.Product;
import com.nexuspos.nexuspos.repository.ProductRepository;

import jakarta.annotation.PostConstruct;

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
            productRepository.save(new Product("103", "27-inch 4K Monitor", 299.99, 15));
            productRepository.save(new Product("104", "Braided USB-C Cable", 12.00, 100));
            productRepository.save(new Product("105", "Noise Canceling Headphones", 149.99, 20));
            productRepository.save(new Product("106", "Ergonomic Desk Chair", 199.00, 10));
            productRepository.save(new Product("107", "Aluminum Laptop Stand", 34.50, 45));
            productRepository.save(new Product("108", "1080p HD Webcam", 59.99, 25));
        }
        //  append 7 new products without overwriting existing data
        if (!productRepository.existsById("109")) {
            productRepository.save(new Product("109", "Bluetooth Speaker", 45.00, 40));
            productRepository.save(new Product("110", "HD Web Camera", 59.99, 15));
            productRepository.save(new Product("111", "Laptop Sleeve", 24.50, 60));
            productRepository.save(new Product("112", "Wireless Earbuds", 79.99, 35));
            productRepository.save(new Product("113", "Gaming Mousepad", 14.99, 80));
            productRepository.save(new Product("114", "USB Flash Drive 64GB", 18.50, 100));
            productRepository.save(new Product("115", "Smartphone Stand", 12.99, 50));
        }
        //  append 10 MORE new products to reach a total of 25
        if (!productRepository.existsById("116")) {
            productRepository.save(new Product("116", "Ergonomic Vertical Mouse", 35.00, 25));
            productRepository.save(new Product("117", "Mechanical Numpad", 25.50, 40));
            productRepository.save(new Product("118", "Monitor Light Bar", 49.99, 20));
            productRepository.save(new Product("119", "USB-C Hub 7-in-1", 39.00, 60));
            productRepository.save(new Product("120", "Desk Mat (Large)", 22.99, 75));
            productRepository.save(new Product("121", "Webcam Privacy Cover", 5.99, 150));
            productRepository.save(new Product("122", "Cable Management Kit", 15.00, 85));
            productRepository.save(new Product("123", "Adjustable Footrest", 29.50, 30));
            productRepository.save(new Product("124", "Blue Light Glasses", 19.99, 45));
            productRepository.save(new Product("125", "Microphone Boom Arm", 45.00, 15));
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