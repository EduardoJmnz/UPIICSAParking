package com.example.eddyh.upiicsaparking;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {
    public DBHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table usuarios(codigo integer primary key autoincrement, usuario text, password text)");
        db.execSQL("insert into usuarios values(01, 'a','a')");
        db.execSQL("create table carrito(producto text, precio integer, p integer)");
        db.execSQL("insert into carrito values('producto', '0','0')");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("create table usuarios(codigo integer primary key autoincrement, usuario text, password text)");
        db.execSQL("insert into usuarios values(01, 'a','a')");
        db.execSQL("create table carrito(producto text, precio integer, p integer)");
        db.execSQL("insert into carrito values('producto', 0,0)");


    }


}

