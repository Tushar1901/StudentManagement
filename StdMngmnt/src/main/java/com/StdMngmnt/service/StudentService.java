package com.StdMngmnt.service;

import com.StdMngmnt.entity.Student;
import com.StdMngmnt.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
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
}
