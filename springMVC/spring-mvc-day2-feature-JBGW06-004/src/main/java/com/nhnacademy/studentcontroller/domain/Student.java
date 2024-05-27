package com.nhnacademy.studentcontroller.domain;

import lombok.Getter;
import lombok.Setter;

public class Student {
    @Getter
    @Setter
    private String id;

    @Getter
    @Setter
    private String password;

    @Getter
    @Setter
    private String name;

    @Getter
    @Setter
    private String email;

    @Getter
    @Setter
    private int score;

    @Getter
    @Setter
    private String comment;

    public Student(String id, String password, String name, String email, int score, String comment) {
        this.id = id;
        this.password = password;
        this.name = name;
        this.email = email;
        this.score = score;
        this.comment = comment;
    }

    public static Student create(String id, String password, String name, String email, int score, String comment) {
        return new Student(id, password, name, email, score, comment);
    }

    private static final String MASK = "*****";

    public static Student pwdMasked(Student student) {
        Student tmpStudent = Student.create(student.getId(), MASK, student.getName(), student.getEmail(), student.getScore(), student.getComment());
        return tmpStudent;
    }
}
