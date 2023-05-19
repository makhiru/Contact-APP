package com.example.contact;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class DBHelper extends SQLiteOpenHelper {

    private static String DATABASE_NAME = "ContactAPP";
    private static String TABLE_NAME = "ContactNumber";
    private static String ID = "id";
    private static String NAME = "name";
    private static String NUMBER = "number";

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String quary = "CREATE TABLE " + TABLE_NAME + " ("
                + ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + NAME + " TEXT,"
                + NUMBER + " TEXT)";
        db.execSQL(quary);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);

    }

    public ArrayList<contact> showdata() {

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_NAME, null);

        ArrayList<contact> arrcontact = new ArrayList<>();

        while (cursor.moveToNext()) {

            int id = cursor.getInt(0);
            String name = cursor.getString(1);
            String number = cursor.getString(2);

            contact contact = new contact(R.drawable.contact, name, number, id);
            arrcontact.add(contact);
        }
        return arrcontact;
    }

    public void adddata(String name, String number) {

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues cv = new ContentValues();
        cv.put(NAME, name);
        cv.put(NUMBER, number);

        db.insert(TABLE_NAME, null, cv);
        db.close();
    }

    public void delete(int id) {

        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_NAME, ID + " = ?", new String[]{String.valueOf(id)});
    }

    public void update(contact contact) {

        SQLiteDatabase database = this.getWritableDatabase();

        String quary = "UPDATE " + TABLE_NAME + " SET "
                + NAME + " = '" + contact.getName() + "', "
                + NUMBER + " = '" + contact.getNumber() + "' WHERE "
                + ID + " = " + contact.getId();

        database.execSQL(quary);
    }
}
