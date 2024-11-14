package com.example.prueba;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;


public class List extends AppCompatActivity {
    ListView lvListar;
    DB admin;
    ArrayList<String> list;
    ArrayAdapter<String>adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        lvListar = findViewById(R.id.lvListar);
        admin = new DB(this,"LibrosDB",null,1);
        admin.getReadableDatabase();
        listar();
    }

    public void listar(){
        list= admin.listaLibros();
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,list);
        lvListar.setAdapter(adapter);
    }
}