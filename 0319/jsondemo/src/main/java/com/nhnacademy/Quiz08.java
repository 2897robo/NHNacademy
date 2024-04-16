package com.nhnacademy;

import org.json.JSONArray;
import org.json.JSONException;

public class Quiz08 {
    public static void main(String[] args) {
        try {
            JSONArray jsonArray = new JSONArray();

            jsonArray.put("true");
            jsonArray.put(1);
            jsonArray.put(true);

            System.out.println("1은 " + jsonArray.get(1) + "번째에 위치합니다.");
        } catch(JSONException e) {
            System.err.println(e);
        }
    }
}