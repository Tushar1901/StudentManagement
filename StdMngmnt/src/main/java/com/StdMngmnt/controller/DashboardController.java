package com.StdMngmnt.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DashboardController {

    @GetMapping("/dashboard")  // ✅ Handles /dashboard after login
    public String showDashboard(Model model) {
        model.addAttribute("message", "Welcome to the Dashboard!");
        return "dashboard"; // Must match dashboard.html in templates
    }  // This should match "dashboard.html" inside templates

}

