package com.nhnacademy.studentcontroller.controller;

import com.nhnacademy.studentcontroller.domain.Student;
import com.nhnacademy.studentcontroller.domain.StudentRegisterRequest;
import com.nhnacademy.studentcontroller.repository.StudentRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("student/register")
public class StudentRegisterController {
    private final StudentRepository studentRepository;

    public StudentRegisterController(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @GetMapping
    public String studentRegister() {
        return "studentRegister";
    }

    @PostMapping
    public String registerStudent(@Valid @ModelAttribute StudentRegisterRequest studentRegisterRequest,
                                  HttpServletRequest request) {
        Student student = studentRepository.register(
                studentRegisterRequest.getId(),
                studentRegisterRequest.getPassword(),
                studentRegisterRequest.getName(),
                studentRegisterRequest.getEmail(),
                Integer.parseInt(studentRegisterRequest.getScore()),
                studentRegisterRequest.getComment()
        );

        // 학생 등록 후 세션에 학생 ID 저장
        HttpSession session = request.getSession(true);
        session.setAttribute("studentId", student.getId());

        // 학생 등록 후 학생의 id를 포함한 URL로 리다이렉트
        return "redirect:/student/" + student.getId();
    }

}
