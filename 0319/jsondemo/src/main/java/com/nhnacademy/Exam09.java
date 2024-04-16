package com.nhnacademy;

import org.json.JSONException;
import org.json.JSONTokener;

public class Exam09 {

    public static void main(String[] args) {
        try {
            JSONTokener tokener = new JSONTokener(System.in);
            while (tokener.more()) {
                Object tmp = tokener.nextValue();
                System.out.println(tmp.getClass().getTypeName() + " : " + tmp);
            }
        } catch (JSONException e) {
            System.err.println(e);
        }
    }
}
