package com.example.demo.controller;

import com.example.demo.model.Product;
import com.example.demo.service.ProductService;
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
@RequestMapping("/products")
public class ProductViewController {

    @Autowired
    private ProductService productService;

    @GetMapping
    public String list(@RequestParam(defaultValue = "0") int page,
                       @RequestParam(defaultValue = "10") int size,
                       @RequestParam(required = false) String q,
                       Model model) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Product> data = (q == null || q.isBlank())
                ? productService.findAll(pageable)
                : productService.findByName(q, pageable);
        model.addAttribute("page", data);
        model.addAttribute("q", q);
        return "product-list";
    }

    @GetMapping("/create")
    public String createForm(Model model) {
        model.addAttribute("product", new Product());
        return "product-form";
    }

    @GetMapping("/edit/{id}")
    public String editForm(@PathVariable Long id, RedirectAttributes ra, Model model) {
        Product p = productService.getProductById(id);
        if (p == null) {
            ra.addFlashAttribute("error", "Không tìm thấy sản phẩm!");
            return "redirect:/products";
        }
        model.addAttribute("product", p);
        return "product-form";
    }

    @PostMapping("/save")
    public String save(@Valid @ModelAttribute("product") Product product,
                       BindingResult binding,
                       RedirectAttributes ra) {
        if (binding.hasErrors()) return "product-form";
        if (product.getId() == null) {
            productService.createProduct(product);
            ra.addFlashAttribute("success", "Tạo sản phẩm thành công!");
        } else {
            productService.saveProduct(product);
            ra.addFlashAttribute("success", "Cập nhật sản phẩm thành công!");
        }
        return "redirect:/products";
    }

    @PostMapping("/delete/{id}")
    public String delete(@PathVariable Long id, RedirectAttributes ra) {
        productService.deleteProduct(id);
        ra.addFlashAttribute("success", "Xóa sản phẩm thành công!");
        return "redirect:/products";
    }
}
