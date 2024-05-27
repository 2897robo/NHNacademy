package com.nhnacademy.studentcontroller.controller;

import com.nhnacademy.studentcontroller.repository.StudentRepository;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Slf4j
@Controller
public class StudentLoginController {
    private final StudentRepository studentRepository;

    public StudentLoginController(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @GetMapping("/")
    public String loginForm(@CookieValue(value="SESSION", required=false) String session,
                            Model model,
                            HttpServletRequest request) {
        if (StringUtils.hasText(session)) {
            HttpSession sessionObj = request.getSession(false);
            if (sessionObj != null) {
                String id = (String) sessionObj.getAttribute("studentId");
                if (StringUtils.hasText(id)) {
                    model.addAttribute("id", id);
                    return "redirect:/student/" + id;
                }
            }
        }
        return "loginForm";
    }

    @PostMapping("/login")
    public String doLogin(@RequestParam("id") String id,
                          @RequestParam("pwd") String pwd,
                          HttpServletRequest request,
                          HttpServletResponse response,
                          Model model) {
        if (studentRepository.matches(id, pwd)) {
            HttpSession session = request.getSession(true);
            session.setAttribute("studentId", id);
            Cookie cookie = new Cookie("SESSION", session.getId());
            response.addCookie(cookie);
            model.addAttribute("id", id);
            log.info("로그인 성공. id:{}", id);
            return "redirect:/student/" + id;
        } else {
            log.info("로그인 실패");
            model.addAttribute("error", "Invalid credentials");
            return "loginForm";
        }
    }

    @GetMapping("/logout")
    public String logout(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate();
        }
        Cookie cookie = new Cookie("SESSION", null);
        cookie.setMaxAge(0);
        response.addCookie(cookie);
        return "redirect:/";
    }
}
