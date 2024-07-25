package com.nhnacademy.homework.jpa.repository;

import com.nhnacademy.homework.jpa.entity.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartRepository extends JpaRepository<Cart, Long> {
    // 사용자 ID로 카트 찾기
    Cart findByUser_id(long user_id);
}
