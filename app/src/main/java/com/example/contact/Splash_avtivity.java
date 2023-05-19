package com.example.contact;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ImageView;
import android.widget.TextView;

public class Splash_avtivity extends AppCompatActivity {

    ImageView imgcontct;
    TextView txtappname;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_avtivity);

        imgcontct = findViewById(R.id.imgcontact);
        txtappname = findViewById(R.id.txtappname);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(Splash_avtivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        }, 1000);

    }
}