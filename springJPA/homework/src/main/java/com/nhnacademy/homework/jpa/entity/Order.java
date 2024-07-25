package com.nhnacademy.homework.jpa.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

//CREATE TABLE `Order` (
//        `id` BIGINT AUTO_INCREMENT PRIMARY KEY,
//        `state` INT NOT NULL,
//        `address` VARCHAR(255) NOT NULL,
//        `quantity` BIGINT NOT NULL,
//        `date` TIMESTAMP NOT NULL,
//        `user_id` BIGINT NOT NULL
//        FOREIGN KEY (user_id) REFERENCES User(id)
//);


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private int state;

    private String address;

    private long quantity;

    private LocalDateTime date;

    private long user_id;

    @ManyToOne
    @JoinColumn(name = "user_id", insertable = false, updatable = false)
    private User user;
}
