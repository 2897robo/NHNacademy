package com.nhnacademy.homework.jpa.entity;

import jakarta.persistence.*;
import lombok.*;

//CREATE TABLE OrderProduct (
//        order_id BIGINT NOT NULL,
//        product_id BIGINT NOT NULL,
//        quantity INT NOT NULL,
//        PRIMARY KEY (order_id, product_id),
//        FOREIGN KEY (order_id) REFERENCES Order(id),
//        FOREIGN KEY (product_id) REFERENCES Product(id)
//);

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@IdClass(OrderProductId.class)
public class OrderProduct {
    @Id
    private long order_id;

    @Id
    private long product_id;

    private int quantity;

    @ManyToOne
    @JoinColumn(name = "order_id", insertable = false, updatable = false)
    private Order order;

    @ManyToOne
    @JoinColumn(name = "product_id", insertable = false, updatable = false)
    private Product product;
}
