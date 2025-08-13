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
        try {
            Pageable pageable = PageRequest.of(page, size);
            Page<Product> data = (q == null || q.isBlank())
                    ? productService.findAll(pageable)
                    : productService.findByName(q, pageable);
            
            model.addAttribute("page", data);
            model.addAttribute("q", q);
            model.addAttribute("title", "Sản phẩm - Warehouse App");
            model.addAttribute("pageTitle", "Danh sách sản phẩm");
            
            return "product-list";
        } catch (Exception e) {
            System.err.println("Error in ProductViewController.list: " + e.getMessage());
            e.printStackTrace();
            model.addAttribute("error", "Không thể tải danh sách sản phẩm: " + e.getMessage());
            return "error";
        }
    }

    @GetMapping("/create")
    public String createForm(Model model) {
        try {
            model.addAttribute("product", new Product());
            model.addAttribute("title", "Thêm sản phẩm - Warehouse App");
            model.addAttribute("pageTitle", "Thêm sản phẩm mới");
            return "product-form";
        } catch (Exception e) {
            System.err.println("Error in ProductViewController.createForm: " + e.getMessage());
            e.printStackTrace();
            model.addAttribute("error", "Không thể tải form tạo sản phẩm: " + e.getMessage());
            return "error";
        }
    }

    @GetMapping("/edit/{id}")
    public String editForm(@PathVariable Long id, RedirectAttributes ra, Model model) {
        try {
            Product p = productService.getProductById(id);
            if (p == null) {
                ra.addFlashAttribute("error", "Không tìm thấy sản phẩm!");
                return "redirect:/products";
            }
            model.addAttribute("product", p);
            model.addAttribute("title", "Sửa sản phẩm - Warehouse App");
            model.addAttribute("pageTitle", "Sửa sản phẩm: " + p.getName());
            return "product-form";
        } catch (Exception e) {
            System.err.println("Error in ProductViewController.editForm: " + e.getMessage());
            e.printStackTrace();
            ra.addFlashAttribute("error", "Không thể tải thông tin sản phẩm: " + e.getMessage());
            return "redirect:/products";
        }
    }

    @PostMapping("/save")
    public String save(@Valid @ModelAttribute("product") Product product,
                       BindingResult binding,
                       RedirectAttributes ra,
                       Model model) {
        try {
            if (binding.hasErrors()) {
                model.addAttribute("title", product.getId() == null ? "Thêm sản phẩm - Warehouse App" : "Sửa sản phẩm - Warehouse App");
                model.addAttribute("pageTitle", product.getId() == null ? "Thêm sản phẩm mới" : "Sửa sản phẩm");
                return "product-form";
            }
            
            if (product.getId() == null) {
                productService.createProduct(product);
                ra.addFlashAttribute("success", "Tạo sản phẩm thành công!");
            } else {
                productService.saveProduct(product);
                ra.addFlashAttribute("success", "Cập nhật sản phẩm thành công!");
            }
            return "redirect:/products";
        } catch (Exception e) {
            System.err.println("Error in ProductViewController.save: " + e.getMessage());
            e.printStackTrace();
            model.addAttribute("error", "Không thể lưu sản phẩm: " + e.getMessage());
            model.addAttribute("title", product.getId() == null ? "Thêm sản phẩm - Warehouse App" : "Sửa sản phẩm - Warehouse App");
            model.addAttribute("pageTitle", product.getId() == null ? "Thêm sản phẩm mới" : "Sửa sản phẩm");
            return "product-form";
        }
    }

    @PostMapping("/delete/{id}")
    public String delete(@PathVariable Long id, RedirectAttributes ra) {
        try {
            Product product = productService.getProductById(id);
            if (product == null) {
                ra.addFlashAttribute("error", "Không tìm thấy sản phẩm để xóa!");
                return "redirect:/products";
            }
            
            productService.deleteProduct(id);
            ra.addFlashAttribute("success", "Xóa sản phẩm '" + product.getName() + "' thành công!");
            return "redirect:/products";
        } catch (Exception e) {
            System.err.println("Error in ProductViewController.delete: " + e.getMessage());
            e.printStackTrace();
            ra.addFlashAttribute("error", "Không thể xóa sản phẩm: " + e.getMessage());
            return "redirect:/products";
        }
    }
}
