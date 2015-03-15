package com.sharp.chat.Database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.sharp.chat.Custom.DBContact;
import com.sharp.chat.Service.AppUtil;

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
        db.execSQL("INSERT INTO"+tableName+"VALUES(?, ?, ?, ?)",new String[]{contact.account,contact.grouping,contact.personalized,contact.portrait});
        Log.d("DBContactMgr", "DBMgr插入数据");
    }

    public void delContact(DBContact contact){
        db.execSQL("DELETE FROM"+tableName+"VALUES(?, ?, ?, ?)",new String[]{contact.account,contact.grouping,contact.personalized,contact.portrait});
        Log.d("DBContactMgr", "DBMgr插入数据");
    }

    public void closeDB(){
        db.close();
        Log.d("DBContactMgr", "DBMgr关闭");
    }

}
