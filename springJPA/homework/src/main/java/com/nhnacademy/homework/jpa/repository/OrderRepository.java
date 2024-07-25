package com.nhnacademy.homework.jpa.repository;

import com.nhnacademy.homework.jpa.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {
    // 사용자 ID로 주문 목록 찾기
    List<Order> findByUser_id(long user_id);

    // 주문 상태로 주문 목록 찾기
    List<Order> findByState(int state);

    // 사용자 ID와 주문 상태로 주문 목록 찾기
    List<Order> findByUser_idAndState(long user_id, int state);
}
