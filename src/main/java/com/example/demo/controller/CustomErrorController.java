package com.example.demo.controller;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.servlet.http.HttpServletRequest;

@Controller
public class CustomErrorController implements ErrorController {

    @RequestMapping("/error")
    public String handleError(HttpServletRequest request, Model model) {
        Integer statusCode = (Integer) request.getAttribute("javax.servlet.error.status_code");
        Exception exception = (Exception) request.getAttribute("javax.servlet.error.exception");
        String message = (String) request.getAttribute("javax.servlet.error.message");
        String requestUri = (String) request.getAttribute("javax.servlet.error.request_uri");

        model.addAttribute("statusCode", statusCode);
        model.addAttribute("exception", exception);
        model.addAttribute("message", message);
        model.addAttribute("requestUri", requestUri);

        if (statusCode != null) {
            if (statusCode == 404) {
                model.addAttribute("error", "Trang không tồn tại!");
                model.addAttribute("errorDescription", "Đường dẫn bạn truy cập không tồn tại trong hệ thống.");
            } else if (statusCode == 500) {
                model.addAttribute("error", "Lỗi hệ thống!");
                model.addAttribute("errorDescription", "Đã xảy ra lỗi trong quá trình xử lý. Vui lòng thử lại sau.");
                
                // Log error for debugging
                if (exception != null) {
                    System.err.println("Error 500 at " + requestUri + ": " + exception.getMessage());
                    exception.printStackTrace();
                }
            } else {
                model.addAttribute("error", "Có lỗi xảy ra!");
                model.addAttribute("errorDescription", "Mã lỗi: " + statusCode);
            }
        }

        return "error";
    }
}