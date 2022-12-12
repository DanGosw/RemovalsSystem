package com.dannyboy.removalssystem2;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DataBaseHandler extends SQLiteOpenHelper {
Context context;
private static final String DB_NAME ="addWorker.db";
private static final int DB_VERSION =1;

public DataBaseHandler(Context context) {
	super(context, DB_NAME, null, DB_VERSION);
	this.context = context;
}
@Override
public void onCreate(SQLiteDatabase db) {
	String cretatetableUsers = "create table workerAccess(cod_w integer primary key autoincrement, image blob, nom_w text not null, ape_w text not null, correo text not null, password text not null)";
	db.execSQL(cretatetableUsers);
	db.execSQL(" insert into workeraccess(nom_w, ape_w, correo, password) values('Danny', 'Boy', 'xd@gmail.com', '120417')");
}

@Override
public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
	db.execSQL(" insert into workeraccess(nom_w, ape_w, correo, password) values('Danny', 'Boy', 'xd@gmail.com', '120417')");
}

	public void  insertImage(byte [] im, String nom, String  ape, String  correo, String pass){

	try {
		SQLiteDatabase db = this.getWritableDatabase();
		ContentValues values = new ContentValues();
		values.put("image", im);
		values.put("nom_w", String.valueOf(nom));
		values.put("ape_w", String.valueOf(ape));
		values.put("correo", String.valueOf(correo));
		values.put("password", String.valueOf(pass));
		 db.insert("workeraccess", null, values);
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
}