package com.StdMngmnt.controller;

import com.StdMngmnt.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/auth")
public class AuthController {

        @GetMapping("/login")
        public String login() {
            return "login";
        }

        @GetMapping("/logout")
        public String logout() {
            return "redirect:/login"; //
        }
}
