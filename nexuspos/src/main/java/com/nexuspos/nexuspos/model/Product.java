package com.nexuspos.nexuspos.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Product {
    
    @Id
    private String id; // The @Id tag tells MySQL this is the Primary Key (Barcode)
    
    private String name;
    private double price;
    private int stockQuantity;

    // JPA requires an empty default constructor to build the database table
    public Product() {}

    public Product(String id, String name, double price, int stockQuantity) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.stockQuantity = stockQuantity;
    }

    public String getId() { return id; }
    public String getName() { return name; }
    public double getPrice() { return price; }
    public int getStockQuantity() { return stockQuantity; }

    public void reduceStock(int quantity) {
        if (quantity <= this.stockQuantity) {
            this.stockQuantity -= quantity;
        }
    }
}