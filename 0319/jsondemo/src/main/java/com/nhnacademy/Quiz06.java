package com.nhnacademy;

import org.json.JSONArray;
import org.json.JSONObject;

public class Quiz06 {
    public static void main(String[] args) {
        String[] birds = {"갈매기","참새","펭귄"};
        JSONObject bird_ob = new JSONObject();
        bird_ob.put("조류", birds);

        String[] mams = {"사자","호랑이","말"};
        JSONObject mam_ob = new JSONObject();
        mam_ob.put("포유류", mams);

        JSONArray tmp = new JSONArray();
        tmp.put(bird_ob);
        tmp.put(mam_ob);

        JSONObject answer = new JSONObject();
        answer.put("동물", tmp);

        System.out.println(answer);
    }
}