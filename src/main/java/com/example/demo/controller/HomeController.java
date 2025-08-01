package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    // Khi truy cập http://…/ hoặc http://…/home
    // sẽ redirect thẳng sang /warehouses
    @GetMapping({"/", "/home"})
    public String home() {
        return "redirect:/warehouses";
    }

}
