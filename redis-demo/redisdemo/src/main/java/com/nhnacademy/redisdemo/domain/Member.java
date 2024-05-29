package com.nhnacademy.redisdemo.domain;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Member {
    private String id;
    private String name;
    private Integer age;
    private String password;
    @JsonSerialize(using = ToStringSerializer.class)
    private Role role;

    public Member(String name, int age, Role role, String password) {
        this.name = name;
        this.age = age;
        this.role = role;
        this.password = password;
    }
}
