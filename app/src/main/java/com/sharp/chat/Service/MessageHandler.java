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
            case "REGISTER":
                register(jsonArray);
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
}
