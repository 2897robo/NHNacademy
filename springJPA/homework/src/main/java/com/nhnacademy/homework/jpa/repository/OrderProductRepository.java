package com.nhnacademy.homework.jpa.repository;

import com.nhnacademy.homework.jpa.entity.OrderProduct;
import com.nhnacademy.homework.jpa.entity.OrderProductId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderProductRepository extends JpaRepository<OrderProduct, OrderProductId> {
}
