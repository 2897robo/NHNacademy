package com.example.demo;

import com.fasterxml.jackson.annotation.JsonCreator;

public enum Role {
    ADMIN, MEMBER, GEUST;

    @JsonCreator
    public static Role jsonCreater(String role) {
        for(Role r : values()) {
            if(r.toString().toLowerCase().equals(role)) {
                return r;
            }
        }
        throw new IllegalArgumentException("Invalid role: " + role);
    }

    @Override
    public String toString() {
        return name().toLowerCase();
    }
}
