package com.StdMngmnt.controller;

import com.StdMngmnt.DTO.UserDTO;
import com.StdMngmnt.entity.User;
import com.StdMngmnt.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
    @RequestMapping("/users")
    public class UserController {

        @Autowired
        private UserService userService;

        @Autowired
        private PasswordEncoder passwordEncoder;

        @GetMapping("/add")
        public String showAddUserForm(Model model) {
            model.addAttribute("user", new UserDTO());
            return "add-user";  // Create add-user.html template
        }

        @PostMapping("/save")
        public String saveUser(@ModelAttribute("user") UserDTO userDTO, RedirectAttributes redirectAttributes) {
            User user = new User();
            user.setUsername(userDTO.getUsername());
            user.setPassword(passwordEncoder.encode(userDTO.getPassword())); // Encrypt password
            user.setRole(userDTO.getRole());

            userService.saveUser(user);
            redirectAttributes.addFlashAttribute("msg", "New user has been successfully added!");
            return "redirect:/users/add"; // Redirect to user list
        }
    }

