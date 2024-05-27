package com.nhnacademy.nhnmart.domain;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class Answer {
    private Long id;
    private String content;
    private LocalDateTime answeredAt;
    private Long csUserId;
    private Long inquiryId;
}