package com.nhnacademy;

import org.json.JSONObject;

public class Exam01 {
    public static void main(String[] args) {
        JSONObject object = new JSONObject();
        object.put("name", "nhn");
        object.put("hi", "test");
        System.out.println(object + " : " + object.getClass().getTypeName());
    }
}
