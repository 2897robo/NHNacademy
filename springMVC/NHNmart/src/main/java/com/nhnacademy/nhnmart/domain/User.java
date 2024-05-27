package com.nhnacademy.nhnmart.domain;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class User {
    private Long id;
    private String username;
    private String password;
    private String name;
    private String role; // "CUSTOMER" or "CS"
}