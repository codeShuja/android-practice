package com.example.appbasic;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class List extends AppCompatActivity {
    ListView lvListar;
    Database admin;
    ArrayList lista;
    ArrayAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        lvListar = findViewById(R.id.lvListar);

        admin = new Database(this,"Biblioteca1DB",null,1);
        cargarLista();
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.itmCrear) {
            Intent intent = new Intent(this,MainActivity.class);
            startActivity(intent);
        } else if (id == R.id.itmListar) {
            Intent intent = new Intent(this,List.class);
            startActivity(intent);
        }else if (id == R.id.itmFavorito) {
            Intent intent = new Intent(this,Favorite.class);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }

    public void cargarLista(){
        lista = admin.listaBiblioteca();
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,lista);
        lvListar.setAdapter(adapter);
    }

    public void guardar(View v){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

}