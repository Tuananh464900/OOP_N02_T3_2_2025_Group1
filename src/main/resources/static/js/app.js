// Warehouse App JavaScript Utilities

document.addEventListener('DOMContentLoaded', function() {
    // Initialize all components
    initializeAlerts();
    initializeFormSubmissions();
    initializeConfirmDialogs();
    initializeTooltips();
    initializeDateFields();
    initializeMobileNavigation();
});

// Auto hide alerts after 5 seconds
function initializeAlerts() {
    const alerts = document.querySelectorAll('.alert:not(.alert-permanent)');
    alerts.forEach(function(alert) {
        // Add fade out animation
        setTimeout(function() {
            if (bootstrap.Alert && alert.classList.contains('show')) {
                const bsAlert = new bootstrap.Alert(alert);
                bsAlert.close();
            }
        }, 5000);
    });
}

// Add loading state to form submissions
function initializeFormSubmissions() {
    const forms = document.querySelectorAll('form');
    forms.forEach(function(form) {
        form.addEventListener('submit', function(e) {
            const submitBtn = form.querySelector('button[type="submit"]');
            if (submitBtn && !submitBtn.disabled) {
                // Prevent double submission
                submitBtn.disabled = true;
                submitBtn.classList.add('btn-loading');
                
                // Store original text
                const originalText = submitBtn.innerHTML;
                
                // Set loading text
                submitBtn.innerHTML = '<span class="spinner-border spinner-border-sm me-2" role="status"></span>Đang xử lý...';
                
                // Re-enable after 10 seconds as fallback
                setTimeout(function() {
                    submitBtn.disabled = false;
                    submitBtn.classList.remove('btn-loading');
                    submitBtn.innerHTML = originalText;
                }, 10000);
            }
        });
    });
}

// Enhanced confirm delete function
function confirmDelete(formId, itemName = '') {
    const message = itemName 
        ? `Bạn có chắc chắn muốn xóa "${itemName}" không? Hành động này không thể hoàn tác.`
        : 'Bạn có chắc chắn muốn xóa không? Hành động này không thể hoàn tác.';
    
    if (confirm(message)) {
        const form = document.getElementById(formId);
        if (form) {
            // Add loading overlay
            showLoadingOverlay();
            form.submit();
        }
    }
}

// Initialize confirm dialogs
function initializeConfirmDialogs() {
    // Add confirmation to all delete buttons
    const deleteButtons = document.querySelectorAll('[onclick*="confirmDelete"]');
    deleteButtons.forEach(function(button) {
        button.addEventListener('click', function(e) {
            e.preventDefault();
            const onclick = button.getAttribute('onclick');
            const formId = onclick.match(/confirmDelete\('([^']+)'\)/);
            if (formId) {
                confirmDelete(formId[1]);
            }
        });
    });
}

// Initialize tooltips
function initializeTooltips() {
    if (typeof bootstrap !== 'undefined' && bootstrap.Tooltip) {
        const tooltipTriggerList = [].slice.call(document.querySelectorAll('[data-bs-toggle="tooltip"]'));
        tooltipTriggerList.map(function(tooltipTriggerEl) {
            return new bootstrap.Tooltip(tooltipTriggerEl);
        });
    }
}

// Set default date/time values
function initializeDateFields() {
    // Set current datetime for datetime-local inputs if empty
    const datetimeFields = document.querySelectorAll('input[type="datetime-local"]');
    datetimeFields.forEach(function(field) {
        if (!field.value) {
            const now = new Date();
            now.setMinutes(now.getMinutes() - now.getTimezoneOffset());
            field.value = now.toISOString().slice(0, 16);
        }
    });

    // Set current date for date inputs if empty
    const dateFields = document.querySelectorAll('input[type="date"]');
    dateFields.forEach(function(field) {
        if (!field.value) {
            const today = new Date().toISOString().split('T')[0];
            field.value = today;
        }
    });
}

