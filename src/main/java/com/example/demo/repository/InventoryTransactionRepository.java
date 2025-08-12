package com.example.demo.repository;

import com.example.demo.model.InventoryTransaction;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface InventoryTransactionRepository extends JpaRepository<InventoryTransaction, Long> {
    long countByTransactionTypeIgnoreCaseAndTransactionDate(String type, LocalDate date);
    List<InventoryTransaction> findTop5ByOrderByTransactionDateDescIdDesc();
    Page<InventoryTransaction> findAll(Pageable pageable);
    Page<InventoryTransaction> findByTransactionTypeIgnoreCase(String type, Pageable pageable);
    Page<InventoryTransaction> findByWarehouse_Id(Long warehouseId, Pageable pageable);
    Page<InventoryTransaction> findByProductNameContainingIgnoreCase(String name, Pageable pageable);
    Page<InventoryTransaction> findByTransactionDateBetween(LocalDate start, LocalDate end, Pageable pageable);
    Page<InventoryTransaction> findByTransactionTypeIgnoreCaseAndWarehouse_IdAndTransactionDateBetween(String type, Long warehouseId, LocalDate start, LocalDate end, Pageable pageable);
}
