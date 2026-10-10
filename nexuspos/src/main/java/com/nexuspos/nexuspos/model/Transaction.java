package com.nexuspos.nexuspos.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity // Creates the 'transaction' table in MySQL
public class Transaction {
    
    @Id
    private String transactionId;
    
    private LocalDateTime timestamp;
    
    @ElementCollection // Tells MySQL to create a hidden secondary table to store this list of items
    private List<CartItem> purchasedItems;
    
    private double grandTotal;

    public Transaction() {} // Empty constructor required by JPA

    public Transaction(String transactionId, List<CartItem> items, double grandTotal) {
        this.transactionId = transactionId;
        this.timestamp = LocalDateTime.now();
        this.purchasedItems = new ArrayList<>(items); 
        this.grandTotal = grandTotal;
    }

    public String getTransactionId() { return transactionId; }
    public LocalDateTime getTimestamp() { return timestamp; }
    public List<CartItem> getPurchasedItems() { return purchasedItems; }
    public double getGrandTotal() { return grandTotal; }
}