package com.nhnacademy.nhnmart.repository;

import com.nhnacademy.nhnmart.domain.Inquiry;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;

@Repository
public class InquiryRepository {
    public static class InquiryRowMapper implements RowMapper<Inquiry> {
        @Override
        public Inquiry mapRow(ResultSet rs, int rowNum) throws SQLException {
            Inquiry inquiry = new Inquiry();
            inquiry.setId(rs.getLong("id"));
            inquiry.setTitle(rs.getString("title"));
            inquiry.setCategory(rs.getString("category"));
            inquiry.setContent(rs.getString("content"));
            inquiry.setCreatedAt(rs.getTimestamp("created_at").toLocalDateTime());
            inquiry.setStatus(rs.getString("status"));
            inquiry.setUserId(rs.getLong("user_id"));
            return inquiry;
        }
    }
}
