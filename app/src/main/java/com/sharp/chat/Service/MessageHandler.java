package com.sharp.chat.Service;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.IOException;

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
            default:
        }
    }

    public Intent getMyIntent() {
        return intent;
    }
    private void movFriend(JSONArray ja)throws JSONException{
        intent.setAction("MOVFRIEND");
        intent.putExtra("STATE",ja.get(0).toString());
        context.sendBroadcast(intent);
        Log.d("MsgHandler", "移动好友");
    }

    private void delGroup(JSONArray ja) throws JSONException{
        intent.setAction("DELGROUP");
        intent.putExtra("STATE",ja.get(0).toString());
        context.sendBroadcast(intent);
        Log.d("MsgHandler","删除组");
    }

    private void addGroup(JSONArray ja) throws JSONException {
        intent.setAction("ADDGROUP");
        intent.putExtra("STATE",ja.get(0).toString());
        context.sendBroadcast(intent);
        Log.d("MsgHandler","添加组");
    }

    private void delFriend(JSONArray ja) throws JSONException {
        intent.setAction("DELFRIEND");
        intent.putExtra("STATE",ja.get(0).toString());
        context.sendBroadcast(intent);
        Log.d("MsgHandler","删除好友");
    }

    private void addFriend(JSONArray ja) throws JSONException {
        intent.setAction("ADDFRIEND");
        intent.putExtra("STATE",ja.get(0).toString());
        context.sendBroadcast(intent);
        Log.d("MsgHandler","添加好友");
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
}
