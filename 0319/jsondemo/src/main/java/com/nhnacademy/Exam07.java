package com.nhnacademy;

import org.json.JSONException;
import org.json.JSONObject;

public class Exam07 {
    public static void main(String[] args) {
        try {
            JSONObject customer = new JSONObject();
            String age = customer.getString("age");
            System.out.println(age);
        } catch (JSONException e) {
            System.err.println(e);
        }

    }
}
