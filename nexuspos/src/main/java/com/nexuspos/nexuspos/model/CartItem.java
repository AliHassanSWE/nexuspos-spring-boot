package com.nexuspos.nexuspos.model;

import jakarta.persistence.Embeddable;
import jakarta.persistence.ManyToOne;

@Embeddable // Tells MySQL this object doesn't need its own table, it lives inside a Transaction
public class CartItem {
    
    @ManyToOne // Relational DB Concept: Many cart items can point to One product
    private Product product;
    
    private int quantity;

    public CartItem() {} // Empty constructor required by JPA

    public CartItem(Product product, int quantity) {
        this.product = product;
        this.quantity = quantity;
    }

    public Product getProduct() { return product; }
    public int getQuantity() { return quantity; }
    
    public void setQuantity(int quantity) { 
        this.quantity = quantity; 
    }

    public double getSubtotal() {
        return product.getPrice() * quantity;
    }
}