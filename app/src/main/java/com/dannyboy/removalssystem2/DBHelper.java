package com.dannyboy.removalssystem2;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Bitmap;
import android.widget.Toast;

import androidx.annotation.Nullable;

import java.io.ByteArrayOutputStream;

public class DBHelper extends SQLiteOpenHelper {
private static final String DB_NAME ="addWorker.db";
private static final int DB_VERSION =1;

private byte[] imageBytes;
Context context;


public DBHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
        this.context = context;
    }

@Override
    public void onCreate(SQLiteDatabase db) {
    
    String cretatetableUsers = "create table workeraccess(cod_w integer primary key autoincrement, image blob, nom_w text not null, ape_w text not null, correo text not null, password text not null)";
    db.execSQL(cretatetableUsers);
        db.execSQL("insert into workeraccess( nom_w, ape_w, correo, password) values('Danny', 'Boy', 'xd@gmail.com', '120417')");
        
        db.execSQL("create table userstable(id_user integer  PRIMARY KEY AUTOINCREMENT NOT NULL, email text NOT NULL, username text NOT NULL,pass text NOT NULL, img_user BLOB)");
        db.execSQL("insert into userstable(email, username, pass) values('xd@gmail.com', 'admin','admin')");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    
        db.execSQL("insert into workeraccess( nom_w, ape_w, correo, password) values('Danny', 'Boy', 'xd@gmail.com', '120417')");
        
        db.execSQL("create table userstable(id_user integer  PRIMARY KEY AUTOINCREMENT NOT NULL, email text NOT NULL, username text NOT NULL,pass text NOT NULL)");
        db.execSQL("insert into userstable(email, username, pass) values('xd@gmail.com','admin','admin')");
    }
public void storeData(ModelClass modelClass){
    SQLiteDatabase db = this.getWritableDatabase();
    Bitmap bitmap= modelClass.getImg();
    ByteArrayOutputStream byteArray = new ByteArrayOutputStream();
    bitmap.compress(Bitmap.CompressFormat.JPEG, 100, byteArray);
    
    ContentValues values = new ContentValues();
    values.put("image", imageBytes);
    values.put("nom_w", modelClass.getUser());
    values.put("ape_w", modelClass.getLast());
    values.put("correo", modelClass.getMail());
    values.put("password", modelClass.getPass());
    long checkIfQueryRun =  db.insert("workerAccess", null, values);
    if (checkIfQueryRun != -1){
        db.close();
    }
    else {
        Toast.makeText(context, "Algo salió mal :(", Toast.LENGTH_SHORT).show();
    }
}
public Cursor getUser(){
    SQLiteDatabase db = this.getReadableDatabase();
    return db.rawQuery("select * from workerAccess", null);
}
}