package com.dannyboy.removalssystem2;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {

    private EditText user, pass;
    private CheckBox reme;
    private boolean ActivatedRadio;
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
        
        user = (EditText) findViewById(R.id.txtuser);
        pass = (EditText) findViewById(R.id.txtpass);
        reme = (CheckBox) findViewById(R.id.remenber);
        
        ActivatedRadio = reme.isChecked();
        reme.setOnClickListener(v -> {
            if (ActivatedRadio) reme.setChecked(false);
            ActivatedRadio = reme.isChecked();
        });
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
        DBHelper admin=new DBHelper(this,"Mudanzas",null,1);
        SQLiteDatabase db=admin.getWritableDatabase();

        String Uss=user.getText().toString();
        String Pas=pass.getText().toString();
    
            Cursor fila = db.rawQuery("select username,pass from userstable where username='" + Uss + "' and pass='" + Pas + "'", null);
 
            if(fila.moveToFirst()){
                String us= fila.getString(0);
                String ps= fila.getString(1);
                SavedLogin();
                    Intent ven=new Intent( MainActivity.this, MainMenu.class);
                    startActivity(ven);
                    finish();
                }
            else {
                Toast toast=Toast.makeText(this,"Datos incorrectos", Toast.LENGTH_LONG);
                toast.show();
                }
            }
        catch (Exception e) {
            Toast toast=Toast.makeText(this,"Error" + e.getMessage(),Toast.LENGTH_LONG);
            toast.show();
        }
    }

public  static void changeCondittion(Context c, boolean xd){
    SharedPreferences pref = c.getSharedPreferences(STRING_PREFERENCES, MODE_PRIVATE);
    pref.edit().putBoolean(ConditionButton, xd).apply();
    }
}