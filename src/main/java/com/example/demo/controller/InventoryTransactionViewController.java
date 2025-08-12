package com.example.demo.controller;

import com.example.demo.model.InventoryTransaction;
import com.example.demo.service.InventoryCoreService;
import com.example.demo.service.InventoryTransactionService;
import com.example.demo.service.ProductService;
import com.example.demo.service.WarehouseService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.LocalDate;

@Controller
@RequestMapping("/inventorytransactions")
public class InventoryTransactionViewController {

    @Autowired private InventoryTransactionService transactionService;
    @Autowired private InventoryCoreService coreService;
    @Autowired private ProductService productService;
    @Autowired private WarehouseService warehouseService;

    @GetMapping
    public String list(@RequestParam(defaultValue = "0") int page,
                       @RequestParam(defaultValue = "10") int size,
                       @RequestParam(required = false) String type,
                       @RequestParam(required = false) Long warehouseId,
                       @RequestParam(required = false) String productName,
                       @RequestParam(required = false) String startDate,
                       @RequestParam(required = false) String endDate,
                       Model model) {
        Pageable pageable = PageRequest.of(page, size);
        LocalDate s = (startDate != null && !startDate.isBlank()) ? LocalDate.parse(startDate) : null;
        LocalDate e = (endDate != null && !endDate.isBlank()) ? LocalDate.parse(endDate) : null;
        Page<InventoryTransaction> data = transactionService.search(type, warehouseId, productName, s, e, pageable);

        model.addAttribute("type", type);
        model.addAttribute("warehouseId", warehouseId);
        model.addAttribute("productName", productName);
        model.addAttribute("startDate", startDate);
        model.addAttribute("endDate", endDate);
        model.addAttribute("warehouses", warehouseService.getAllWarehouses());
        model.addAttribute("page", data);
        return "inventory-list";
    }

    @GetMapping("/create")
    public String createForm(Model model) {
        model.addAttribute("transaction", new InventoryTransaction());
        model.addAttribute("products", productService.getAllProducts());
        model.addAttribute("warehouses", warehouseService.getAllWarehouses());
        return "inventory-form";
    }

    @GetMapping("/edit/{id}")
    public String editForm(@PathVariable Long id, RedirectAttributes ra, Model model) {
        InventoryTransaction t = transactionService.getTransactionById(id);
        if (t == null) {
            ra.addFlashAttribute("error", "Không tìm thấy giao dịch!");
            return "redirect:/inventorytransactions";
        }
        model.addAttribute("transaction", t);
        model.addAttribute("products", productService.getAllProducts());
        model.addAttribute("warehouses", warehouseService.getAllWarehouses());
        return "inventory-form";
    }

    @PostMapping("/save")
    public String save(@Valid @ModelAttribute("transaction") InventoryTransaction transaction,
                       BindingResult binding,
                       @RequestParam(required = false) Long productId,
                       @RequestParam(required = false) Long warehouseId,
                       @RequestParam(required = false) String transactionType,
                       @RequestParam(required = false) String transactionDate,
                       @RequestParam(required = false) Integer quantity,
                       @RequestParam(required = false) String notes,
                       RedirectAttributes ra,
                       Model model) {

        if (binding.hasErrors()) {
            model.addAttribute("products", productService.getAllProducts());
            model.addAttribute("warehouses", warehouseService.getAllWarehouses());
            return "inventory-form";
        }

        if (transaction.getId() == null) {
            LocalDate date = (transactionDate != null && !transactionDate.isBlank())
                    ? LocalDate.parse(transactionDate) : LocalDate.now();
            if ("RECEIVE".equalsIgnoreCase(transactionType)) {
                coreService.receiveStock(productId, warehouseId, quantity != null ? quantity : 0, date, notes);
            } else if ("ISSUE".equalsIgnoreCase(transactionType)) {
                coreService.issueStock(productId, warehouseId, quantity != null ? quantity : 0, date, notes);
            } else {
                ra.addFlashAttribute("error", "Loại giao dịch không hợp lệ!");
                return "redirect:/inventorytransactions";
            }
        } else {
            transactionService.saveTransaction(transaction);
        }

        ra.addFlashAttribute("success", "Lưu giao dịch thành công!");
        return "redirect:/inventorytransactions";
    }

    @PostMapping("/delete/{id}")
    public String delete(@PathVariable Long id, RedirectAttributes ra) {
        transactionService.deleteTransaction(id);
        ra.addFlashAttribute("success", "Xóa giao dịch thành công!");
        return "redirect:/inventorytransactions";
    }
}
