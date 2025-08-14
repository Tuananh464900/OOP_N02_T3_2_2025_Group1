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

## 👥 Thành viên nhóm

| STT | Họ tên               | Mã SV    | GitHub |
| --- | -------------------- | ---------| ------- |
| 1   | Hồ Tuấn Anh          | 24100464 | [Tuananh464900](https://github.com/Tuananh464900) |
| 2   | Phùng Quốc Bình      | 24100241 |         |
| 3   | Nguyễn Lệ Thu        |          | [nglthu](https://github.com/nglthu) |

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

## 🧠 Phân tích đối tượng

---

### 1) 📦 Product (Sản phẩm)

**Thuộc tính**
- `id` (Long) – khóa chính
- `code` (String) – mã SP duy nhất
- `name` (String) – tên sản phẩm
- `category` (String) – danh mục
- `unit` (String) – đơn vị tính (cái, hộp, kg…)
- `costPrice` (BigDecimal) – giá nhập
- `sellPrice` (BigDecimal) – giá bán đề xuất
- `status` (Enum: ACTIVE / INACTIVE) – trạng thái kinh doanh
- `createdAt`, `updatedAt` (Timestamp)

**Chức năng**
- Đăng ký / tạo mới sản phẩm (validate `code` duy nhất)
- Hiển thị danh sách + phân trang, sắp xếp, lọc theo danh mục/trạng thái
- Chỉnh sửa thông tin sản phẩm
- Xóa (soft delete hoặc đổi `status`)
- Tìm kiếm theo mã, tên, danh mục
- (Tuỳ chọn) Báo cáo tồn theo từng kho

---

### 2) 🏬 Warehouse (Kho)

**Thuộc tính**
- `id` (Long) – khóa chính
- `code` (String) – mã kho duy nhất
- `name` (String) – tên kho
- `address` (String) – địa chỉ
- `manager` (String) – người phụ trách
- `phone` (String) – liên hệ
- `status` (Enum: ACTIVE / INACTIVE)
- `createdAt`, `updatedAt` (Timestamp)

**Chức năng**
- Tạo mới kho (validate `code` duy nhất)
- Hiển thị danh sách kho
- Chỉnh sửa thông tin kho
- Xóa kho (chỉ khi không còn tồn hoặc đã khóa nghiệp vụ)
- Tìm kiếm kho theo mã, tên, địa chỉ
- Xem tồn kho tổng quan theo từng kho

---

### 3) 🔄 InventoryTransaction (Giao dịch nhập/xuất)

**Thuộc tính**
- `id` (Long) – khóa chính
- `product` (FK → Product) – sản phẩm
- `warehouse` (FK → Warehouse) – kho
- `type` (Enum: IN / OUT) – loại giao dịch
- `quantity` (BigDecimal / Integer) – số lượng
- `unitPrice` (BigDecimal) – đơn giá (dùng cho nhập hoặc xuất)
- `totalAmount` (BigDecimal) – thành tiền (= `quantity * unitPrice`)
- `transDate` (LocalDateTime) – ngày giao dịch
- `note` (String) – ghi chú
- `createdBy` (String) – người lập
- `status` (Enum: COMPLETED / CANCELLED)

**Chức năng**
- Nhập kho (IN) / Xuất kho (OUT)
- Chỉnh sửa / huỷ phiếu giao dịch
- Tra cứu theo nhiều tiêu chí
- Báo cáo tồn kho, thẻ kho, xuất–nhập–tồn

## 🧱 Cấu trúc lớp và phân lớp

### Các lớp chính

- **Product**: thông tin sản phẩm
  - Thuộc tính tiêu biểu: `id`, `code`, `name`, `category`, `unit`, `costPrice`, `sellPrice`, `status`, `createdAt`, `updatedAt`
  - Vai trò: đại diện mặt hàng; dùng trong giao dịch nhập/xuất; hỗ trợ tra cứu & thống kê

- **Warehouse**: thông tin kho
  - Thuộc tính tiêu biểu: `id`, `code`, `name`, `address`, `manager`, `phone`, `status`, `createdAt`, `updatedAt`
  - Vai trò: nơi lưu trữ sản phẩm; kết hợp với giao dịch để theo dõi tồn theo từng kho

- **InventoryTransaction**: phiếu nhập/xuất kho
  - Thuộc tính tiêu biểu: `id`, `product` (FK → Product), `warehouse` (FK → Warehouse), `type` (IN/OUT), `quantity`, `unitPrice`, `totalAmount`, `transDate`, `note`, `createdBy`, `status`
  - Vai trò: ghi nhận nghiệp vụ nhập/xuất; cập nhật tồn; phục vụ báo cáo xuất–nhập–tồn

- **DemoApplication (Main)**: lớp chạy chính của ứng dụng Spring Boot
  - Vai trò: khởi động ứng dụng, nạp cấu hình & bean, khởi tạo context

 ## ✅ Kiểm thử chức năng

| Lớp | Chức năng kiểm thử chính |
| --- | ------------------------ |
| `Product` | Đăng ký, xóa, chỉnh sửa, hiển thị danh sách sản phẩm, tìm kiếm |
| `Warehouse` | Tạo mới kho, xóa, chỉnh sửa, hiển thị danh sách, tìm kiếm |
| `InventoryTransaction` | Tạo phiếu nhập/xuất kho, chỉnh sửa/huỷ, hiển thị danh sách, tìm kiếm |

## 🛠️ Chức năng chính

### 📦 Quản lý sản phẩm (Product)
- Thêm / Sửa / Xóa sản phẩm
- Hiển thị danh sách sản phẩm
- Tìm kiếm theo mã, tên, danh mục
- Cập nhật tồn kho khi nhập/xuất

### 🏬 Quản lý kho (Warehouse)
- Thêm / Sửa / Xóa kho
- Hiển thị danh sách kho
- Tìm kiếm theo mã kho, tên kho, địa chỉ

### 🔄 Quản lý nhập/xuất kho (InventoryTransaction)
- Tạo phiếu nhập (IN) / xuất (OUT)
- Cập nhật, chỉnh sửa, huỷ phiếu giao dịch
- Hiển thị danh sách giao dịch
- Tìm kiếm theo nhiều tiêu chí (thời gian, sản phẩm, kho, loại giao dịch)

### 💾 Lưu trữ dữ liệu
- Lưu dữ liệu vào **CSDL MySQL** thông qua Spring Data JPA
- Sử dụng **Entity, Repository, Service** để quản lý dữ liệu trong bộ nhớ và đồng bộ với DB
- Hỗ trợ **truy vấn phân trang, sắp xếp** và lọc dữ liệu

📖 Ví dụ

Product product1 = new Product("P001", "Laptop", "Electronics", "Cai", 10, 15000, 18000);

✨ Hướng phát triển

Thêm giao diện GUI

Kết nối cơ sở dữ liệu
## Sơ đồ hoạt động & Class Diagram

<img width="1628" height="871" alt="Class diagram" src="https://github.com/user-attachments/assets/6fd36c4f-2bb0-40a5-9f1a-b8d05458b427" />



