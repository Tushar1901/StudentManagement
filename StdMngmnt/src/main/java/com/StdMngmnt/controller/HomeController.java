package com.StdMngmnt.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/")
    public String showIndex() {
        return "index"; // This will look for "index.html" in src/main/resources/templates/
    }
}
