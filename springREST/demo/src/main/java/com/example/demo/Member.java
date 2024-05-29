package com.example.demo;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.Getter;

@Getter
public class Member {
    @JsonProperty("user-name")
    private String name;
    @JsonProperty("class")
    private String clazz;
    private int age;

    @JsonSerialize(using = ToStringSerializer.class)
    private Role role;

    public Member(String name, int age, Role role) {
        this.name = name;
        this.age = age;
        this.role = role;
        this.clazz = "A";
    }
}
