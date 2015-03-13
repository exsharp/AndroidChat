package com.sharp.chat.Service;

import android.content.Context;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedWriter;
import java.io.IOException;

/**
 * Created by sharp on 3/10/2015.
 */
public class SendMessage {

    private BufferedWriter writer;
    private String type;
    private final String TYPE = "TYPE";
    private final String CONTENT = "CONTENT";
    private JSONObject jsonObject = new JSONObject();
    private JSONArray jsonArray = new JSONArray();
    private String jsonString = "";

    public SendMessage(String type,Context context){
        this.type = type;
        AppUtil app = (AppUtil)context.getApplicationContext();
        this.writer = app.getWriter();
    }

    public SendMessage(String type,BufferedWriter writer){
        this.type = type;
        this.writer = writer;
    }

    public void setJSON(String arr[]){
        for (int i = 0; i < arr.length; i++) {
            jsonArray.put(arr[i]);
        }
        this.putJSONArray();
        this.sentMsg(jsonString);
    }

    public void setJSON(JSONArray jsonArray){
        this.jsonArray = jsonArray;
        this.putJSONArray();
        this.sentMsg(jsonString);
    }

    private void putJSONArray(){
        try {
            jsonObject.put(TYPE, type);
            jsonObject.put(CONTENT, jsonArray);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        jsonString = jsonObject.toString();
    }

    private void sentMsg(final String str){
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    writer.write(str);
                    writer.flush();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
}
