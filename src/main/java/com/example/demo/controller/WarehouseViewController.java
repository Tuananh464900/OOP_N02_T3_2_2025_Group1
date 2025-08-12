package com.example.demo.controller;

import com.example.demo.model.Warehouse;
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

@Controller
@RequestMapping("/warehouses")
public class WarehouseViewController {

    @Autowired
    private WarehouseService warehouseService;

    @GetMapping
    public String list(@RequestParam(defaultValue = "0") int page,
                       @RequestParam(defaultValue = "10") int size,
                       @RequestParam(required = false) String q,
                       @RequestParam(required = false) String field,
                       Model model) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Warehouse> data;
        if (q == null || q.isBlank()) {
            data = warehouseService.findAll(pageable);
        } else if ("code".equalsIgnoreCase(field)) {
            data = warehouseService.findByCode(q, pageable);
        } else {
            data = warehouseService.findByName(q, pageable);
        }
        model.addAttribute("page", data);
        model.addAttribute("q", q);
        model.addAttribute("field", field);
        return "warehouse-list";
    }

    @GetMapping("/create")
    public String createForm(Model model) {
        model.addAttribute("warehouse", new Warehouse());
        return "warehouse-form";
    }

    @GetMapping("/edit/{id}")
    public String editForm(@PathVariable Long id, RedirectAttributes ra, Model model) {
        Warehouse w = warehouseService.getWarehouseById(id);
        if (w == null) {
            ra.addFlashAttribute("error", "Không tìm thấy kho!");
            return "redirect:/warehouses";
        }
        model.addAttribute("warehouse", w);
        return "warehouse-form";
    }

    @PostMapping("/save")
    public String save(@Valid @ModelAttribute("warehouse") Warehouse warehouse,
                       BindingResult binding,
                       RedirectAttributes ra) {
        if (binding.hasErrors()) return "warehouse-form";
        if (warehouse.getId() == null) {
            warehouseService.createWarehouse(warehouse);
            ra.addFlashAttribute("success", "Tạo kho thành công!");
        } else {
            warehouseService.createWarehouse(warehouse); // dùng như saveOrUpdate
            ra.addFlashAttribute("success", "Cập nhật kho thành công!");
        }
        return "redirect:/warehouses";
    }

    @PostMapping("/delete/{id}")
    public String delete(@PathVariable Long id, RedirectAttributes ra) {
        warehouseService.deleteWarehouse(id);
        ra.addFlashAttribute("success", "Xóa kho thành công!");
        return "redirect:/warehouses";
    }
}
