package com.example.demo.controller;

import com.example.demo.dto.StockRequest;
import com.example.demo.model.InventoryTransaction;
import com.example.demo.service.InventoryCoreService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/inventory/core")
@CrossOrigin
public class InventoryCoreController {

    private final InventoryCoreService coreService;

    public InventoryCoreController(InventoryCoreService coreService) {
        this.coreService = coreService;
    }

    @PostMapping("/receive")
    public InventoryTransaction receive(@RequestBody @Valid StockRequest req) {
        return coreService.receiveStock(req.getProductId(), req.getWarehouseId(),
                req.getQuantity(), req.getDate(), req.getNote());
    }

    @PostMapping("/issue")
    public InventoryTransaction issue(@RequestBody @Valid StockRequest req) {
        return coreService.issueStock(req.getProductId(), req.getWarehouseId(),
                req.getQuantity(), req.getDate(), req.getNote());
    }
}