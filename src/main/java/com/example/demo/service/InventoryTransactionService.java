package com.example.demo.service;

import com.example.demo.model.InventoryTransaction;
import java.util.List;

public interface InventoryTransactionService {
    List<InventoryTransaction> getAllTransactions();

    // thêm phương thức lấy theo ID
    InventoryTransaction getTransactionById(Long id);

    // thêm phương thức tạo mới
    InventoryTransaction createTransaction(InventoryTransaction tx);

    void deleteTransaction(Long id);
}
