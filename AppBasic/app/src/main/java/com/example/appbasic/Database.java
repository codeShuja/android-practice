package com.example.appbasic;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class Database extends SQLiteOpenHelper {
    public Database(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE TABLE biblioteca(id INTEGER PRIMARY KEY AUTOINCREMENT, autor TEXT, libro TEXT, precio REAL, genero TEXT, generos_secundarios TEXT,horas TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public ArrayList<String> listaBiblioteca() {
        ArrayList<String> list = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor fila = db.rawQuery("SELECT * FROM biblioteca", null);

        if (fila.moveToFirst()) {
            do {
                list.add(fila.getInt(0) + " " + fila.getString(1) + " " + fila.getString(2) + " " + fila.getDouble(3)
                        + " " + fila.getString(4)+ " " + fila.getString(5)+ " " + fila.getString(6));
            } while (fila.moveToNext());
        }

        fila.close();
        db.close();
        return list;
    }


}
