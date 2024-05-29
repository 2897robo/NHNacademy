package com.example.demo;

import com.fasterxml.jackson.annotation.JsonCreator;

public enum Locale {
    KO, JP, EN;

    @JsonCreator
    public static Locale jsonCreater(String locale) {
        for(Locale l : values()) {
            if(l.toString().toLowerCase().equals(locale.toLowerCase())) {
                return l;
            }
        }
        throw new IllegalArgumentException("Invalid locale " + locale);
    }

    @Override
    public String toString() {
        return name().toLowerCase();
    }
}
