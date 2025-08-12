package com.example.demo.controller;

import com.example.demo.repository.InventoryTransactionRepository;
import com.example.demo.repository.ProductRepository;
import com.example.demo.repository.WarehouseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.time.LocalDate;

@Controller
@RequestMapping("/dashboard")
public class DashboardController {

    @Autowired private ProductRepository productRepository;
    @Autowired private WarehouseRepository warehouseRepository;
    @Autowired private InventoryTransactionRepository transactionRepository;

    @GetMapping
    public String dashboard(Model model){
        long productCount = productRepository.count();
        long warehouseCount = warehouseRepository.count();
        long transactionCount = transactionRepository.count();

        LocalDate today = LocalDate.now();
        long todayReceipt = transactionRepository.countByTransactionTypeIgnoreCaseAndTransactionDate("RECEIPT", today);
        long todayIssue = transactionRepository.countByTransactionTypeIgnoreCaseAndTransactionDate("ISSUE", today);

        model.addAttribute("productCount", productCount);
        model.addAttribute("warehouseCount", warehouseCount);
        model.addAttribute("transactionCount", transactionCount);
        model.addAttribute("todayReceipt", todayReceipt);
        model.addAttribute("todayIssue", todayIssue);

        // 5 báº£n ghi gáº§n nháº¥t
        model.addAttribute("recent", transactionRepository.findTop5ByOrderByTransactionDateDescIdDesc());
        return "dashboard";
    }
}