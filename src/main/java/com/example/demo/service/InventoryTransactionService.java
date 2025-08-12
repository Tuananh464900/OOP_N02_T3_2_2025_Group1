package com.example.demo.service;

import com.example.demo.model.InventoryTransaction;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface InventoryTransactionService {
    List<InventoryTransaction> getAllTransactions();
    InventoryTransaction createTransaction(InventoryTransaction transaction);
    InventoryTransaction saveTransaction(InventoryTransaction transaction);
    void deleteTransaction(Long id);

    Page<InventoryTransaction> findAll(Pageable pageable);
    InventoryTransaction getTransactionById(Long id);
    Page<InventoryTransaction> search(String type, Long warehouseId, String productName, java.time.LocalDate start, java.time.LocalDate end, Pageable pageable);
}
