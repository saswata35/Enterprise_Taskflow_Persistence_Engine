package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PageController {

    // Serves the main dashboard
    @GetMapping("/")
    public String home() {
        return "index.html";
    }

    // Serves an analytics subpage at http://localhost:8086/analytics
    @GetMapping("/analytics")
    public String analyticsPage() {
        return "analytics.html";
    }
}