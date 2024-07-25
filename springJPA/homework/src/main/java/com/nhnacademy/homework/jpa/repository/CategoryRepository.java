package com.nhnacademy.homework.jpa.repository;

import com.nhnacademy.homework.jpa.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}
