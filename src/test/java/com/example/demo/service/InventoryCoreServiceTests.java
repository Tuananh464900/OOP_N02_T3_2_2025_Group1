package com.example.demo.service;

import com.example.demo.exception.BusinessException;
import com.example.demo.model.Product;
import com.example.demo.model.Warehouse;
import com.example.demo.repository.InventoryTransactionRepository;
import com.example.demo.repository.ProductRepository;
import com.example.demo.repository.WarehouseRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@Import(InventoryCoreServiceImpl.class)
class InventoryCoreServiceTests {

    @Autowired ProductRepository productRepository;
    @Autowired WarehouseRepository warehouseRepository;
    @Autowired InventoryTransactionRepository transactionRepository;
    @Autowired InventoryCoreServiceImpl core;

    Product p; Warehouse w;

    @BeforeEach
    void setup(){
        p = new Product();
        p.setName("Demo");
        p.setMinStock(0);
        p.setMaxStock(100);
        p.setQuantity(10);
        productRepository.save(p);

        w = new Warehouse();
        w.setCode("W1");
        w.setName("Kho 1");
        w.setLocation("HN");
        warehouseRepository.save(w);
    }

    @Test
    void receive_increases_stock_and_writes_transaction(){
        core.receiveStock(p.getId(), w.getId(), 5, null, "nhap demo");
        Product updated = productRepository.findById(p.getId()).orElseThrow();
        assertEquals(15, updated.getQuantity());
        assertEquals(1, transactionRepository.count());
    }

    @Test
    void issue_fails_when_not_enough_stock(){
        assertThrows(BusinessException.class, () -> core.issueStock(p.getId(), w.getId(), 20, null, "xuat"));
    }

    @Test
    void issue_ok_when_enough_stock(){
        core.issueStock(p.getId(), w.getId(), 5, null, "xuat demo");
        Product updated = productRepository.findById(p.getId()).orElseThrow();
        assertEquals(5, updated.getQuantity());
    }
}