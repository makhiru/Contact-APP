package com.example.contact;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

public class ShowActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    ArrayList<contact> arrcontact = new ArrayList<>();
    ContactAdapter adapter;
    DBHelper database;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show);
        database = new DBHelper(this);

        recyclerView = findViewById(R.id.recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        arrcontact = database.showdata();

        adapter = new ContactAdapter(this, arrcontact, onclick);
        recyclerView.setAdapter(adapter);

    }

    contact_onclick onclick = new contact_onclick() {
        @Override
        public void onclick(contact contact, boolean update) {

            if (update) {

                Dialog dialog = new Dialog(ShowActivity.this);
                dialog.setCancelable(false);
                dialog.setContentView(R.layout.update_box);

                EditText edtname = dialog.findViewById(R.id.updateedtname);
                EditText edtnumber = dialog.findViewById(R.id.updateedtnumber);
                Button save = dialog.findViewById(R.id.updatebtddnsave);
                Button close = dialog.findViewById(R.id.updatebtnclose);

                edtname.setText(contact.getName());
                edtnumber.setText(contact.getNumber());

                save.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        if (!edtname.getText().toString().isEmpty() && !edtnumber.getText().toString().isEmpty()) {
                            String name = edtname.getText().toString();
                            String number = edtnumber.getText().toString();
                            database.update(new contact(R.drawable.contact, name, number, contact.getId()));
                            arrcontact.clear();
                            arrcontact.addAll(database.showdata());
                            adapter.notifyDataSetChanged();

                            Toast.makeText(ShowActivity.this, "Data Added Succesfully!", Toast.LENGTH_SHORT).show();

                            edtname.setText("");
                            edtnumber.setText("");

                            dialog.dismiss();

                        } else {
                            Toast.makeText(ShowActivity.this, "Enter proper Input!", Toast.LENGTH_SHORT).show();
                        }

                    }
                });

                close.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });

                dialog.show();

            } else {

                AlertDialog.Builder builder = new AlertDialog.Builder(ShowActivity.this);

                builder.setTitle("Delete Contact");
                builder.create();
                builder.setCancelable(false);
                builder.setIcon(R.drawable.ic_baseline_delete_24);
                builder.setMessage("Are You sure Delete Contact?");

                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        database.delete(contact.getId());
                        arrcontact.clear();
                        arrcontact.addAll(database.showdata());
                        adapter.notifyDataSetChanged();

                    }
                });
                builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(ShowActivity.this, "No Delete Contact", Toast.LENGTH_SHORT).show();
                    }
                });
                builder.show();


            }

        }

    };

}