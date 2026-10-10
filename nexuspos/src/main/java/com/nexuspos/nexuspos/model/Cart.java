package com.nexuspos.nexuspos.model;

import java.util.ArrayList;
import java.util.List;

public class Cart {
    private List<CartItem> items;

    public Cart() {
        this.items = new ArrayList<>();
    }

    public void addItem(CartItem newItem) {
        // DSA Logic: Search the ArrayList to see if the product is already in the cart
        for (CartItem existingItem : items) {
            if (existingItem.getProduct().getId().equals(newItem.getProduct().getId())) {
                // Product exists! Just increase the quantity and exit the method
                existingItem.setQuantity(existingItem.getQuantity() + 1);
                return; 
            }
        }
        // If the loop finishes without finding the product, add it as a new row
        this.items.add(newItem);
    }

    public List<CartItem> getItems() { return items; }

    public double getCartTotal() {
        double total = 0;
        for (CartItem item : items) {
            total += item.getSubtotal();
        }
        return total;
    }
    
    // Helper method to empty the cart after a successful checkout
    public void clearCart() {
        this.items.clear();
    }
}