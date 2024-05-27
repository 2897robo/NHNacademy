package com.example.demo.controller;

import com.example.demo.model.Student;
import com.example.demo.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Optional;

@Controller
public class StudentRegisterController {

    @Autowired
    private StudentRepository studentRepository;

    @GetMapping("/student/register")
    public String showRegisterForm() {
        return "studentRegister";
    }

    @PostMapping("/student/register")
    public String register(@ModelAttribute Student student) {
        Optional<Student> existingStudent = studentRepository.findByStudentId(student.getStudentId());
        if (existingStudent.isEmpty()) {
            studentRepository.save(student);
            return "redirect:/student/view";
        }
        return "redirect:/student/register";
    }
}
