package com.nhnacademy.nhnmart.service;

import com.nhnacademy.nhnmart.domain.Answer;
import com.nhnacademy.nhnmart.domain.Inquiry;
import com.nhnacademy.nhnmart.repository.InquiryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminService {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private InquiryRepository inquiryRepository;

    public List<Inquiry> getUnansweredInquiries() {
        String sql = "SELECT * FROM inquiries WHERE status = 'UNANSWERED'";
        return jdbcTemplate.query(sql, new InquiryRepository.InquiryRowMapper());
    }

    public Inquiry findInquiryById(Long id) {
        String sql = "SELECT * FROM inquiries WHERE id = ?";
        return jdbcTemplate.queryForObject(sql, new Object[]{id}, new InquiryRepository.InquiryRowMapper());
    }

    public void createAnswer(Answer answer) {
        String sql = "INSERT INTO answers (content, answered_at, cs_user_id, inquiry_id) VALUES (?, ?, ?, ?)";
        jdbcTemplate.update(sql, answer.getContent(), answer.getAnsweredAt(), answer.getCsUserId(), answer.getInquiryId());

        // Update the status of the inquiry to 'ANSWERED'
        String updateInquirySql = "UPDATE inquiries SET status = 'ANSWERED' WHERE id = ?";
        jdbcTemplate.update(updateInquirySql, answer.getInquiryId());
    }
}
