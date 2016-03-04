package com.edufi.sebuku.helper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.CursorIndexOutOfBoundsException;
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
    private static final String KEY_FULLNAME = "fullname";
    private static final String KEY_EMAIL = "email";
    private static final String KEY_TOKEN = "token";
    private static final String KEY_STATUS = "status";

    public DBHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_SESSION_TABLE = "CREATE TABLE "
                + TABLE_SESSION + "("
                + KEY_SESSIONID + " INTEGER PRIMARY KEY, "
                + KEY_USERID + " INTEGER, "
                + KEY_FULLNAME + " TEXT, "
                + KEY_EMAIL + " TEXT, "
                + KEY_TOKEN + " TEXT, "
                + KEY_STATUS + " INTEGER"
                + ")";
        db.execSQL(CREATE_SESSION_TABLE);
    }

    public void insertData() {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_SESSIONID, 1);
        values.put(KEY_USERID, 0);
        values.put(KEY_FULLNAME, "");
        values.put(KEY_EMAIL, "");
        values.put(KEY_TOKEN, "");
        values.put(KEY_STATUS, 0);
        db.insert(TABLE_SESSION, null, values);
    }

    public int login(Session session) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_USERID, session.getUser_id());
        values.put(KEY_FULLNAME, session.getFullname());
        values.put(KEY_EMAIL, session.getEmail());
        values.put(KEY_TOKEN, session.getToken());
        values.put(KEY_STATUS, 1);
        return db.update(TABLE_SESSION, values, KEY_SESSIONID + " = ? ", new String[] {"1"});
    }

    public int logout() {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_USERID, 0);
        values.put(KEY_FULLNAME, "");
        values.put(KEY_EMAIL, "");
        values.put(KEY_TOKEN, "");
        values.put(KEY_STATUS, 0);
        return db.update(TABLE_SESSION, values, KEY_SESSIONID + " = ? ", new String[] {"!"});
    }

    public Session getSession() {
        SQLiteDatabase db = this.getReadableDatabase();
        Session session = null;
        Cursor cursor = db.query(TABLE_SESSION, new String[] {
                KEY_USERID, KEY_FULLNAME, KEY_EMAIL, KEY_TOKEN, KEY_STATUS
        }, KEY_SESSIONID + " = ? ", new String[] {"1"}, null, null, null, null);
        try {
            if (cursor != null) {
                cursor.moveToFirst();
                session = new Session();
                session.setUser_id(cursor.getInt(0));
                session.setFullname(cursor.getString(1));
                session.setEmail(cursor.getString(2));
                session.setToken(cursor.getString(3));
                if (cursor.getString(4).equals("1")) {
                    session.setStatus(true);
                } else {
                    session.setStatus(false);
                }
            }
        } catch (CursorIndexOutOfBoundsException ex) {
            insertData();
            return getSession();
        }
        return session;
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_SESSION);
        onCreate(db);
        insertData();
    }
}
