package com.example.sqliteapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DataBaseHelper extends SQLiteOpenHelper {

    public static final String USER_TABLE = "USER_TABLE";
    public static final String COLUMN_USER_NAME = "USER_NAME";
    public static final String COLUMN_USER_AGE = "USER_AGE";
    public static final String COLUMN_ACTIVE_USER = "ACTIVE_USER";
    public static final String COLUMN_ID = "ID";

    public DataBaseHelper(@Nullable Context context) {
        super(context, "user.db", null, 1);
    }

    //Creating database
    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTableStatement = " CREATE TABLE " + USER_TABLE + " (" + COLUMN_ID + " INTIGER PRIMARY KEY AUTOINCREMENT, " + COLUMN_USER_NAME + " TEXT, " + COLUMN_USER_AGE + " INT, " + COLUMN_ACTIVE_USER + " BOOL) ";

        db.execSQL(createTableStatement);

    }

    //Database changes
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public boolean addOne(UserModel userModel){

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(COLUMN_USER_NAME, userModel.getName());
        cv.put(COLUMN_USER_AGE, userModel.getAge());
        cv.put(COLUMN_ACTIVE_USER, userModel.isIsactive());

        long insert = db.insert(USER_TABLE, null, cv);
        if (insert == -1){
            return false;
        }
        else {
            return true;
        }
    }

}
