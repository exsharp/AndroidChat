package com.sharp.chat.Database;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.sharp.chat.Custom.DBContact;
import com.sharp.chat.Service.AppUtil;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by sharp on 3/15/2015.
 */
public class DBContactManager {
    private DBContactHelper helper;
    private SQLiteDatabase db;
    private String tableName="";

    public DBContactManager(Context context) {
        helper = new DBContactHelper(context);
        db = helper.getWritableDatabase();

        AppUtil app = (AppUtil)context.getApplicationContext();
        tableName = app.getAccount();
        Log.d("DBContactMgr","构造DBMgr");
    }

    public void addContact(DBContact contact){
        db.execSQL("REPLACE INTO "+tableName+" VALUES(?, ?, ?, ?)",new String[]{contact.friend,contact.grouping,contact.personalized,contact.portrait});
        Log.d("DBContactMgr", "DBMgr插入数据");
    }

    public void delContact(DBContact contact){
        db.execSQL("DELETE FROM "+tableName+" VALUES(?, ?, ?, ?)",new String[]{contact.friend,contact.grouping,contact.personalized,contact.portrait});
        Log.d("DBContactMgr", "DBMgr删除数据");
    }

    public List<DBContact> Query(){
        LinkedList<DBContact> contacts = new LinkedList<>();
        Cursor c = queryTheCursor();
        while (c.moveToNext()){
            DBContact contact = new DBContact();
            contact.friend = c.getString(c.getColumnIndex("friend"));
            contact.grouping = c.getString(c.getColumnIndex("grouping"));
            contact.personalized = c.getString(c.getColumnIndex("personalized"));
            contact.portrait = c.getString(c.getColumnIndex("portrait"));
            contacts.add(contact);
        }
        c.close();
        return contacts;
    }

    public Cursor queryTheCursor(){
        Cursor c = db.rawQuery("select * from "+tableName,new String[]{});
        return c;
    }

    public void closeDB(){
        db.close();
        Log.d("DBContactMgr", "DBMgr关闭");
    }

}
