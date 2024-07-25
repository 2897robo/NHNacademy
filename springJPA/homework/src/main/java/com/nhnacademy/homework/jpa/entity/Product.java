package com.nhnacademy.homework.jpa.entity;

import jakarta.persistence.*;
import lombok.*;

//CREATE TABLE Product (
//        id BIGINT AUTO_INCREMENT PRIMARY KEY,
//        name VARCHAR(255) NOT NULL,
//        description TEXT,
//        price DOUBLE NOT NULL,
//        stock INT NOT NULL,
//        imageUrl VARCHAR(255),
//        category_id BIGINT,
//        maker_id BIGINT,
//        FOREIGN KEY (category_id) REFERENCES Category(id),
//        FOREIGN KEY (maker_id) REFERENCES Maker(id)
//);

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String name;

    private String description;

    private double price;

    private int stock;

    private String imageUrl;

    private long category_id;

    private long maker_id;

    @ManyToOne
    @JoinColumn(name = "category_id", insertable = false, updatable = false)
    private Category category;

    @ManyToOne
    @JoinColumn(name = "maker_id", insertable = false, updatable = false)
    private Maker maker;
}
