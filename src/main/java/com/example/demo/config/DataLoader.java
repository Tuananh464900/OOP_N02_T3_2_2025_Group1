package com.example.demo.config;

import com.example.demo.model.Product;
import com.example.demo.model.Warehouse;
import com.example.demo.repository.ProductRepository;
import com.example.demo.repository.WarehouseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements CommandLineRunner {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private WarehouseRepository warehouseRepository;

    @Override
    public void run(String... args) throws Exception {
        // Tạo dữ liệu mẫu nếu database trống
        if (productRepository.count() == 0) {
            loadSampleData();
        }
    }

    private void loadSampleData() {
        // Tạo kho mẫu
        Warehouse kho1 = new Warehouse();
        kho1.setCode("KHO001");
        kho1.setName("Kho Tổng");
        kho1.setLocation("Hà Nội");
        warehouseRepository.save(kho1);

        Warehouse kho2 = new Warehouse();
        kho2.setCode("KHO002");
        kho2.setName("Kho Chi nhánh");
        kho2.setLocation("TP. Hồ Chí Minh");
        warehouseRepository.save(kho2);

        // Tạo sản phẩm mẫu
        Product product1 = new Product();
        product1.setName("Laptop Dell Inspiron 15");
        product1.setCategory("Điện tử");
        product1.setDescription("Laptop văn phòng hiệu năng cao");
        product1.setDefaultPrice(15000000);
        product1.setSellPrice(18000000);
        product1.setUnit("Chiếc");
        product1.setMinStock(5);
        product1.setMaxStock(50);
        product1.setQuantity(20);
        productRepository.save(product1);

        Product product2 = new Product();
        product2.setName("Chuột không dây Logitech");
        product2.setCategory("Phụ kiện");
        product2.setDescription("Chuột không dây chất lượng cao");
        product2.setDefaultPrice(500000);
        product2.setSellPrice(650000);
        product2.setUnit("Chiếc");
        product2.setMinStock(10);
        product2.setMaxStock(100);
        product2.setQuantity(25);
        productRepository.save(product2);

        Product product3 = new Product();
        product3.setName("Bàn phím cơ Gaming");
        product3.setCategory("Phụ kiện");
        product3.setDescription("Bàn phím cơ cho game thủ");
        product3.setDefaultPrice(1200000);
        product3.setSellPrice(1500000);
        product3.setUnit("Chiếc");
        product3.setMinStock(3);
        product3.setMaxStock(30);
        product3.setQuantity(2); // Low stock để test warning
        productRepository.save(product3);

        Product product4 = new Product();
        product4.setName("Màn hình Samsung 24 inch");
        product4.setCategory("Điện tử");
        product4.setDescription("Màn hình Full HD chuyên nghiệp");
        product4.setDefaultPrice(3000000);
        product4.setSellPrice(3800000);
        product4.setUnit("Chiếc");
        product4.setMinStock(5);
        product4.setMaxStock(40);
        product4.setQuantity(15);
        productRepository.save(product4);

        Product product5 = new Product();
        product5.setName("USB 3.0 32GB");
        product5.setCategory("Lưu trữ");
        product5.setDescription("USB tốc độ cao");
        product5.setDefaultPrice(200000);
        product5.setSellPrice(280000);
        product5.setUnit("Chiếc");
        product5.setMinStock(20);
        product5.setMaxStock(200);
        product5.setQuantity(15); // Low stock để test warning
        productRepository.save(product5);

        System.out.println("✅ Đã tạo dữ liệu mẫu:");
        System.out.println("   - " + warehouseRepository.count() + " kho");
        System.out.println("   - " + productRepository.count() + " sản phẩm");
        System.out.println("🌐 Truy cập H2 Console tại: http://localhost:8088/h2-console");
        System.out.println("   JDBC URL: jdbc:h2:mem:warehouse_db");
        System.out.println("   Username: sa");
        System.out.println("   Password: (để trống)");
    }
}