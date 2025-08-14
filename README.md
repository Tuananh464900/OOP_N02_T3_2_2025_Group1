# Ứng dụng: Xây dựng quản lý kho hàng

## 📋 Mục lục

- [Giới thiệu]
- [Thành viên]
- [Phân tích đối tượng]
- [Cấu trúc thư mục]
- [Cấu trúc lớp và phân lớp]
- [Kiểm thử chức năng]
- [Các chức năng chính]
- [Class Diagram]
- [Activity Diagram]
- [Mô tả đối tượng]
- [Giao diện chương trình]
- [Chức năng nổi bật]
- [Công nghệ sử dụng]
- [Hướng phát triển]
- [Cài đặt & Chạy]
- [Tài liệu tham khảo]
  
## ✨ Giới thiệu

Đây là ứng dụng **Quản lý Kho Hàng** được xây dựng theo hướng **lập trình hướng đối tượng (OOP)**, cho phép:

- Quản lý sản phẩm (**Product**)
- Quản lý nhà kho (**Warehouse**)
- Quản lý giao dịch nhập/xuất (**InventoryTransaction**)


## 👥 Thành viên nhóm

| STT | Họ tên               | Mã SV    | GitHub |
| --- | -------------------- | ---------| ------- |
| 1   | Hồ Tuấn Anh          | 24100464 | [Tuananh464900](https://github.com/Tuananh464900) |
| 2   | Phùng Quốc Bình      | 24100241 |         |
| 3   | Nguyễn Lệ Thu        |          | [nglthu](https://github.com/nglthu) |

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

### 📁 Cấu trúc thư mục


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


## Sơ đồ hoạt động & Class Diagram


1.📊 Biểu đồ lớp (Class Diagram)
<img width="1628" height="871" alt="Class diagram" src="https://github.com/user-attachments/assets/8447c2c0-691f-4b3f-bc95-def714d5efdf" />
2.📊 Đăng nhập (Activity Diagram)
<img width="664" height="1157" alt="activity diagram (5)" src="https://github.com/user-attachments/assets/230ea7d3-0a42-451f-a8bf-09a01ede697a" />
                   3.📊 Tìm sản phẩm (Activity Diagram)
<img width="1100" height="1012" alt="activity diagram (2)" src="https://github.com/user-attachments/assets/206cae78-b50e-4d2b-ab92-bcfa3f25dc66" />
4.📊 Xóa sản phẩm (Activity Diagram)
<img width="1270" height="1082" alt="activity diagram (1)" src="https://github.com/user-attachments/assets/f1d3d45d-54d5-492d-be22-41d3097083f1" /> 
5.📊 Thêm sản phẩm (Activity Diagram)
<img width="1057" height="1064" alt="activity diagram (3)" src="https://github.com/user-attachments/assets/35e6d287-6eeb-4187-ab6c-92909b1ea0b8" />   


## 📚 Mô tả đối tượng

### 1. Product
**Thuộc tính:**
- `productID`: Mã sản phẩm
- `productName`: Tên sản phẩm
- `quantity`: Số lượng
- `price`: Giá bán
- `category`: Loại sản phẩm
- `unit`: Đơn vị tính
- `importPrice`: Giá nhập
- `exportPrice`: Giá xuất

**Hành vi:**
- `createProduct(Product)`: Thêm sản phẩm mới
- `updateProduct(Product)`: Cập nhật thông tin sản phẩm
- `deleteProduct(productID)`: Xóa sản phẩm
- `displayProduct()`: Hiển thị thông tin sản phẩm

---

### 2. Warehouse
**Thuộc tính:**
- `warehouseID`: Mã kho
- `warehouseName`: Tên kho
- `address`: Địa chỉ

**Hành vi:**
- `createWarehouse(Warehouse)`: Thêm kho mới
- `updateWarehouse(Warehouse)`: Cập nhật thông tin kho
- `deleteWarehouse(warehouseID)`: Xóa kho
- `displayWarehouse()`: Hiển thị thông tin kho

---

### 3. InventoryTransaction
**Thuộc tính:**
- `transactionID`: Mã giao dịch
- `productID`: Mã sản phẩm
- `warehouseID`: Mã kho
- `quantity`: Số lượng
- `transactionDate`: Ngày giao dịch
- `transactionType`: Loại giao dịch (*Import* / *Export*)

**Hành vi:**
- `createTransaction(InventoryTransaction)`: Tạo giao dịch mới
- `displayTransaction()`: Hiển thị thông tin giao dịch
- `isStockAvailable(productID, quantity)`: Kiểm tra tồn kho

## Giao diện Chương trình
<img width="2559" height="1038" alt="image" src="https://github.com/user-attachments/assets/c28d4f67-e93d-4419-838b-6b89ff2c346f" />


## 🌟 Chức năng nổi bật
- CRUD đầy đủ cho **Product**, **Warehouse**, **InventoryTransaction**
- **Nhập/Xuất kho**: tự động cập nhật tồn kho theo giao dịch
- **Tìm kiếm/Lọc** theo mã, tên, danh mục; sắp xếp & phân trang
- **Dashboard**: tổng quan số liệu, giao dịch gần đây
- **Xử lý lỗi** tập trung (GlobalExceptionHandler), thông báo rõ ràng trên giao diện
- **Kiểm thử**: unit test tối thiểu cho service cốt lõi

## 💡 Công nghệ sử dụng
- **Ngôn ngữ**: Java 17
- **Framework**: Spring Boot 3 (Web, Data JPA, Validation)
- **Kiến trúc**: MVC (Controller – Service – Repository – Entity – View)
- **View**: Thymeleaf, Bootstrap
- **CSDL**: MySQL (local) / **Cloud MySQL** (profile `prod`)
- **Build & Dependency**: Maven
- **Khác**: Lombok, HikariCP
- **Tài liệu**: Class Diagram & Activity Diagram (đính kèm trong `images/`)

## 🚀 Hướng phát triển
- **Bảo mật**: Đăng nhập/đăng xuất, phân quyền (Admin/Staff) với Spring Security
- **Danh mục (Category)**: module quản lý riêng, liên kết Product–Category
- **Báo cáo/Thống kê**: biểu đồ doanh thu/thống kê theo tháng (Chart.js)
- **Triển khai**: Demo chạy công khai (Render/Railway) dùng **Cloud MySQL**
- **API**: Mở rộng RESTful API cho tích hợp hệ thống khác
- **Kiểm thử**: tăng coverage (service/repository), UI test cơ bản

##⚙️ Cài đặt & Chạy
git clone :https://github.com/Tuananh464900/OOP_N02_T3_2_2025_Group1
Mở project bằng IDE 


##📚 Tài liệu tham khảo
Slide bài giảng môn Lập trình Hướng Đối Tượng – GVHD: TS.Nguyễn Lệ Thu
Java Docs – Oracle
Stack Overflow – Community



