package com.sharp.chat.Service;

import android.app.Application;

import java.io.BufferedWriter;

/**
 * Created by sharp on 3/10/2015.
 */
public class AppUtil extends Application {

    private String account = null;
    private String password = null;
    private String currentActivity = "";
    private String chatWithWho = "";
    private BufferedWriter writer;

    public String getAccount(){
        return account;
    }
    public void setUser(String name,String word){
        account = name;
        password = word;
    }

    public void setCurrentActivity(String activity){
        currentActivity = activity;
    }
    public String getCurrentActivity() {
        return currentActivity;
    }

    public void setChatWithWho(String who) {
        chatWithWho = who;
    }
    public String getChatWithWho() {
        return chatWithWho;
    }

    public void setWriter(BufferedWriter writer){
        this.writer = writer;
    }
    public BufferedWriter getWriter(){
        return writer;
    }

}
