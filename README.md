# Ứng dụng: Xây dựng quản lý kho hàng


📋 Mục lục

[Giới thiệu]
[Thành viên]
[Phân tích đối tượng]
[cấu trúc thư mục]
[cấu trúc lớp và phân lớp]
[kiểm thử chức năng]
[Các chức năng chính]
[Class Diagram]
[Activity Diagram]
[Mô tả đối tượng]
[Giao diện chương trình]
[Chức năng nổi bật]
[Công nghệ sử dụng]
[Hướng phát triển]
[Cài đặt--Chạy]
[Tài liệu tham khảo]
✨ Giới thiệu

Đây là ứng dụng Quản lý Kho Hàng được xây dựng theo hướng lập trình hướng đối tượng (OOP), cho phép:

Quản lý sản phẩm (Product)
Quản lý nhà kho: Kho hàng (Warehouse)
Giao dịch nhập/xuất (InventoryTransaction)

👥 Thành viên
Họ tên	MSSV	GitHub
Hồ Tuấn Anh 24100464 @Tuananh464900
Phùng Quốc Bình 24100241 
Nguyễn Lệ Thu		@nglthu

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

### 📁 Cấu trúc thư mục

> **Lưu ý:** Dán *nguyên khối* dưới đây vào README. Khối mã (fenced code block) sẽ giữ nguyên khoảng trắng và không làm `#` biến thành heading.

```text
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
│  │  │  └─  application.properties             # Cấu hình ứng dụng
│  ├─ test/java/com/example/demo/              # Unit tests
│  │  ├─ DemoApplicationTests.java
│  │  └─ service/InventoryCoreServiceTests.java
│  └─ resources/application.properties         # Cấu hình test
├─ pom.xml                                     # Maven config
└─ README.md                                   # Tài liệu mô tả dự án
```

<details><summary>📂 Bản thu gọn (click để mở)</summary>

```text
QLKH/
├─ src/
│  ├─ main/…
│  ├─ test/…
├─ pom.xml
└─ README.md
```
</details>


📚 Mô tả đối tượng

Product: Đại diện cho hàng hóa trong kho

Warehouse: Kho hàng

InventoryTransaction: Ghi nhận nhập hoặc xuất hàng

📖 Ví dụ

Product product1 = new Product("P001", "Laptop", "Electronics", "Cai", 10, 15000, 18000);

✨ Hướng phát triển

Thêm giao diện GUI

Kết nối cơ sở dữ liệu




