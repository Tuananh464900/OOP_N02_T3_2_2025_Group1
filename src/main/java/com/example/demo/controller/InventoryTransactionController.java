package com.example.demo.controller;

import com.example.demo.model.InventoryTransaction;
import com.example.demo.service.InventoryTransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/transactions")
@CrossOrigin
public class InventoryTransactionController {
    @Autowired
    private InventoryTransactionService service;

    @GetMapping
    public List<InventoryTransaction> getAll() {
        return service.getAllTransactions();
    }

    @GetMapping("/{id}")
    public InventoryTransaction getById(@PathVariable Long id) {
        // gọi đúng phương thức trong service
        return service.getTransactionById(id);
    }

    @PostMapping
    public InventoryTransaction create(@RequestBody InventoryTransaction transaction) {
        // gọi đúng phương thức trong service
        return service.createTransaction(transaction);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        // gọi đúng phương thức trong service
        service.deleteTransaction(id);
    }
}
