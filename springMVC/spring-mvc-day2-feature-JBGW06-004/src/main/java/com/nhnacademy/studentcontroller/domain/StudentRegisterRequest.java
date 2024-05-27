package com.nhnacademy.studentcontroller.domain;

import jakarta.validation.constraints.*;
import lombok.Value;

@Value
public class StudentRegisterRequest {
    @NotBlank(message = "이름은 필수 입력 항목입니다.")
    String id;
    String password;
    String name;

    @Email(message = "올바른 이메일 형식이 아닙니다.")
    String email;

    @Min(value = 0, message = "점수는 0점 이상이어야 합니다.")
    @Max(value = 100, message = "점수는 100점 이하여야 합니다.")
    String score;

    @NotBlank(message = "평가는 필수 입력 항목입니다.")
    @Size(max = 200, message = "평가는 200자 이하여야 합니다.")
    String comment;
}
