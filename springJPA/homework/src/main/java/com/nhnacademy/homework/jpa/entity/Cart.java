package com.nhnacademy.homework.jpa.entity;

import jakarta.persistence.*;
import lombok.*;

//CREATE TABLE `cart` (
//        `id` BIGINT NOT NULL AUTO_INCREMENT,
//        `user_id` BIGINT NOT NULL,
//        PRIMARY KEY (`id`),
//        FOREIGN KEY (user_id) REFERENCES User(id)
//);

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private long user_id;

    @ManyToOne
    @JoinColumn(name = "user_id", insertable = false, updatable = false)
    private User user;
}

