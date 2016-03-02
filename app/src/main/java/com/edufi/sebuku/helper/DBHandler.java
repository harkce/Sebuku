package com.edufi.sebuku.helper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by habibfikri on 3/3/2016.
 */
public class DBHandler extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "com.edufi.sebuku.db";

    private static final String TABLE_SESSION = "session";
    private static final String KEY_SESSIONID = "session_id";
    private static final String KEY_USERID = "user_id";
    private static final String KEY_TOKEN = "token";
    private static final String KEY_LOGGEDIN = "loggedin";

    public DBHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
