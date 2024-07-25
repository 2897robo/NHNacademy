package com.nhnacademy.homework.jpa.entity;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@EqualsAndHashCode
public class OrderProductId implements Serializable {
    private long order_id;
    private long product_id;
}
