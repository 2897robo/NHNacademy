package com.nhnacademy.studentcontroller.repository;

import com.nhnacademy.studentcontroller.domain.Student;

public interface StudentRepository {
    boolean exists(String id);

    boolean matches(String id, String password);

    Student register(String id, String password, String name, String email, int score, String comment);

    public Student getStudent(String id);

    Student updateStudent(String id, String password, String name, String email, int score, String comment);
}
