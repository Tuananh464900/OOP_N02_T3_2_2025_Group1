# Warehouse Management System - Hệ thống Quản lý Kho

## Mô tả
Ứng dụng web quản lý kho với giao diện hiện đại, thân thiện người dùng và đầy đủ tính năng. Giao diện được thiết kế theo yêu cầu với header, sidebar navigation và dashboard chính.

## Tính năng chính

### 🎯 Dashboard Overview
- **Tổng sản phẩm**: Hiển thị tổng số sản phẩm trong hệ thống
- **Số kho**: Tổng số kho hàng đang hoạt động
- **Sắp hết hàng**: Cảnh báo sản phẩm có lượng tồn kho thấp
- **Sắp hết hạn**: Cảnh báo sản phẩm sắp đến hạn sử dụng

### 🧭 Navigation Menu
- **Tổng quan**: Trang dashboard chính
- **Sản phẩm**: Quản lý danh sách sản phẩm
- **Tồn kho**: Theo dõi lượng tồn kho
- **Nhập kho**: Quản lý quy trình nhập hàng
- **Xuất kho**: Quản lý quy trình xuất hàng
- **Báo cáo**: Xem báo cáo và thống kê
- **Người dùng**: Quản lý người dùng hệ thống

### 🎨 Giao diện
- Header với các công cụ: Preview, Code View, Publish, Search, Notifications
- Sidebar navigation với thiết kế tối giản
- Dashboard responsive với các metric cards
- Hỗ trợ đa ngôn ngữ (Tiếng Việt)
- Thiết kế responsive cho mobile và desktop

## Cài đặt và chạy

### Yêu cầu hệ thống
- Trình duyệt web hiện đại (Chrome, Firefox, Safari, Edge)
- Không cần cài đặt thêm phần mềm

### Cách chạy
1. Tải xuống tất cả file của dự án
2. Mở file `index.html` trong trình duyệt web
3. Hoặc sử dụng local server:
   ```bash
   # Sử dụng Python
   python -m http.server 8000
   
   # Sử dụng Node.js
   npx serve .
   
   # Sử dụng PHP
   php -S localhost:8000
   ```

### Cấu trúc file
```
warehouse-management/
├── index.html          # File HTML chính
├── styles.css          # File CSS styling
├── script.js           # File JavaScript functionality
└── README.md           # Hướng dẫn sử dụng
```

## Sử dụng

### Navigation
- Click vào các menu item trong sidebar để chuyển đổi giữa các trang
- Menu "Tổng quan" được highlight mặc định
- Các menu item có hiệu ứng hover và active state

### Dashboard
- Click vào các metric cards để xem chi tiết
- Các warning cards hiển thị thông tin cảnh báo
- Hover effects trên tất cả các cards

### Header Tools
- **Eye icon**: Preview mode
- **Code icon**: Code view
- **Refresh**: Làm mới trang
- **Copy**: Tùy chọn copy
- **Publish**: Xuất bản
- **Search**: Tìm kiếm
- **Notifications**: Thông báo (có badge số 1)
- **User**: Hồ sơ người dùng

## Tùy chỉnh

### Thay đổi màu sắc
Chỉnh sửa file `styles.css`:
```css
:root {
    --primary-color: #3498db;
    --success-color: #2ecc71;
    --warning-color: #f39c12;
    --danger-color: #e74c3c;
    --sidebar-bg: #2c3e50;
}
```

### Thêm menu items
Chỉnh sửa file `index.html` trong phần `<ul class="nav-menu">`:
```html
<li class="nav-item">
    <i class="fas fa-icon-name"></i>
    <span>Tên menu mới</span>
</li>
```

### Thay đổi metrics
Chỉnh sửa file `index.html` trong phần metric cards:
```html
<div class="metric-card blue">
    <div class="metric-info">
        <h3>Tên metric mới</h3>
        <div class="metric-value">Giá trị</div>
    </div>
    <div class="metric-dot blue-dot"></div>
</div>
```

## Tính năng nâng cao

### Responsive Design
- Tự động điều chỉnh layout cho mobile và tablet
- Sidebar collapse trên màn hình nhỏ
- Grid layout linh hoạt cho metric cards

### Animations
- Smooth transitions cho hover effects
- Loading spinner khi chuyển trang
- Notification system với slide-in animation

### Accessibility
- Tooltips cho tất cả icons
- Keyboard navigation support
- High contrast color scheme

## Phát triển

### Thêm tính năng mới
1. Cập nhật HTML structure trong `index.html`
2. Thêm CSS styling trong `styles.css`
3. Implement JavaScript functionality trong `script.js`

### Backend Integration
Ứng dụng có thể dễ dàng tích hợp với backend:
- REST API calls
- Real-time data updates
- User authentication
- Database operations

## Hỗ trợ

### Trình duyệt hỗ trợ
- Chrome 60+
- Firefox 55+
- Safari 12+
- Edge 79+

### Mobile Support
- iOS Safari 12+
- Chrome Mobile 60+
- Samsung Internet 7+

## Giấy phép
Dự án này được phát hành dưới giấy phép MIT. Bạn có thể tự do sử dụng, chỉnh sửa và phân phối.

## Đóng góp
Mọi đóng góp đều được chào đón! Vui lòng:
1. Fork dự án
2. Tạo feature branch
3. Commit changes
4. Push to branch
5. Tạo Pull Request

---

**Lưu ý**: Đây là phiên bản frontend demo. Để sử dụng trong production, cần tích hợp với backend system và database.




