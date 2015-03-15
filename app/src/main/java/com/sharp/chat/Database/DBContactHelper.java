package com.sharp.chat.Database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.sharp.chat.Service.AppUtil;

/**
 * Created by sharp on 3/15/2015.
 */
public class DBContactHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "contact.db";
    private static final int DATABASE_VERSION = 1;
    private String tableName="";

    public DBContactHelper(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        AppUtil app = (AppUtil)context.getApplicationContext();
        tableName = app.getAccount();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("(CREATE TABLE IF NOT EXISTS"+tableName+
                "(account VARCHAR,grouping VARCHAR,personalized VARCHAR,portrait)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("ALTER TABLE tableName ADD COLUMN other STRING");
    }
}
