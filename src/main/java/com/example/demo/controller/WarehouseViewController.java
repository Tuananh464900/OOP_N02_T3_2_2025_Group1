package com.example.demo.controller;

import com.example.demo.model.Warehouse;
import com.example.demo.service.WarehouseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/warehouses")
public class WarehouseViewController {

    @Autowired
    private WarehouseService warehouseService;

    @GetMapping
    public String listWarehouses(Model model) {
        model.addAttribute("warehouses", warehouseService.getAllWarehouses());
        return "warehouse-list";
    }

    @GetMapping("/create")
    public String showCreateForm(Model model) {
        model.addAttribute("warehouse", new Warehouse());
        return "warehouse-form";
    }

    @PostMapping("/save")
    public String saveWarehouse(@ModelAttribute Warehouse warehouse) {
        warehouseService.createWarehouse(warehouse);
        return "redirect:/warehouses";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        Warehouse warehouse = warehouseService.getWarehouseById(id);
        model.addAttribute("warehouse", warehouse);
        return "warehouse-form";
    }

    @GetMapping("/delete/{id}")
    public String deleteWarehouse(@PathVariable Long id) {
        warehouseService.deleteWarehouse(id);
        return "redirect:/warehouses";
    }
}
