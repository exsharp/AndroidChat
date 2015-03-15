package com.sharp.chat.Custom;

/**
 * Created by sharp on 3/15/2015.
 */
public class DBContact {
    public String friend;
    public String grouping;
    public String personalized;
    public String portrait;

    public DBContact(){
        this.friend="";
        this.grouping="";
        this.personalized="";
        this.portrait="";
    }

    public DBContact(String account,String grouping,String personalized,String portrait){
        this.friend = account;
        this.grouping = grouping;
        this.personalized = personalized;
        this.portrait = portrait;
    }

    @Override
    public String toString() {
        return "["+friend+" "+grouping+" "+personalized+" "+portrait+" "+"]";
    }
}
