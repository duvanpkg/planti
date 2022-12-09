package com.example.planti;

import android.content.Context;
import android.content.res.Resources;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import androidx.annotation.Nullable;

import java.io.ByteArrayOutputStream;

public class Bdsqlite extends SQLiteOpenHelper {
    public Bdsqlite(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("create table users(id int primary Key, email text, password text, name text, description text)");
        sqLiteDatabase.execSQL("create table plants(id int primary Key, name String, plantKind String, imageBitmap String, description String, ratingAcum float, timesRated int)");

        sqLiteDatabase.execSQL("insert into users values(1,'admin@admin.com', 'admin','admin','soy el admin')");

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