// Mobile navigation toggle
function initializeMobileNavigation() {
    // Create mobile toggle button if it doesn't exist
    let mobileToggle = document.querySelector('.mobile-nav-toggle');
    if (!mobileToggle && window.innerWidth <= 768) {
        mobileToggle = document.createElement('button');
        mobileToggle.className = 'btn btn-primary mobile-nav-toggle position-fixed';
        mobileToggle.style.cssText = 'top: 1rem; left: 1rem; z-index: 1060;';
        mobileToggle.innerHTML = '<i class="bi bi-list"></i>';
        document.body.appendChild(mobileToggle);
        
        const sidebar = document.querySelector('.sidebar-gradient');
        if (sidebar) {
            mobileToggle.addEventListener('click', function() {
                sidebar.classList.toggle('show');
            });
        }
    }
}

// Show loading overlay
function showLoadingOverlay(message = 'Đang xử lý...') {
    // Remove existing overlay
    removeLoadingOverlay();
    
    const overlay = document.createElement('div');
    overlay.className = 'loading-overlay';
    overlay.innerHTML = `
        <div class="text-center">
            <div class="spinner-border text-primary mb-3" role="status">
                <span class="visually-hidden">Loading...</span>
            </div>
            <div>${message}</div>
        </div>
    `;
    document.body.appendChild(overlay);
}

// Remove loading overlay
function removeLoadingOverlay() {
    const overlay = document.querySelector('.loading-overlay');
    if (overlay) {
        overlay.remove();
    }
}

// Utility functions for common operations
const Utils = {
    // Format number with thousands separator
    formatNumber: function(num) {
        return new Intl.NumberFormat('vi-VN').format(num);
    },
    
    // Format currency
    formatCurrency: function(amount) {
        return new Intl.NumberFormat('vi-VN', {
            style: 'currency',
            currency: 'VND'
        }).format(amount);
    },
    
    // Format date
    formatDate: function(date) {
        return new Intl.DateTimeFormat('vi-VN').format(new Date(date));
    },
    
    // Debounce function for search inputs
    debounce: function(func, wait) {
        let timeout;
        return function executedFunction(...args) {
            const later = () => {
                clearTimeout(timeout);
                func(...args);
            };
            clearTimeout(timeout);
            timeout = setTimeout(later, wait);
        };
    },
    
    // Show toast notification
    showToast: function(message, type = 'info') {
        const toastContainer = document.querySelector('.toast-container') || 
            this.createToastContainer();
        
        const toast = document.createElement('div');
        toast.className = `toast align-items-center text-white bg-${type} border-0`;
        toast.setAttribute('role', 'alert');
        toast.setAttribute('aria-live', 'assertive');
        toast.setAttribute('aria-atomic', 'true');
        
        toast.innerHTML = `
            <div class="d-flex">
                <div class="toast-body">${message}</div>
                <button type="button" class="btn-close btn-close-white me-2 m-auto" 
                        data-bs-dismiss="toast" aria-label="Close"></button>
            </div>
        `;
        
        toastContainer.appendChild(toast);
        
        if (typeof bootstrap !== 'undefined' && bootstrap.Toast) {
            const bsToast = new bootstrap.Toast(toast);
            bsToast.show();
            
            // Remove toast element after it's hidden
            toast.addEventListener('hidden.bs.toast', function() {
                toast.remove();
            });
        }
    },
    
    // Create toast container if it doesn't exist
    createToastContainer: function() {
        const container = document.createElement('div');
        container.className = 'toast-container position-fixed top-0 end-0 p-3';
        container.style.zIndex = '1070';
        document.body.appendChild(container);
        return container;
    }
};

// Global error handler
window.addEventListener('error', function(e) {
    console.error('JavaScript Error:', e.error);
    Utils.showToast('Có lỗi xảy ra. Vui lòng thử lại.', 'danger');
});

// Export utilities to global scope
window.WarehouseApp = {
    Utils,
    confirmDelete,
    showLoadingOverlay,
    removeLoadingOverlay
};