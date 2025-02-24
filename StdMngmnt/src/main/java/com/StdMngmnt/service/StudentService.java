package com.StdMngmnt.service;

import com.StdMngmnt.entity.Student;
import com.StdMngmnt.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;

    public Student saveStudent(Student student) {
        return studentRepository.save(student);
    }
    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }
    public void deleteStudent(Long id) {
        studentRepository.deleteById(id);
    }

    public Student getStudentById(Long id) {
        return studentRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Student not found with ID: " + id));
    }

    public void updateStudent(Long id, Student updatedStudent) {
//        if (id == null) {
//            System.out.println("❌ ID is NULL before searching in the database!");
//            throw new IllegalArgumentException("Student ID cannot be null.");
//        }

        Student existingStudent = studentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Student not found with ID: " + id));

        // Update only the fields that need to be changed
        existingStudent.setName(updatedStudent.getName());
        existingStudent.setPhone(updatedStudent.getPhone());
        existingStudent.setAddress(updatedStudent.getAddress());
        existingStudent.setEmail(updatedStudent.getEmail());

        studentRepository.save(existingStudent); // Save changes
    }

}
