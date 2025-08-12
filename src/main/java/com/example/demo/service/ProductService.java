package com.example.demo.service;

import com.example.demo.model.Product;

import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ProductService {
    List<Product> getAllProducts();
    Product createProduct(Product product);
    Product saveProduct(Product product);
    Product getProductById(Long id);
    void deleteProduct(Long id);

    Page<Product> findAll(Pageable pageable);
    Page<Product> findByName(String q, Pageable pageable);

}
