package com.nexuspos.nexuspos.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.nexuspos.nexuspos.model.Transaction;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, String> {
    
    // Custom SQL query to sum the grandTotal of every receipt
    @Query("SELECT COALESCE(SUM(t.grandTotal), 0) FROM Transaction t")
    Double getTotalRevenue();
}