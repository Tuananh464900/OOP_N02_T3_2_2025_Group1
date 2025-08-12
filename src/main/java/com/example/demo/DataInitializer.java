package com.example.demo;

import com.example.demo.model.Product;
import com.example.demo.model.Warehouse;
import com.example.demo.repository.ProductRepository;
import com.example.demo.repository.WarehouseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer implements CommandLineRunner {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private WarehouseRepository warehouseRepository;

    @Override
    public void run(String... args) throws Exception {
        try {
            // Check if data already exists
            if (productRepository.count() > 0) {
                System.out.println("Data already exists, skipping initialization.");
                return;
            }

            // Create sample warehouse
            Warehouse warehouse = new Warehouse();
            warehouse.setCode("WH001");
            warehouse.setName("Kho chính");
            warehouse.setLocation("123 Đường ABC, Quận 1, TP.HCM");
            warehouseRepository.save(warehouse);

            // Create sample products
            Product product1 = new Product("Laptop Dell Inspiron", 50, 15000000);
            product1.setCategory("Điện tử");
            product1.setDescription("Laptop Dell Inspiron 15 inch, RAM 8GB, SSD 256GB");
            product1.setSellPrice(17000000);
            product1.setUnit("cái");
            product1.setMinStock(10);
            product1.setMaxStock(100);
            productRepository.save(product1);

            Product product2 = new Product("Chuột không dây Logitech", 100, 200000);
            product2.setCategory("Phụ kiện");
            product2.setDescription("Chuột không dây Logitech M185, pin AAA");
            product2.setSellPrice(250000);
            product2.setUnit("cái");
            product2.setMinStock(20);
            product2.setMaxStock(200);
            productRepository.save(product2);

            Product product3 = new Product("Bàn phím cơ Logitech", 30, 800000);
            product3.setCategory("Phụ kiện");
            product3.setDescription("Bàn phím cơ Logitech G Pro X, switch Blue");
            product3.setSellPrice(950000);
            product3.setUnit("cái");
            product3.setMinStock(5);
            product3.setMaxStock(50);
            productRepository.save(product3);

            System.out.println("Sample data initialized successfully!");
        } catch (Exception e) {
            System.err.println("Error initializing data: " + e.getMessage());
            e.printStackTrace();
        }
    }
}