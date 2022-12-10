package com.dannyboy.removalssystem2;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.content.res.Resources;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.widget.ImageView;
import android.widget.Toast;
import androidx.annotation.Nullable;
import androidx.core.content.res.ResourcesCompat;
import java.util.Objects;

public class DataBaseHandler extends SQLiteOpenHelper {

public DataBaseHandler(@Nullable Context context, String s, Object o, int i) {
	super(context, "addWorker.db", null, 1);
	BitmapFactory.Options options = new BitmapFactory.Options();
	options.inScaled = false;
	Bitmap source = BitmapFactory.decodeResource(Objects.requireNonNull(context).getResources(),
		R.drawable.user_img, options);
	img.setImageBitmap(source);
}
ImageView img;
@Override
public void onCreate(SQLiteDatabase db) {
	
	db.execSQL("create table  workerAccess(cod_work integer primary key autoincrement, image_work blob not null, nom_work text not null, ape_work text not null, correo text not null, password text not null)");
	Bitmap bitmap = ((BitmapDrawable) Objects.requireNonNull(ResourcesCompat.getDrawable(Resources.getSystem(), R.drawable.user_img, null))).getBitmap();
	img.setImageBitmap(bitmap);

	db.execSQL("insert into workerAccess(image_work, nom_work, ape_work, correo, password) values('Danny', 'Boy', 'xd@gmail.com', '120417')");
}

@SuppressLint("ResourceType")
@Override
public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
	db.execSQL("create table  workerAccess(cod_work integer primary key autoincrement, image_work blob, nom_work text not null, ape_work text not null, correo text not null, password text not null)");

	
	db.execSQL(" insert into workerAccess(nom_work, ape_work, correo, password) values('Danny', 'Boy', 'xd@gmail.com', '120417')");
}
	public void  insertImage(byte [] im, String nom, String  ape, String  correo, String pass){

	try {
		SQLiteDatabase db = this.getWritableDatabase();
		ContentValues values = new ContentValues();
		values.put("image_work", im);
		values.put("nom_work", String.valueOf(nom));
		values.put("ape_work", String.valueOf(ape));
		values.put("correo", String.valueOf(correo));
		values.put("password", String.valueOf(pass));
		 db.insert("workerAccess", null, values);
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
}