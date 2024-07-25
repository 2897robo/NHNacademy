package com.nhnacademy.homework.jpa.entity;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@EqualsAndHashCode
public class CartContentsId implements Serializable {
    private long cart_id;
    private long product_id;
}
