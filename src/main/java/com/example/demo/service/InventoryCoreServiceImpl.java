package com.example.demo.service;

import com.example.demo.exception.BusinessException;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.InventoryTransaction;
import com.example.demo.model.Product;
import com.example.demo.model.Warehouse;
import com.example.demo.repository.InventoryTransactionRepository;
import com.example.demo.repository.ProductRepository;
import com.example.demo.repository.WarehouseRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

@Service
public class InventoryCoreServiceImpl implements InventoryCoreService {

    private final ProductRepository productRepository;
    private final WarehouseRepository warehouseRepository;
    private final InventoryTransactionRepository transactionRepository;

    public InventoryCoreServiceImpl(ProductRepository productRepository,
                                    WarehouseRepository warehouseRepository,
                                    InventoryTransactionRepository transactionRepository) {
        this.productRepository = productRepository;
        this.warehouseRepository = warehouseRepository;
        this.transactionRepository = transactionRepository;
    }

    @Override
    @Transactional
    public InventoryTransaction receiveStock(Long productId, Long warehouseId, int qty, LocalDate date, String note) {
        if (qty <= 0) throw new BusinessException("Số lượng nhập phải > 0");

        Product p = productRepository.findById(productId)
                .orElseThrow(() -> new ResourceNotFoundException("Product", "id", productId));
        Warehouse w = warehouseRepository.findById(warehouseId)
                .orElseThrow(() -> new ResourceNotFoundException("Warehouse", "id", warehouseId));

        p.setQuantity(p.getQuantity() + qty);
        productRepository.save(p);

        InventoryTransaction t = new InventoryTransaction();
        t.setProductName(p.getName());
        t.setQuantity(qty);
        t.setTransactionType("RECEIVE");
        t.setTransactionDate(date != null ? date : LocalDate.now());
        t.setWarehouse(w);
        t.setNotes(note);
        return transactionRepository.save(t);
    }

    @Override
    @Transactional
    public InventoryTransaction issueStock(Long productId, Long warehouseId, int qty, LocalDate date, String note) {
        if (qty <= 0) throw new BusinessException("Số lượng xuất phải > 0");

        Product p = productRepository.findById(productId)
                .orElseThrow(() -> new ResourceNotFoundException("Product", "id", productId));
        Warehouse w = warehouseRepository.findById(warehouseId)
                .orElseThrow(() -> new ResourceNotFoundException("Warehouse", "id", warehouseId));

        int newQty = p.getQuantity() - qty;
        if (newQty < 0) throw new BusinessException("Không đủ tồn để xuất");
        if (p.getMinStock() > 0 && newQty < p.getMinStock()) {
            throw new BusinessException("Xuất sẽ làm tồn thấp hơn mức tối thiểu của sản phẩm " + p.getName());
        }

        p.setQuantity(newQty);
        productRepository.save(p);

        InventoryTransaction t = new InventoryTransaction();
        t.setProductName(p.getName());
        t.setQuantity(qty);
        t.setTransactionType("ISSUE");
        t.setTransactionDate(date != null ? date : LocalDate.now());
        t.setWarehouse(w);
        t.setNotes(note);
        return transactionRepository.save(t);
    }
}
