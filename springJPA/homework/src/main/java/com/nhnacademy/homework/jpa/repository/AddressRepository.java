package com.nhnacademy.homework.jpa.repository;

import com.nhnacademy.homework.jpa.entity.Address;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AddressRepository extends JpaRepository<Address, Long> {
    // 사용자 ID로 주소 목록 찾기
    List<Address> findByUser_id(long user_id);
}
