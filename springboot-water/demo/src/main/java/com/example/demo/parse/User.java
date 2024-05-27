package com.example.demo.parse;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class User {
    private int id;
    private int password;
    private String name;

    public User(int id, int password, String name) {
        this.id = id;
        this.password = password;
        this.name = name;
    }

    @Override
    public String toString() {
        return "Account(" + "id=" + id + ", password=" + password + ", name='" + name + '\'' + ')';
    }

    public boolean authenticate(int id, int password) {
        return this.id == id && this.password == password;
    }
}
