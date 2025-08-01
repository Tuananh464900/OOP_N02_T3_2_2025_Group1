package com.example.demo.service;

import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.InventoryTransaction;
import com.example.demo.model.Product;
import com.example.demo.model.Warehouse;
import com.example.demo.repository.InventoryTransactionRepository;
import com.example.demo.repository.ProductRepository;
import com.example.demo.repository.WarehouseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class InventoryTransactionServiceImpl implements InventoryTransactionService {

    @Autowired
    private InventoryTransactionRepository repo;

    @Autowired
    private ProductRepository productRepo;

    @Autowired
    private WarehouseRepository warehouseRepo;

    @Override
    public List<InventoryTransaction> getAllTransactions() {
        return repo.findAll();
    }

    @Override
    public InventoryTransaction getTransactionById(Long id) {
        return repo.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException(
                "InventoryTransaction", "id", id));
    }

    @Override
    @Transactional
    public InventoryTransaction createTransaction(InventoryTransaction tx) {
        // Load and attach managed Product entity
        Long pid = tx.getProduct().getId();
        Product p = productRepo.findById(pid)
            .orElseThrow(() -> new ResourceNotFoundException(
                "Product", "id", pid));

        // Load and attach managed Warehouse entity
        Long wid = tx.getWarehouse().getId();
        Warehouse w = warehouseRepo.findById(wid)
            .orElseThrow(() -> new ResourceNotFoundException(
                "Warehouse", "id", wid));

        tx.setProduct(p);
        tx.setWarehouse(w);

        return repo.save(tx);
    }

    @Override
    public void deleteTransaction(Long id) {
        InventoryTransaction existing = getTransactionById(id);
        repo.delete(existing);
    }
}
