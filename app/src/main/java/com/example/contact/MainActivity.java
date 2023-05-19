package com.example.contact;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ImageView imgcontact;
    EditText edtname, edtnumber;
    Button btnadd, btnshow;
    String name, number;

    ArrayList<contact> arrcontact = new ArrayList<>();

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imgcontact = findViewById(R.id.imgcontact);
        edtname = findViewById(R.id.edtname);
        edtnumber = findViewById(R.id.edtnumber);
        btnadd = findViewById(R.id.btnadd);
        btnshow = findViewById(R.id.btnshow);

        DBHelper database = new DBHelper(this);

        btnadd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                arrcontact = database.showdata();

                if (!edtname.getText().toString().isEmpty() && !edtnumber.getText().toString().isEmpty()) {
                    name = edtname.getText().toString();
                    number = edtnumber.getText().toString();
                    database.adddata(name, number);
                    Toast.makeText(MainActivity.this, "Data Added Succesfully!", Toast.LENGTH_SHORT).show();

                    edtname.setText("");
                    edtnumber.setText("");

                } else {
                    Toast.makeText(MainActivity.this, "Enter proper Input!", Toast.LENGTH_SHORT).show();
                }
            }
        });

        btnshow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(MainActivity.this, ShowActivity.class);
                startActivity(intent);
            }
        });

    }
}