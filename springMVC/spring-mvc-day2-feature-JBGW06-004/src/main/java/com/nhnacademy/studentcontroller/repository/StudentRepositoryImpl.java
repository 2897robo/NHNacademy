package com.nhnacademy.studentcontroller.repository;

import com.nhnacademy.studentcontroller.domain.Student;
import com.nhnacademy.studentcontroller.exception.NoStudentException;
import com.nhnacademy.studentcontroller.exception.StudentAlreadyExistsException;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Repository
public class StudentRepositoryImpl implements StudentRepository {
    private final Map<String, Student> students = new HashMap<>();

    public StudentRepositoryImpl() {
        students.put("giuk", Student.create("giuk", "2897", "김기욱", "tmp@gmail.com", 100, "good"));
        students.put("hoho", Student.create("hoho", "0000", "김채호", "chaeho@gmail.com", 50, "soso"));
        students.put("cheol", Student.create("cheol", "1234", "김철수", "cheol@gmail.com", 0, "bad"));
    }

    @Override
    public boolean exists(String id) {
        return students.containsKey(id);
    }

    @Override
    public boolean matches(String id, String password) {
        return Optional.ofNullable(getStudent(id))
                .map(student -> student.getPassword().equals(password))
                .orElse(false);
    }

    @Override
    public Student register(String id, String password, String name, String email, int score, String comment) {
        if(exists(id)) {
            throw new StudentAlreadyExistsException("Student already exists" + id);
        }

        Student student = Student.create(id, password, name, email, score, comment);
        students.put(id, student);

        return student;
    }

    @Override
    public Student getStudent(String id) {
        return students.get(id);
    }

    @Override
    public Student updateStudent(String id, String password, String name, String email, int score, String comment) {
        if(!exists(id)) {
            throw new NoStudentException("Student does not exists" + id);
        } else {
            Student student = students.get(id);
            student.setPassword(password);
            student.setName(name);
            student.setEmail(email);
            student.setScore(score);
            student.setComment(comment);

            return student;
        }
    }
}
