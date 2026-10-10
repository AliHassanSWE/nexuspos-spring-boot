package com.nexuspos.nexuspos.controller;

import java.util.UUID;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nexuspos.nexuspos.model.Cart;
import com.nexuspos.nexuspos.model.CartItem;
import com.nexuspos.nexuspos.model.Product;
import com.nexuspos.nexuspos.model.Transaction;
import com.nexuspos.nexuspos.service.ProductService;
import com.nexuspos.nexuspos.service.TransactionService;

@RestController
@RequestMapping("/api/cart")
public class CartController {

    private final ProductService productService;
    private final TransactionService transactionService; // Added the new service
    private Cart currentCart;

    // Connects both the Product and Transaction services
    public CartController(ProductService productService, TransactionService transactionService) {
        this.productService = productService;
        this.transactionService = transactionService;
        this.currentCart = new Cart();
    }

    @GetMapping
    public Cart getCart() {
        return currentCart;
    }

    @PostMapping("/add/{productId}")
    public Cart addToCart(@PathVariable String productId) {
        Product product = productService.getProductById(productId);
        if (product != null) {
            currentCart.addItem(new CartItem(product, 1));
        }
        return currentCart;
    }

    @PostMapping("/checkout")
    public String processCheckout() {
        if (currentCart.getItems().isEmpty()) {
            return "Cart is empty. Cannot checkout.";
        }

        // 1. Reduce the inventory stock
        for (CartItem item : currentCart.getItems()) {
            Product inventoryProduct = item.getProduct();
            inventoryProduct.reduceStock(item.getQuantity());
        }

        // 2. Generate a random Receipt ID and save the transaction
        String receiptId = UUID.randomUUID().toString().substring(0, 8);
        Transaction newSale = new Transaction(receiptId, currentCart.getItems(), currentCart.getCartTotal());
        transactionService.saveTransaction(newSale);

        // 3. Empty the cart for the next customer
        currentCart.clearCart();
        
        return "Checkout successful! Receipt ID: " + receiptId;
    }
}