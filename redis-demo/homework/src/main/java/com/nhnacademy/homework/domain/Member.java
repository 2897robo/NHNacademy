package com.nhnacademy.homework.domain;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

//직렬화를 통한 Redis 저장 가능
@Getter
@Setter
public class Member implements Serializable {
    private String id;
    private String name;
    private String password;

    @JsonSerialize(using = ToStringSerializer.class)
    private Integer age;

    @JsonSerialize(using = ToStringSerializer.class)
    private Role role;
}
