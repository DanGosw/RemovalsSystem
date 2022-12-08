package com.dannyboy.removalssystem2;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;


public class AccessSystem extends AppCompatActivity {

        private Button LogUp;

    @SuppressLint("SuspiciousIndentation")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_access_system);

          LogUp = (Button) findViewById(R.id.CloseSession);

    }
}