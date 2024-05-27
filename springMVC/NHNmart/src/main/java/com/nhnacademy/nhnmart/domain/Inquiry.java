package com.nhnacademy.nhnmart.domain;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class Inquiry {
    private Long id;
    private String title;
    private String category;
    private String content;
    private LocalDateTime createdAt;
    private String status; // "UNANSWERED" or "ANSWERED"
    private Long userId;
    private Answer answer;
}