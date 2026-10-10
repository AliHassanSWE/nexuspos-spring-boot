package com.nexuspos.nexuspos.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.nexuspos.nexuspos.model.Transaction;
import com.nexuspos.nexuspos.repository.TransactionRepository;

@Service
public class TransactionService {
    
    private final TransactionRepository transactionRepository;

    public TransactionService(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }

    public void saveTransaction(Transaction transaction) {
        // Now permanently saves to MySQL instead of temporary RAM
        transactionRepository.save(transaction);
    }

    public List<Transaction> getAllTransactions() {
        return transactionRepository.findAll();
    }
}