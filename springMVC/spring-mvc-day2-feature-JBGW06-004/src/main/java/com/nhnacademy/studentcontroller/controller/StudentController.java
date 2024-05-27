package com.nhnacademy.studentcontroller.controller;

import com.nhnacademy.studentcontroller.domain.Student;
import com.nhnacademy.studentcontroller.domain.StudentRegisterRequest;
import com.nhnacademy.studentcontroller.exception.NoStudentException;
import com.nhnacademy.studentcontroller.repository.StudentRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Slf4j
@Controller
@RequestMapping("/student")
public class StudentController {
    private final StudentRepository studentRepository;

    public StudentController(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @GetMapping("/{id}")
    public String viewStudent(@PathVariable("id") String id, Model model) {
        Student student = studentRepository.getStudent(id);
        if (student == null) {
            throw new NoStudentException("Student not found");
        }
        model.addAttribute("student", Student.pwdMasked(student));
        return "studentView";
    }

    @GetMapping(value = "/{id}", params = "hideScore")
    public String withoutScore(@PathVariable("id") String id, Model model) {
        Student student = studentRepository.getStudent(id);
        if (student == null) {
            throw new NoStudentException("Student not found");
        }
        model.addAttribute("hide", true);
        model.addAttribute("student", Student.pwdMasked(student));
        return "studentView";
    }

    @ModelAttribute("student")
    public Student student(@CookieValue(value = "SESSION", required = false) String session,
                           Model model,
                           HttpServletRequest request) {
        if (StringUtils.hasText(session)) {
            HttpSession sessionObj = request.getSession(false);
            if (sessionObj != null) {
                String id = (String) sessionObj.getAttribute("studentId");
                log.info("id : {}", id);
                if (StringUtils.hasText(id)) {
                    model.addAttribute("id", id);
                    Student student = studentRepository.getStudent(id);
                    if (student != null) {
                        return Student.pwdMasked(student);
                    }
                }
            }
        }
        throw new NoStudentException("No session found or student not found in session");
    }

    @GetMapping("/{id}/modify")
    public String studentModifyForm(Model model, @ModelAttribute("student") Student student) {
        if (student == null) {
            throw new NoStudentException("Student not found in session");
        }
        model.addAttribute("student", student);
        log.info("student: {}", student.getId());
        return "studentModify";
    }

    @PostMapping("/{id}/modify")
    public ModelAndView modifyStudent(@Valid @ModelAttribute StudentRegisterRequest studentRegisterRequest,
                                      BindingResult bindingResult,
                                      @PathVariable("id") String id) {
        if(bindingResult.hasErrors()) {
            return new ModelAndView("error");
        }

        Student student = studentRepository.updateStudent(
                studentRegisterRequest.getId(),
                studentRegisterRequest.getPassword(),
                studentRegisterRequest.getName(),
                studentRegisterRequest.getEmail(),
                Integer.parseInt(studentRegisterRequest.getScore()),
                studentRegisterRequest.getComment()
        );

        ModelAndView modelAndView = new ModelAndView("studentView");
        modelAndView.addObject("student", Student.pwdMasked(student));

        return modelAndView;
    }
}