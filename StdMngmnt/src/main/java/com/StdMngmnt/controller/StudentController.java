package com.StdMngmnt.controller;

import com.StdMngmnt.entity.Student;
import com.StdMngmnt.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/students") // ✅ Controller mapped to /students URL prefix  // ✅ Add pre-fix for URL mapping
//@PreAuthorize("hasAuthority('USER')") // ✅ Only USER can access this controller  // ✅ Add authorization for access control  // ✅ Add pre-fix for authorization control
public class StudentController {

    @Autowired
    private StudentService studentService;
    @GetMapping("/view")
    // @PreAuthorize("hasAuthority('USER')")
    public String listStudents(Model model) {
        model.addAttribute("students", studentService.getAllStudents());
        return "students";
    }

    @GetMapping("/add")
    @PreAuthorize("hasAuthority('ADMIN')") // ✅ Only ADMIN can access
    public String showAddStudentForm(Model model) {
        model.addAttribute("student", new Student());
        return "add-student";
    }

    @PostMapping("/save")
    @PreAuthorize("hasAuthority('ADMIN')") // ✅ Only ADMIN can add students
    public String saveStudent(@ModelAttribute Student student, Model model) {
        studentService.saveStudent(student);
        model.addAttribute("students", studentService.getAllStudents());
        return "students";
    }
}
