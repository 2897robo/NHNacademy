package com.nhnacademy.homework.domain;

import com.fasterxml.jackson.annotation.JsonCreator;

public enum Role {
    ADMIN, MEMBER, GOOGLE;

    @JsonCreator
    public static Role jsonCreator(String role) {
        for (Role getRoleValue : Role.values()) {
            if (getRoleValue.toString().toLowerCase().equals(role.toLowerCase())) {
                return getRoleValue;
            }
        }
        throw new IllegalArgumentException("Unknown locale: " + role);
    }
}
