package com.StdMngmnt.controller;

import com.StdMngmnt.entity.Student;
import com.StdMngmnt.service.StudentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@Controller
@RequestMapping("/students") // ✅ Controller mapped to /students URL prefix  // ✅ Add pre-fix for URL mapping
@Tag(name = "Student Management", description = "APIs for managing students")
//@PreAuthorize("hasAuthority('USER')") // ✅ Only USER can access this controller  // ✅ Add authorization for access control  // ✅ Add pre-fix for authorization control
public class StudentController {

    @Autowired
    private StudentService studentService;
    @GetMapping("/view")
    @Operation(summary = "Get all students", description = "Fetches a list of all students")
    // @PreAuthorize("hasAuthority('USER')")
    public String listStudents(Model model) {
        model.addAttribute("students", studentService.getAllStudents());
        return "students";
    }

    @GetMapping("/add")
    @PreAuthorize("hasAuthority('ADMIN')") // ✅ Only ADMIN can access
    @Operation(summary = "Add a new student", description = "Creates a new student record")
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

    // Load update form
    @GetMapping("/update")
    @PreAuthorize("hasAuthority('ADMIN')") // ✅ Only ADMIN can update students
    public String showUpdateForm(@RequestParam("id") Long id, Model model) {
        System.out.println("Fetching student with ID: " + id); // Debugging

        Student student = studentService.getStudentById(id);

        if (student == null) {
            System.out.println("Student not found!");
            throw new IllegalArgumentException("Student not found with ID: " + id);
        }

        model.addAttribute("student", student);
        return "update"; // Load update.html
    }


    // Process update form submission
    @PostMapping("/update")
    @PreAuthorize("hasAuthority('ADMIN')") // ✅ Only ADMIN can update students
    public String updateStudent(@ModelAttribute Student student, Model model) {

        studentService.updateStudent(student.getId(), student);
        model.addAttribute("students", studentService.getAllStudents());
        return "students"; // Redirect back to student list
    }

    @DeleteMapping("/delete/{id}")
    @PreAuthorize("hasAuthority('ADMIN')") // ✅ Only ADMIN can update students
    public String deleteStudent(@PathVariable Long id, Model model) {

        studentService.deleteStudent(id); // Calls service to delete student
        model.addAttribute("students", studentService.getAllStudents());
        return "students"; // Redirect back to student list
    }
}
