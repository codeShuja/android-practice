package com.example.appbasic;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Favorite extends AppCompatActivity {
    EditText txtNombre, txtContenido, txtUrl;
    WebView vw;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorite);
        txtNombre = findViewById(R.id.txtNombre);
        txtContenido = findViewById(R.id.txtContenido);
        txtUrl = findViewById(R.id.txtUrl);
        vw = findViewById(R.id.vw);

        txtUrl.setText("hubinformacion.continental.edu.pe");
        web();

        txtUrl.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                web();
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });

    }

    public void web(){
        String url = txtUrl.getText().toString();
        vw.setWebViewClient(new WebViewClient());
        vw.loadUrl("https://"+url);
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

    public void guardar(View v){
        String nombre = txtNombre.getText().toString();
        String contenido = txtContenido.getText().toString();
        try {
            OutputStreamWriter writer = new OutputStreamWriter(openFileOutput(nombre,MODE_PRIVATE));
            writer.write(contenido);
            writer.flush();
            writer.close();
            Toast.makeText(this, "Se guardo correctamente", Toast.LENGTH_SHORT).show();
            txtNombre.requestFocus();
            txtContenido.setText("");
            txtNombre.setText("");

        }catch (IOException e){
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    public void listar(View v){
        String nombre = txtNombre.getText().toString();
        try {
            BufferedReader bf = new BufferedReader(new InputStreamReader(openFileInput(nombre)));
            StringBuilder builder = new StringBuilder();
            String linea;
            while ((linea=bf.readLine())!=null){
                builder.append(linea).append("\n");
            }
            txtContenido.setText(builder);
            bf.close();
            txtNombre.setText("");
        }catch (IOException e){
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

}