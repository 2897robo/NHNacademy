package com.nhnacademy.homework.jpa.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;

import java.time.LocalDateTime;

//CREATE TABLE User (
//        id VARCHAR(50) NOT NULL,
//        password VARCHAR(20) NOT NULL,
//        name VARCHAR(255),
//        role VARCHAR(255),
//        birthday TIMESTAMP,
//        point INT,
//        joinDate TIMESTAMP,
//        lastLogin TIMESTAMP,
//        PRIMARY KEY (id)
//);

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class User {

    @Id
    private String id;

    private String password;

    private String name;

    private Role role;

    private LocalDateTime birthday;

    private int point;

    private LocalDateTime joinDate;

    private LocalDateTime lastLogin;

    public enum Role {
        ADMIN, USER;
    }
}