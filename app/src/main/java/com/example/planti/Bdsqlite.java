package com.example.planti;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Bitmap;

import androidx.annotation.Nullable;

public class Bdsqlite extends SQLiteOpenHelper {


    public Bdsqlite(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("create table users(id int primary Key, email text, password text)");
        sqLiteDatabase.execSQL("create table plants(id int primary Key, name String, plantKind String, imageBitmap String, description String)");

        sqLiteDatabase.execSQL("insert into users values(1,'admin@admin.com', 'admin')");
        Bitmap bitmap = Bitmap.createBitmap(100, 100, Bitmap.Config.ARGB_8888);
        sqLiteDatabase.execSQL("insert into plants values(1,'planta prueba', 'rosas', '','rosas blancas')");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
