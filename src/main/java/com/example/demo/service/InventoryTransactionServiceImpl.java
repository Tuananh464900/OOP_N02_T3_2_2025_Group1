package com.example.demo.service;

import com.example.demo.model.InventoryTransaction;
import com.example.demo.repository.InventoryTransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDate;
import java.util.List;

@Service
public class InventoryTransactionServiceImpl implements InventoryTransactionService {

    @Autowired
    private InventoryTransactionRepository transactionRepository;

    @Override
    public List<InventoryTransaction> getAllTransactions() {
        return transactionRepository.findAll();
    }

    @Override
    public InventoryTransaction createTransaction(InventoryTransaction transaction) {
        return transactionRepository.save(transaction);
    }

    @Override
    public InventoryTransaction saveTransaction(InventoryTransaction transaction) {
        return transactionRepository.save(transaction);
    }

    @Override
    public void deleteTransaction(Long id) {
        transactionRepository.deleteById(id);
    }

    @Override
    public Page<InventoryTransaction> findAll(Pageable pageable) {
        return transactionRepository.findAll(pageable);
    }

    @Override
    public InventoryTransaction getTransactionById(Long id) {
        return transactionRepository.findById(id).orElse(null);
    }

    @Override
    public Page<InventoryTransaction> search(String type, Long warehouseId, String productName,
                                             LocalDate start, LocalDate end, Pageable pageable) {
        boolean hasType = type != null && !type.isBlank();
        boolean hasWh = warehouseId != null;
        boolean hasProd = productName != null && !productName.isBlank();
        boolean hasDate = start != null && end != null;

        if (hasType && !hasWh && !hasProd && !hasDate) {
            return transactionRepository.findByTransactionTypeIgnoreCase(type, pageable);
        }
        if (hasWh && !hasType && !hasProd && !hasDate) {
            return transactionRepository.findByWarehouse_Id(warehouseId, pageable);
        }
        if (hasProd && !hasType && !hasWh && !hasDate) {
            return transactionRepository.findByProductNameContainingIgnoreCase(productName, pageable);
        }
        if (hasDate && !hasType && !hasWh && !hasProd) {
            return transactionRepository.findByTransactionDateBetween(start, end, pageable);
        }
        return transactionRepository.findAll(pageable);
    }
}
