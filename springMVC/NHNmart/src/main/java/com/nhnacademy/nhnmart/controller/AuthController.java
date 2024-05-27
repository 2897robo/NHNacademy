package com.nhnacademy.nhnmart.controller;

import com.nhnacademy.nhnmart.domain.User;
import com.nhnacademy.nhnmart.service.AuthService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AuthController {
    @Autowired
    private AuthService authService;

    @GetMapping("/login")
    public String loginForm() {
        return "login";
    }

    @PostMapping("/login")
    public String login(@RequestParam String username, @RequestParam String password, HttpSession session) {
        User user = authService.login(username, password);
        if (user != null) {
            session.setAttribute("user", user);
            return user.getRole().equals("CUSTOMER") ? "redirect:/cs" : "redirect:/admin";
        } else {
            return "login?error=true";
        }
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/login";
    }
}
