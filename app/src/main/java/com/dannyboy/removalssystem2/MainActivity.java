package com.dannyboy.removalssystem2;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

private EditText user, pass;
private TextView et;
private CheckBox reme;

private boolean ActivatedRadio;
DBHelper admin;
static final String STRING_PREFERENCES = "Hello";
private static final String ConditionButton = "est.but.ses";

@Override
protected void onCreate(Bundle savedInstanceState) {
	
	setTheme(R.style.Theme_RemovalsSystem2);
	super.onCreate(savedInstanceState);
	setContentView(R.layout.activity_main);
	if (ObtCondition()){
		Intent AC =new Intent( MainActivity.this, MainMenu.class);
		startActivity(AC);
		finish();
	}
	
	et = findViewById(R.id.textView3);
	user = findViewById(R.id.txtuser);
	pass = findViewById(R.id.txtpass);
	reme = findViewById(R.id.remenber);
	Button crea = findViewById(R.id.btncreate);
	
	crea.setOnClickListener(view -> CreateAccount());
	
	ActivatedRadio = reme.isChecked();
	reme.setOnClickListener(v -> {
		if (ActivatedRadio) reme.setChecked(false);
		ActivatedRadio = reme.isChecked();
	});
}

	private void CreateAccount(){
	try {
		Intent ven=new Intent( MainActivity.this, CreateUserLogin.class);
		startActivity(ven);
		finish();
	}catch (Exception e){
		Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
	}
		}

public void  SavedLogin(){
	SharedPreferences PR = getSharedPreferences(STRING_PREFERENCES, Context.MODE_PRIVATE);
	PR.edit().putBoolean(ConditionButton, reme.isChecked()).apply();
}

public boolean ObtCondition(){
	SharedPreferences PR = getSharedPreferences(STRING_PREFERENCES, Context.MODE_PRIVATE);
	return PR.getBoolean(ConditionButton, false);
}

public void Login(View v){
	try {
		admin=new DBHelper(getBaseContext());
		SQLiteDatabase db=admin.getWritableDatabase();
		
		String Uss=user.getText().toString();
		String Pas=pass.getText().toString();
		
		@SuppressLint("Recycle") Cursor fila = db.rawQuery("select nom_w, password from workeraccess where nom_w='" + Uss + "' and password='" + Pas + "'", null);
		
		if(fila.moveToFirst()){
			
			fila.getString(0);
			fila.getString(1);
			SavedLogin();
			Intent ven=new Intent( MainActivity.this, MainMenu.class);
			startActivity(ven);
			finish();
		}
		else {
			Toast.makeText(this,"Datos incorrectos", Toast.LENGTH_LONG).show();
		}
	}
	catch (Exception e) {
		et.setText(e.getMessage());
		user.setText(e.getMessage());
		Toast.makeText(this,"Error " + e.getMessage(),Toast.LENGTH_LONG).show();
	}
}

public  static void changeCondittion(Context c, boolean xd){
	SharedPreferences pref = c.getSharedPreferences(STRING_PREFERENCES, MODE_PRIVATE);
	pref.edit().putBoolean(ConditionButton, xd).apply();
}

public boolean onKeyDown(int keyCode, KeyEvent event){
	
	if (keyCode == KeyEvent.KEYCODE_BACK){
		AlertDialog.Builder builder= new AlertDialog.Builder(this);
		builder.setMessage("¿Desea cerrar la sesión?").setPositiveButton("Si", (dialog, which) -> {
			
			Intent intent = new Intent(Intent.ACTION_MAIN);
			intent.addCategory(Intent.CATEGORY_HOME);
			intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
			startActivity(intent);
		}).setNegativeButton("Cancelar", (dialog, which) -> dialog.dismiss());
		builder.show();
	}
	return super.onKeyDown(keyCode, event);
}
}