package com.nexuspos.nexuspos.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.nexuspos.nexuspos.model.Product;
import com.nexuspos.nexuspos.repository.ProductRepository;
import com.nexuspos.nexuspos.service.ProductService;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    private final ProductService productService;
    private final ProductRepository productRepository; // Added to handle database saving

    // Connects (injects) both the Service and Repository into the Controller
    public ProductController(ProductService productService, ProductRepository productRepository) {
        this.productService = productService;
        this.productRepository = productRepository;
    }

    // Endpoint to view the whole inventory
    @GetMapping
    public List<Product> getInventory() {
        return productService.getAllProducts();
    }

    // NEW: Endpoint to update stock levels (Admin Only)
    @PostMapping("/{id}/addStock")
public ResponseEntity<?> addStock(@PathVariable String id, @RequestParam int quantity) {
    return productRepository.findById(id).map(product -> {
            product.setStockQuantity(product.getStockQuantity() + quantity);
            productRepository.save(product);
            return ResponseEntity.ok().body("Stock updated successfully");
        }).orElseGet(() -> ResponseEntity.notFound().build());
    }
}