package com.example.demo.service;

import com.example.demo.model.InventoryTransaction;

import java.time.LocalDate;

public interface InventoryCoreService {
    InventoryTransaction receiveStock(Long productId, Long warehouseId, int qty, LocalDate date, String note);
    InventoryTransaction issueStock(Long productId, Long warehouseId, int qty, LocalDate date, String note);
}