package com.dannyboy.removalssystem2;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import androidx.annotation.Nullable;

public class DBHelper extends SQLiteOpenHelper {
    public DBHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table userstable(id_user integer  PRIMARY KEY AUTOINCREMENT NOT NULL, email text NOT NULL, username text NOT NULL,pass text NOT NULL, img_user BLOB)");
        db.execSQL("insert into userstable(email, username, pass) values('xd@gmail.com', 'admin','admin')");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("create table userstable(id_user integer  PRIMARY KEY AUTOINCREMENT NOT NULL, email text NOT NULL, username text NOT NULL,pass text NOT NULL)");
        db.execSQL("insert into userstable(email, username, pass) values('xd@gmail.com','admin','admin')");
    }
}