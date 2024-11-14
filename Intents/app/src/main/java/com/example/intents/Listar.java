package com.example.intents;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class Listar extends AppCompatActivity {
    TextView lvNom, lvTelf,lvCor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listar);
        lvNom = findViewById(R.id.lvNombre);
        lvTelf = findViewById(R.id.lvTelefono);
        lvCor = findViewById(R.id.lvCorreo);
        listar();
    }

    public void listar(){
        String nombre = getIntent().getStringExtra("nombre");
        String correo = getIntent().getStringExtra("correo");
        String telefono = getIntent().getStringExtra("telefono");
        lvNom.setText("Nombre: " + nombre);
        lvCor.setText("Correo: " + correo);
        lvTelf.setText("Telefono: " + telefono);
    }

    public void guardar(View v){
        finish();
    }
}