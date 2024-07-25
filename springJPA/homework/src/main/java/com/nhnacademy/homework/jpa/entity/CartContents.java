package com.nhnacademy.homework.jpa.entity;

import jakarta.persistence.*;
import lombok.*;

//CREATE TABLE CartContents (
//        cart_id BIGINT NOT NULL,
//        product_id BIGINT NOT NULL,
//        quantity INT NOT NULL,
//        PRIMARY KEY (cart_id, product_id),
//        FOREIGN KEY (cart_id) REFERENCES Cart(id),
//        FOREIGN KEY (product_id) REFERENCES Product(id)
//);


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@IdClass(CartContentsId.class)
public class CartContents {
    @Id
    private long cart_id;

    @Id
    private long product_id;

    private int quantity;

    @ManyToOne
    @JoinColumn(name = "cart_id", insertable = false, updatable = false)
    private Cart cart;

    @ManyToOne
    @JoinColumn(name = "product_id", insertable = false, updatable = false)
    private Product product;
}

