package com.nhnacademy;

import org.json.JSONException;
import org.json.JSONObject;

public class Quiz05 {
    public static void main(String[] args) {
                try {
            String jsonString = "{\"model\":\"K2\",\"power\":10}";
            JSONObject jsonObject = new JSONObject(jsonString);

            String name = jsonObject.getString("model");
            System.out.println("Model : " + name);
            Object power = jsonObject.get("power");
            System.out.println("Power : "+ power);
        } catch (JSONException e) {
            System.err.println(e);
        }

    }
}
