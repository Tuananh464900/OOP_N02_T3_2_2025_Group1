package com.example.demo.controller;

import com.example.demo.model.InventoryTransaction;
import com.example.demo.service.InventoryTransactionService;
import com.example.demo.service.ProductService;
import com.example.demo.service.WarehouseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/inventory")
public class InventoryTransactionViewController {

    @Autowired private InventoryTransactionService inventoryService;
    @Autowired private ProductService productService;
    @Autowired private WarehouseService warehouseService;

    // 1. Hiển thị danh sách
    @GetMapping
    public String listTransactions(Model model) {
        model.addAttribute("transactions", inventoryService.getAllTransactions());
        return "transaction-list";
    }

    // 2. Form tạo mới
    @GetMapping("/create")
    public String showCreateForm(Model model) {
        model.addAttribute("transaction", new InventoryTransaction());
        model.addAttribute("products", productService.getAllProducts());
        model.addAttribute("warehouses", warehouseService.getAllWarehouses());
        return "transaction-form";
    }

    // 3. Lưu (cả create & update)
  @PostMapping("/save")
public String saveTransaction(@ModelAttribute("transaction") InventoryTransaction tx) {
    System.out.println(">>> Received tx from form: " + tx);
    InventoryTransaction saved = inventoryService.createTransaction(tx);
    System.out.println(">>> Saved tx id: " + saved.getId());
    return "redirect:/inventory";
}


    // 4. Form chỉnh sửa
    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        InventoryTransaction tx = inventoryService.getTransactionById(id);
        model.addAttribute("transaction", tx);
        model.addAttribute("products", productService.getAllProducts());
        model.addAttribute("warehouses", warehouseService.getAllWarehouses());
        return "transaction-form";
    }

    // 5. Xóa
    @GetMapping("/delete/{id}")
    public String deleteTransaction(@PathVariable Long id) {
        inventoryService.deleteTransaction(id);
        return "redirect:/inventory";
    }
}
