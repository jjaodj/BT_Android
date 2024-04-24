package com.example.afinal;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;
public class DatabaseHelper  extends  SQLiteOpenHelper {

    public static final String databaseName = "Sign.db";

    public DatabaseHelper(@Nullable Context context) {
        super(context, "Sign.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase MyDatabase) {
        MyDatabase.execSQL("create Table new(email TEXT primary key, password TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase MyDatabase, int oldVersion, int newVersion) {
        MyDatabase.execSQL("drop Table if exists new");
    }

    public Boolean insertData(String email, String password){
        SQLiteDatabase MyDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("email",email);
        contentValues.put("password",password);
        long result = MyDatabase.insert("new",null,contentValues);

        if (result == -1){
            return false;
        }else {
            return true;
        }
    }

    public Boolean checkEmail(String email)
    {
        SQLiteDatabase MyDatabase = this.getWritableDatabase();
        Cursor cursor = MyDatabase.rawQuery("select * from new where email = ?",new String[]{email});

        if (cursor.getCount()>0){
            return true;
        }else {
            return false;
        }
    }

    public boolean checkEmailPassword(String email, String password)
    {
        SQLiteDatabase MyDatabase = this.getWritableDatabase();
        Cursor cursor = MyDatabase.rawQuery("select * from new where email = ? and password =?",new String[]{email, password});

        if (cursor.getCount() > 0){
            return true;
        }else {
            return false;
        }
    }

}

