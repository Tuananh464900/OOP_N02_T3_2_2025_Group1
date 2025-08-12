// Warehouse Management System JavaScript

document.addEventListener('DOMContentLoaded', function() {
    // Initialize the application
    initApp();
});

function initApp() {
    // Add click event listeners to navigation items
    setupNavigation();
    
    // Add header interactions
    setupHeaderInteractions();
    
    // Add metric card interactions
    setupMetricCards();
    
    // Add warning card interactions
    setupWarningCards();
    
    // Initialize tooltips and notifications
    setupTooltips();
}

function setupNavigation() {
    const navItems = document.querySelectorAll('.nav-item');
    
    navItems.forEach(item => {
        item.addEventListener('click', function() {
            // Remove active class from all items
            navItems.forEach(nav => nav.classList.remove('active'));
            
            // Add active class to clicked item
            this.classList.add('active');
            
            // Handle navigation (you can add page routing here)
            const menuText = this.querySelector('span').textContent;
            console.log(`Navigating to: ${menuText}`);
            
            // Show loading state
            showLoadingState();
            
            // Simulate navigation delay
            setTimeout(() => {
                hideLoadingState();
                updatePageTitle(menuText);
            }, 500);
        });
    });
}

function setupHeaderInteractions() {
    // Copy dropdown functionality
    const copyDropdown = document.querySelector('.copy-dropdown');
    if (copyDropdown) {
        copyDropdown.addEventListener('click', function() {
            // Toggle dropdown menu (you can implement a proper dropdown)
            showNotification('Copy options', 'info');
        });
    }
    
    // Publish button
    const publishBtn = document.querySelector('.publish-btn');
    if (publishBtn) {
        publishBtn.addEventListener('click', function() {
            showNotification('Publishing...', 'info');
            // Simulate publishing process
            setTimeout(() => {
                showNotification('Published successfully!', 'success');
            }, 2000);
        });
    }
    
    // Notification bell
    const notificationBell = document.querySelector('.notification-bell');
    if (notificationBell) {
        notificationBell.addEventListener('click', function() {
            showNotification('You have 1 new notification', 'info');
        });
    }
    
    // Search functionality
    const searchIcon = document.querySelector('.header-right .fa-search');
    if (searchIcon) {
        searchIcon.addEventListener('click', function() {
            const searchTerm = prompt('Enter search term:');
            if (searchTerm) {
                showNotification(`Searching for: ${searchTerm}`, 'info');
            }
        });
    }
    
    // Close button
    const closeBtn = document.querySelector('.header .fa-times');
    if (closeBtn) {
        closeBtn.addEventListener('click', function() {
            if (confirm('Are you sure you want to close?')) {
                showNotification('Closing application...', 'info');
            }
        });
    }
}

function setupMetricCards() {
    const metricCards = document.querySelectorAll('.metric-card');
    
    metricCards.forEach(card => {
        card.addEventListener('click', function() {
            const metricType = this.querySelector('h3').textContent;
            const metricValue = this.querySelector('.metric-value').textContent;
            
            // Show detailed information
            showMetricDetails(metricType, metricValue);
            
            // Add click animation
            this.style.transform = 'scale(0.95)';
            setTimeout(() => {
                this.style.transform = '';
            }, 150);
        });
        
        // Add hover effects
        card.addEventListener('mouseenter', function() {
            this.style.cursor = 'pointer';
        });
    });
}

function setupWarningCards() {
    const warningCards = document.querySelectorAll('.warning-card');
    
    warningCards.forEach(card => {
        card.addEventListener('click', function() {
            const warningText = this.querySelector('span').textContent;
            
            // Show detailed warning information
            showWarningDetails(warningText);
        });
    });
}

function setupTooltips() {
    // Add tooltips to header icons
    const headerIcons = document.querySelectorAll('.header i');
    headerIcons.forEach(icon => {
        icon.title = getIconTooltip(icon.className);
    });
}

function getIconTooltip(className) {
    const tooltips = {
        'fas fa-eye': 'Preview',
        'fas fa-code': 'Code View',
        'fas fa-sync-alt': 'Refresh',
        'fas fa-search': 'Search',
        'fas fa-star': 'Favorites',
        'fas fa-bell': 'Notifications',
        'fas fa-download': 'Download',
        'fas fa-square': 'Fullscreen',
        'fas fa-th': 'Grid View',
        'fas fa-user': 'User Profile',
        'fas fa-bars': 'Menu'
    };
    
    for (const [key, value] of Object.entries(tooltips)) {
        if (className.includes(key)) {
            return value;
        }
    }
    return '';
}

function showMetricDetails(metricType, metricValue) {
    const details = getMetricDetails(metricType, metricValue);
    
    // Create modal or show details in a more sophisticated way
    const message = `${metricType}: ${metricValue}\n\n${details}`;
    showNotification(message, 'info');
}

