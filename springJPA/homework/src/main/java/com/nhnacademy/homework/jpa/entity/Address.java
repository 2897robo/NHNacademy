package com.nhnacademy.homework.jpa.entity;

import jakarta.persistence.*;
import lombok.*;

//CREATE TABLE Address (
//        id BIGINT AUTO_INCREMENT PRIMARY KEY,
//        name VARCHAR(255) NOT NULL,
//        city VARCHAR(255) NOT NULL,
//        user_id BIGINT NOT NULL,
//        FOREIGN KEY (user_id) REFERENCES User(id)
//        );

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String name;

    private String city;

    private long user_id;

    @ManyToOne
    @JoinColumn(name = "user_id", insertable = false, updatable = false)
    private User user;
}
