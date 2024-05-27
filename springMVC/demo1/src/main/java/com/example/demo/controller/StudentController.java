package com.example.demo.controller;

import com.example.demo.model.Student;
import com.example.demo.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.Optional;

@Controller
public class StudentController {

    @Autowired
    private StudentRepository studentRepository;

    @GetMapping("/student/view")
    public String viewStudent(HttpServletRequest request, Model model) {
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if ("SESSION".equals(cookie.getName())) {
                    String studentId = cookie.getValue();
                    Optional<Student> student = studentRepository.findByStudentId(studentId);
                    if (student.isPresent()) {
                        model.addAttribute("student", student.get());
                        return "studentView";
                    }
                }
            }
        }
        return "redirect:/login";
    }

    @GetMapping("/student/modify")
    public String showModifyForm(HttpServletRequest request, Model model) {
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if ("SESSION".equals(cookie.getName())) {
                    String studentId = cookie.getValue();
                    Optional<Student> student = studentRepository.findByStudentId(studentId);
                    if (student.isPresent()) {
                        model.addAttribute("student", student.get());
                        return "studentModify";
                    }
                }
            }
        }
        return "redirect:/login";
    }

    @PostMapping("/student/modify")
    public String modifyStudent(@ModelAttribute Student student) {
        studentRepository.save(student);
        return "redirect:/student/view";
    }

    @PostMapping("/student/logout")
    public String logout(HttpServletResponse response) {
        Cookie sessionCookie = new Cookie("SESSION", null);
        sessionCookie.setMaxAge(0);
        sessionCookie.setPath("/");
        response.addCookie(sessionCookie);
        return "redirect:/login";
    }
}
