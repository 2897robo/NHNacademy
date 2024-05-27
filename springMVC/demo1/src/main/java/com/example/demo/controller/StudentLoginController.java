package com.example.demo.controller;

import com.example.demo.model.Student;
import com.example.demo.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.Optional;

@Controller
public class StudentLoginController {

    @Autowired
    private StudentRepository studentRepository;

    @GetMapping("/login")
    public String showLoginForm(HttpServletRequest request, ModelMap modelMap) {
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if ("SESSION".equals(cookie.getName())) {
                    String studentId = cookie.getValue();
                    Optional<Student> student = studentRepository.findByStudentId(studentId);
                    if (student.isPresent()) {
                        modelMap.addAttribute("student", student.get());
                        return "studentView";
                    }
                }
            }
        }
        return "loginForm";
    }

    @PostMapping("/login")
    public String login(@ModelAttribute Student student, HttpServletResponse response) {
        Optional<Student> foundStudent = studentRepository.findByStudentIdAndPassword(student.getStudentId(), student.getPassword());
        if (foundStudent.isPresent()) {
            Cookie sessionCookie = new Cookie("SESSION", student.getStudentId());
            sessionCookie.setPath("/");
            sessionCookie.setHttpOnly(true);
            response.addCookie(sessionCookie);
            return "redirect:/student/view";
        }
        return "redirect:/login";
    }
}