function getMetricDetails(metricType, metricValue) {
    const details = {
        'Tổng sản phẩm': 'Tổng số sản phẩm trong hệ thống quản lý kho',
        'Số kho': 'Tổng số kho hàng đang hoạt động',
        'Sắp hết hàng': 'Số sản phẩm có lượng tồn kho thấp',
        'Sắp hết hạn': 'Số sản phẩm sắp đến hạn sử dụng'
    };
    
    return details[metricType] || 'Chi tiết về chỉ số này';
}

function showWarningDetails(warningText) {
    const details = getWarningDetails(warningText);
    
    const message = `Cảnh báo: ${warningText}\n\n${details}`;
    showNotification(message, 'warning');
}

function getWarningDetails(warningText) {
    const details = {
        'Hàng sắp hết tồn kho': 'Các sản phẩm có lượng tồn kho dưới mức tối thiểu. Cần bổ sung hàng hóa.',
        'Hàng sắp hết hạn': 'Các sản phẩm sắp đến hạn sử dụng. Cần kiểm tra và xử lý kịp thời.'
    };
    
    return details[warningText] || 'Thông tin chi tiết về cảnh báo này';
}

function showLoadingState() {
    // Add loading indicator to main content
    const mainContent = document.querySelector('.main-content');
    if (mainContent) {
        const loader = document.createElement('div');
        loader.className = 'loading-overlay';
        loader.innerHTML = '<div class="spinner"></div><p>Đang tải...</p>';
        mainContent.appendChild(loader);
    }
}

function hideLoadingState() {
    // Remove loading indicator
    const loader = document.querySelector('.loading-overlay');
    if (loader) {
        loader.remove();
    }
}

function updatePageTitle(title) {
    // Update page title based on navigation
    document.title = `${title} - Quản lý Kho`;
    
    // Update main content header if needed
    const mainContent = document.querySelector('.main-content');
    if (mainContent) {
        let header = mainContent.querySelector('.page-header');
        if (!header) {
            header = document.createElement('h1');
            header.className = 'page-header';
            mainContent.insertBefore(header, mainContent.firstChild);
        }
        header.textContent = title;
    }
}

function showNotification(message, type = 'info') {
    // Create notification element
    const notification = document.createElement('div');
    notification.className = `notification notification-${type}`;
    notification.innerHTML = `
        <div class="notification-content">
            <span class="notification-message">${message}</span>
            <button class="notification-close">&times;</button>
        </div>
    `;
    
    // Add to page
    document.body.appendChild(notification);
    
    // Show notification
    setTimeout(() => {
        notification.classList.add('show');
    }, 100);
    
    // Auto hide after 5 seconds
    setTimeout(() => {
        hideNotification(notification);
    }, 5000);
    
    // Close button functionality
    const closeBtn = notification.querySelector('.notification-close');
    closeBtn.addEventListener('click', () => {
        hideNotification(notification);
    });
}

function hideNotification(notification) {
    notification.classList.remove('show');
    setTimeout(() => {
        if (notification.parentNode) {
            notification.parentNode.removeChild(notification);
        }
    }, 300);
}

// Add CSS for notifications and loading states
const additionalStyles = `
    .loading-overlay {
        position: absolute;
        top: 0;
        left: 0;
        right: 0;
        bottom: 0;
        background: rgba(255, 255, 255, 0.9);
        display: flex;
        flex-direction: column;
        align-items: center;
        justify-content: center;
        z-index: 1000;
    }
    
    .spinner {
        width: 40px;
        height: 40px;
        border: 4px solid #f3f3f3;
        border-top: 4px solid #3498db;
        border-radius: 50%;
        animation: spin 1s linear infinite;
        margin-bottom: 16px;
    }
    
    @keyframes spin {
        0% { transform: rotate(0deg); }
        100% { transform: rotate(360deg); }
    }
    
    .notification {
        position: fixed;
        top: 100px;
        right: 20px;
        background: white;
        border-radius: 8px;
        box-shadow: 0 4px 12px rgba(0,0,0,0.15);
        padding: 16px;
        max-width: 400px;
        transform: translateX(100%);
        transition: transform 0.3s ease;
        z-index: 10000;
    }
    
    .notification.show {
        transform: translateX(0);
    }
    
    .notification-content {
        display: flex;
        align-items: center;
        justify-content: space-between;
    }
    
    .notification-message {
        flex: 1;
        margin-right: 16px;
        white-space: pre-line;
    }
    
    .notification-close {
        background: none;
        border: none;
        font-size: 20px;
        cursor: pointer;
        color: #666;
        padding: 0;
        width: 24px;
        height: 24px;
        display: flex;
        align-items: center;
        justify-content: center;
    }
    
    .notification-close:hover {
        color: #333;
    }
    
    .notification-info {
        border-left: 4px solid #3498db;
    }
    
    .notification-success {
        border-left: 4px solid #2ecc71;
    }
    
    .notification-warning {
        border-left: 4px solid #f39c12;
    }
    
    .notification-error {
        border-left: 4px solid #e74c3c;
    }
    
    .page-header {
        margin-bottom: 32px;
        font-size: 28px;
        font-weight: 600;
        color: #333;
    }
`;

// Inject additional styles
const styleSheet = document.createElement('style');
styleSheet.textContent = additionalStyles;
document.head.appendChild(styleSheet);