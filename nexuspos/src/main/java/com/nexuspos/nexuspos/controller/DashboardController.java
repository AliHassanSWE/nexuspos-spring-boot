package com.nexuspos.nexuspos.controller;

import com.nexuspos.nexuspos.repository.ProductRepository;
import com.nexuspos.nexuspos.repository.TransactionRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/dashboard")
public class DashboardController {

    private final TransactionRepository transactionRepository;
    private final ProductRepository productRepository;

    public DashboardController(TransactionRepository transactionRepository, ProductRepository productRepository) {
        this.transactionRepository = transactionRepository;
        this.productRepository = productRepository;
    }

    @GetMapping("/stats")
    public Map<String, Object> getDashboardStats() {
        Map<String, Object> stats = new HashMap<>();
        
        // Fetch total revenue, total transactions, and low stock items (under 20 units)
        stats.put("totalRevenue", transactionRepository.getTotalRevenue());
        stats.put("totalTransactions", transactionRepository.count());
        stats.put("lowStockAlerts", productRepository.findByStockQuantityLessThan(20));
        
        return stats;
    }
}