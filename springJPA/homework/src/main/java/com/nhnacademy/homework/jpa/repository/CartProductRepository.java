package com.nhnacademy.homework.jpa.repository;

import com.nhnacademy.homework.jpa.entity.CartContents;
import com.nhnacademy.homework.jpa.entity.CartContentsId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartProductRepository extends JpaRepository<CartContents, CartContentsId> {
}
