package com.nexuspos.nexuspos.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nexuspos.nexuspos.model.Transaction;
import com.nexuspos.nexuspos.service.TransactionService;

@RestController
@RequestMapping("/api/transactions")
public class TransactionController {
    
    private final TransactionService transactionService;

    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    // Endpoint to retrieve all past sales from the database
    @GetMapping
    public List<Transaction> getHistory() {
        return transactionService.getAllTransactions();
    }
}