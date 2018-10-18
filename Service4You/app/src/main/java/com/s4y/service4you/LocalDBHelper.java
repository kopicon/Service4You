package com.s4y.service4you;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.s4y.service4you.Entities.User;

/**
 * Created by boldi on 2018. 09. 10..
 */

public class LocalDBHelper extends SQLiteOpenHelper {

    private static final String TAG = "DatabaseHelper";

    private static final String DATABASE_NAME = "Service4You.db";
    private static final int DATABASE_VERSION = 6;

    private static final String USER_INFO_TABLE = "User_table";
    private static final String USERNAME = "UserName";
    private static final String EMAIL = "Email";
    private static final String PASSWORD = "Password";
    private static final String USER_AVATAR = "Avatar";

    public LocalDBHelper(Context context) {
        super(context,DATABASE_NAME,null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + USER_INFO_TABLE+ "(UserName TEXT, Email TEXT, Password TEXT, Avatar INTEGER)");
    }

    public boolean addUser (User user){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValuesU = new ContentValues();
        contentValuesU.put(USERNAME,user.getUserName());
        contentValuesU.put(EMAIL,user.getEmail());
        contentValuesU.put(PASSWORD,user.getPassword());
        contentValuesU.put(USER_AVATAR, user.getAvatar());
        long UserResult = db.insert(USER_INFO_TABLE,null,contentValuesU);
        return UserResult !=1 ;
    }

    public User getUser(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT "+"UserName,Email,Password,Avatar FROM User_table",null);
        User cUser = new User(cursor.getString(0),cursor.getString(1),cursor.getString(2),cursor.getInt(3));
        return cUser;
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + USER_INFO_TABLE);
        onCreate(db);
    }
}
