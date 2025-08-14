# Ứng dụng: Xây dựng quản lý kho hàng


📋 Mục lục

Giới thiệu

Thành viên

Ứng dụng

Yêu cầu

Cài đặt--Chạy

Cấu trúc thư mục

Mô tả đối tượng

Ví dụ

Hướng phát triển


✨ Giới thiệu

Đây là ứng dụng Quản lý Kho Hàng được xây dựng theo hướng lập trình hướng đối tượng (OOP), cho phép:

Quản lý sản phẩm (Product)

Quản lý nhà kho: Kho hàng (Warehouse)

Giao dịch nhập/xuất (InventoryTransaction)

👥 Thành viên

Hồ Tuấn Anh (24100464) – Tuananh464900

🌐 Ứng dụng

Quản lý sản phẩm

Quản lý Nhà Kho

Giao dịch nhập/xuất kho


📆 Yêu cầu

Java 11 hoặc cao hơn

Maven hoặc IDE hỗ trợ (IntelliJ, Visual Studio Code…)

⚙️ Cài đặt & Chạy

git clone https://github.com/Tuananh464900/OOP_N02_T3_2_2025_Group1

🗂️ Cấu trúc thư mục
QLKH/  # Spring Boot Project
├─ src/
│  ├─ main/
│  │  ├─ java/com/example/demo/
│  │  │  ├─ controller/                        # Các Controller xử lý request
│  │  │  ├─ dto/                               # Data Transfer Object
│  │  │  ├─ exception/                         # Xử lý ngoại lệ
│  │  │  ├─ model/                             # Class mô hình dữ liệu
│  │  │  │  ├─ InventoryTransaction.java
│  │  │  │  ├─ Product.java
│  │  │  │  └─ Warehouse.java
│  │  │  ├─ repository/                        # Truy vấn DB (JPA Repository)
│  │  │  ├─ service/                           # Business logic
│  │  │  └─ DemoApplication.java               # Main()
│  │  ├─ resources/
│  │  │  ├─ static/                            # CSS, JS
│  │  │  │  ├─ css/app.css
│  │  │  │  └─ js/app.js
│  │  │  ├─ templates/                         # Thymeleaf HTML
│  │  │  │  ├─ dashboard.html
│  │  │  │  ├─ product-list.html
│  │  │  │  ├─ warehouse-list.html
│  │  │  │  └─ ...
│  │  │  ├─ application.properties             # Cấu hình ứng dụng
│  │  │  └─ application-cloud.yml
│  ├─ test/java/com/example/demo/              # Unit tests
│  │  ├─ DemoApplicationTests.java
│  │  └─ service/InventoryCoreServiceTests.java
│  └─ resources/application.properties         # Cấu hình test
├─ pom.xml                                     # Maven config
└─ README.md                                   # Tài liệu mô tả dự án


📚 Mô tả đối tượng

Product: Đại diện cho hàng hóa trong kho

Warehouse: Kho hàng

InventoryTransaction: Ghi nhận nhập hoặc xuất hàng

📖 Ví dụ

Product product1 = new Product("P001", "Laptop", "Electronics", "Cai", 10, 15000, 18000);

✨ Hướng phát triển

Thêm giao diện GUI

Kết nối cơ sở dữ liệu




