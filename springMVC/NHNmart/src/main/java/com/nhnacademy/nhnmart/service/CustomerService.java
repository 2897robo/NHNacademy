package com.nhnacademy.nhnmart.service;

import com.nhnacademy.nhnmart.domain.Inquiry;
import com.nhnacademy.nhnmart.repository.InquiryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private InquiryRepository inquiryRepository;

    public List<Inquiry> getInquiriesByUser(Long userId) {
        String sql = "SELECT * FROM inquiries WHERE user_id = ?";
        return jdbcTemplate.query(sql, new Object[]{userId}, new InquiryRepository.InquiryRowMapper());
    }

    public void createInquiry(Inquiry inquiry) {
        String sql = "INSERT INTO inquiries (title, category, content, created_at, status, user_id) VALUES (?, ?, ?, ?, ?, ?)";
        jdbcTemplate.update(sql, inquiry.getTitle(), inquiry.getCategory(), inquiry.getContent(), inquiry.getCreatedAt(), inquiry.getStatus(), inquiry.getUserId());
    }
}
