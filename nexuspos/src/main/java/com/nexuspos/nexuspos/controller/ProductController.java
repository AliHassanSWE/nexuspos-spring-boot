package com.nexuspos.nexuspos.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nexuspos.nexuspos.model.Product;
import com.nexuspos.nexuspos.service.ProductService;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    private final ProductService productService;

    // Connects (injects) the Service into the Controller
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    // Endpoint to view the whole inventory
    @GetMapping
    public List<Product> getInventory() {
        return productService.getAllProducts();
    }
}