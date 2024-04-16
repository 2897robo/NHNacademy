package com.nhnacademy;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;

public class Manager {
    private static final String FILE_PATH = "./recorder.json";
    private File file;
    private FileWriter writer;
    private FileReader reader;
    //private JSONObject rootObject;
    private JSONObject userObject;
    private JSONArray userArray;
    private JSONObject recordObject;
    private JSONArray recordArray;
    private JSONTokener jsonTokener;
    // private Item item;   item 추가, 조회 기능 미구현
    private Record record;
    private User user;

    public void write_user(int id, String name) throws IOException {
        file = new File(FILE_PATH);
        if(file.exists() && file.length() > 0) {
            reader = new FileReader(FILE_PATH);
            jsonTokener = new JSONTokener(reader);
            userObject = new JSONObject(jsonTokener);
            reader.close();
            if(userObject.has("user")) {
                userArray = userObject.getJSONArray("user");
            } else {
                userArray = new JSONArray();
            }
        } else {
            userObject = new JSONObject();
            userArray = new JSONArray();
        }
        
        user = new User(id, name);
        JSONObject NEWuserObject = new JSONObject(user);
        userArray.put(NEWuserObject);
    
        userObject.put("user", userArray);
    
        writer = new FileWriter(FILE_PATH);
        writer.write(userObject.toString(4));
        writer.flush();
        writer.close();
    }

    public void read_user() throws IOException {
        reader = new FileReader(FILE_PATH);
        jsonTokener = new JSONTokener(reader);
        userObject = new JSONObject(jsonTokener);
        System.out.println("ID \t NICKNAME");

        userArray = userObject.getJSONArray("user");
        for(int i=0; i<userArray.length(); i++) {
            userObject = userArray.getJSONObject(i);
            System.out.println(userObject.getInt("id") + "\t" + userObject.getString("nickname"));
        }

        reader.close();
    }

    public void write_record(int count, int win) throws IOException {
        file = new File(FILE_PATH);
        if(file.exists() && file.length() > 0) {
            reader = new FileReader(FILE_PATH);
            jsonTokener = new JSONTokener(reader);
            recordObject = new JSONObject(jsonTokener);
            reader.close();
            if(recordObject.has("record")) {
                recordArray = recordObject.getJSONArray("record");
            } else {
                recordArray = new JSONArray();
            }
        } else {
            recordObject = new JSONObject();
            recordArray = new JSONArray();
        }
        
        record = new Record(count, win);
        JSONObject NEWrecordObject = new JSONObject(record);
        recordArray.put(NEWrecordObject);

        recordObject.put("record", recordArray);

        writer = new FileWriter(FILE_PATH);
        writer.write(recordObject.toString(4));
        writer.flush();
        writer.close();
    }

    public void read_record() throws IOException {
        reader = new FileReader(FILE_PATH);
        jsonTokener = new JSONTokener(reader);
        recordObject = new JSONObject(jsonTokener);
        System.out.println("COUNT \t WIN");

        recordArray = recordObject.getJSONArray("record");
        for(int i=0; i<recordArray.length(); i++) {
            recordObject = recordArray.getJSONObject(i);
            System.out.println(recordObject.getInt("game_count") + "\t" + recordObject.getInt("win_count"));
        }

        reader.close();
    }
}