package com.example.demo.service;

import com.example.demo.model.Warehouse;

import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface WarehouseService {
    List<Warehouse> getAllWarehouses();
    Warehouse createWarehouse(Warehouse warehouse);
    Warehouse getWarehouseById(Long id);
    void deleteWarehouse(Long id);

    Page<Warehouse> findAll(Pageable pageable);
    Page<Warehouse> findByName(String q, Pageable pageable);
    Page<Warehouse> findByCode(String q, Pageable pageable);

}
