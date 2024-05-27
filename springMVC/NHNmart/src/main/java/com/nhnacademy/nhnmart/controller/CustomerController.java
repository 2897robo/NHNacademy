package com.nhnacademy.nhnmart.controller;

import com.nhnacademy.nhnmart.domain.Inquiry;
import com.nhnacademy.nhnmart.domain.User;
import com.nhnacademy.nhnmart.service.CustomerService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.time.LocalDateTime;
import java.util.List;

@Controller
@RequestMapping("/cs")
public class CustomerController {
    @Autowired
    private CustomerService customerService;

    @GetMapping
    public String customerHome(HttpSession session, Model model) {
        User user = (User) session.getAttribute("user");
        List<Inquiry> inquiries = customerService.getInquiriesByUser(user.getId());
        model.addAttribute("inquiries", inquiries);
        return "customer_home";
    }

    @GetMapping("/inquiry")
    public String inquiryForm(Model model) {
        model.addAttribute("inquiry", new Inquiry());
        return "inquiry_form";
    }

    @PostMapping("/inquiry")
    public String createInquiry(@ModelAttribute Inquiry inquiry, HttpSession session) {
        User user = (User) session.getAttribute("user");
        inquiry.setUserId(user.getId());
        inquiry.setCreatedAt(LocalDateTime.now());
        inquiry.setStatus("UNANSWERED");
        customerService.createInquiry(inquiry);
        return "redirect:/cs";
    }
}