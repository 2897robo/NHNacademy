package com.nhnacademy.minidooraydgateway.dto;

import lombok.Builder;

@Builder
public record MilestoneGetByProjectIdRequestDto(long projectId) {
}
