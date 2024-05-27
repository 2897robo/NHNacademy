package com.nhnacademy.nhnmart.controller;

import com.nhnacademy.nhnmart.domain.Answer;
import com.nhnacademy.nhnmart.domain.Inquiry;
import com.nhnacademy.nhnmart.domain.User;
import com.nhnacademy.nhnmart.service.AdminService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    private AdminService adminService;

    @GetMapping
    public String adminHome(Model model) {
        List<Inquiry> unansweredInquiries = adminService.getUnansweredInquiries();
        model.addAttribute("inquiries", unansweredInquiries);
        return "admin_home";
    }

    @GetMapping("/answer/{id}")
    public String answerForm(@PathVariable Long id, Model model) {
        Inquiry inquiry = adminService.findInquiryById(id);
        model.addAttribute("inquiry", inquiry);
        model.addAttribute("answer", new Answer());
        return "answer_form";
    }

    @PostMapping("/answer")
    public String createAnswer(@ModelAttribute Answer answer, HttpSession session) {
        User csUser = (User) session.getAttribute("user");
        answer.setCsUserId(csUser.getId());
        answer.setAnsweredAt(LocalDateTime.now());
        adminService.createAnswer(answer);
        return "redirect:/admin";
    }
}