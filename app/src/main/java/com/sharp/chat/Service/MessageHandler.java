package com.sharp.chat.Service;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import com.sharp.chat.Custom.DBContact;
import com.sharp.chat.Database.DBContactManager;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;

/**
 * Created by sharp on 3/10/2015.
 */
public class MessageHandler {
    private String type;
    private JSONArray jsonArray;
    private JSONTokener json;
    private Intent intent;
    private Context context;
    private AppUtil app;

    MessageHandler (String str,Context context) throws JSONException {
        this.context = context;
        app = (AppUtil)context.getApplicationContext();
        json =new JSONTokener(str);
        JSONObject info = (JSONObject) json.nextValue();
        type = info.getString("TYPE");
        jsonArray = info.getJSONArray("CONTENT");
    }

    public void judgeType() throws JSONException, IOException {
        intent = new Intent();
        switch (type){
            case "LOGIN":
                login(jsonArray);
                break;
            case "REGISTER":
                register(jsonArray);
                break;
            case "FRIEND_LIST":
                friendList(jsonArray);
                break;
            default:
        }
    }

    private void login(JSONArray ja) throws JSONException {
        intent.setAction("LOGIN");
        if (ja.get(0).equals("登陆成功")){
            intent.putExtra("RESULT","SUCCESS");
        } else {
            intent.putExtra("RESULT","FAIL");
            intent.putExtra("WHY",ja.get(1).toString());
        }
        context.sendBroadcast(intent);
        Log.d("MsgHandler", "身份验证");
    }
    private void register(JSONArray ja)throws JSONException{
        intent.setAction("REGISTER");
        if (ja.get(0).equals("exist")){
            intent.putExtra("RESULT","exist");
        }else{
            intent.putExtra("RESULT","success");
        }
        context.sendBroadcast(intent);
    }
    private void friendList(JSONArray ja) throws JSONException {
        DBContactManager manager = new DBContactManager(context);
        for (int i = 0 ;i<ja.length();i+=4) {
            Log.d("FriendList", ja.getString(i));
            Log.d("FriendList", ja.getString(i + 1));
            manager.addContact(new DBContact(ja.getString(i), ja.getString(i + 1), ja.getString(i+2), ja.getString(i+3)));
        }
        LinkedList<DBContact> list = new LinkedList<>(manager.Query());
        Log.d("FriendList","共有好友:"+list.size());
        for (int i = 0 ;i<list.size();i++){
            Log.d("FriendList",list.get(i).toString());
        }
        manager.closeDB();
    }
}
