package com.StdMngmnt.controller;

import com.StdMngmnt.service.FeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping
public class FeeController {
    @Autowired
    private FeeService feeService;
    @GetMapping("/fees")
    @PreAuthorize("hasRole('ADMIN')")
    public String listFees(Model model) {
        model.addAttribute("fees", feeService.getAllFees());
        return "fees";
    }
}
