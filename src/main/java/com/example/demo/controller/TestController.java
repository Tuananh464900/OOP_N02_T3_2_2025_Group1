package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class TestController {

    @GetMapping("/test")
    @ResponseBody
    public String test() {
        return "✅ Application is running successfully!";
    }

    @GetMapping("/test-page")
    public String testPage(Model model) {
        model.addAttribute("message", "Test page works!");
        model.addAttribute("title", "Test Page - Warehouse App");
        return "test";
    }
}