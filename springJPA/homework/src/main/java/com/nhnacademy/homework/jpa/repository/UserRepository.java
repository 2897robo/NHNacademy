package com.nhnacademy.homework.jpa.repository;

import com.nhnacademy.homework.jpa.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<User, String> {
    // 사용자 이름으로 사용자 찾기
    User findByName(String name);

    // 사용자 역할로 사용자 목록 찾기
    List<User> findByRole(User.Role role);
}

//Create
//User user = userRepository.save(new User(id, password));
//Read
//User user = userRepository.findById(id);
//Update
//User user = userRepository.findById(id);
//user.setPassword(newPassword);
//Delete
//userRepository.deleteById(id);