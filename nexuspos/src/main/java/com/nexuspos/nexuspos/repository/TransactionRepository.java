package com.nexuspos.nexuspos.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nexuspos.nexuspos.model.Transaction;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, String> {
}