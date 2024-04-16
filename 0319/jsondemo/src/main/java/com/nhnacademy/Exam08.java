package com.nhnacademy;

import org.json.JSONArray;
import org.json.JSONObject;

public class Exam08 {
    public static void main(String[] args) {
        String[] cities = new String[] { "서울", "부산", "광주", "대구", "대전", "인천", "울산" };

        JSONArray jsonArray = new JSONArray();
        for (String city : cities) {
            jsonArray.put(city);
        }

        JSONObject object = new JSONObject();
        object.put("도시", jsonArray);

        System.out.println(object.toString());
    }
}
