package com.example.intents;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    EditText  txtNom, txtCorreo,txtTelefono;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        txtNom = findViewById(R.id.txtNombre);
        txtCorreo = findViewById(R.id.txtCorreo);
        txtTelefono = findViewById(R.id.txtTelefono);

    }

    public void guardar(View v){
        Intent i = new Intent(this,Listar.class);
        String nombre = txtNom.getText().toString();
        String correo = txtCorreo.getText().toString();
        String telefono = txtTelefono.getText().toString();
        i.putExtra("nombre",nombre);
        i.putExtra("telefono",telefono);
        i.putExtra("correo",correo);
        startActivity(i);
        limpiar();

    }

    public void limpiar(){
        txtNom.setText("");
        txtTelefono.setText("");
        txtCorreo.setText("");
    }

}