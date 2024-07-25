package com.nhnacademy.homework.jpa.repository;

import com.nhnacademy.homework.jpa.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
