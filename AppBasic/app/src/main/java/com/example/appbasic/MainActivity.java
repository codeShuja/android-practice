package com.example.appbasic;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    EditText txtAutor, txtLibro, txtPrecio;
    RadioGroup rdGroup;
    RadioButton rdNarrativo,rdLirico,rdDramatico;
    CheckBox chkFantastico, chkCienciaFiccion, chkHistorico;
    Database admin;
    ListView lvHoras;
    String selechoras = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        txtAutor = findViewById(R.id.txtAutor);
        txtLibro = findViewById(R.id.txtLibro);
        txtPrecio = findViewById(R.id.txtPrecio);
        rdDramatico = findViewById(R.id.rdDramatico);
        rdNarrativo = findViewById(R.id.rdNarrativo);
        rdLirico = findViewById(R.id.rdLirico);
        rdGroup = findViewById(R.id.rdGroup);
        chkFantastico = findViewById(R.id.chkFantastico);
        chkCienciaFiccion = findViewById(R.id.chkCienciaFiccion);
        chkHistorico = findViewById(R.id.chkHistorico);
        lvHoras = findViewById(R.id.lvHoras);

        admin = new Database(this,"Biblioteca1DB",null,1);
        admin.getReadableDatabase();

        String[] horas = {"1-2 horas","2-4 horas","5 a mas"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,horas);
        lvHoras.setAdapter(adapter);

        lvHoras.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                selechoras = lvHoras.getItemAtPosition(i).toString();
            }
        });
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

    public void guardar(View v) {
        SQLiteDatabase db = admin.getWritableDatabase();
        ContentValues values = new ContentValues();

        String genero = "";
        if (rdNarrativo.isChecked()) genero = "Narrativo";
        else if (rdLirico.isChecked()) genero = "Lirico";
        else if (rdDramatico.isChecked()) genero = "Dramatico";

        String generosSecundarios = "";
        if (chkFantastico.isChecked()) generosSecundarios +="Fantastico";
        if (chkCienciaFiccion.isChecked()) generosSecundarios +="Ciencia Ficcion";
        if (chkHistorico.isChecked()) generosSecundarios +="Historico";

        String generosSecundariosTexto = generosSecundarios.isEmpty() ? "Ninguno" : String.join(", ", generosSecundarios);

        values.put("autor", txtAutor.getText().toString());
        values.put("libro", txtLibro.getText().toString());
        values.put("precio", txtPrecio.getText().toString());
        values.put("genero", genero);
        values.put("generos_secundarios", generosSecundariosTexto);
        values.put("horas",selechoras);

        db.insert("biblioteca", null, values);
        db.close();

        Toast.makeText(this, "Se guardó correctamente", Toast.LENGTH_SHORT).show();
        txtAutor.setText("");
        txtLibro.setText("");
        txtPrecio.setText("");
        rdGroup.clearCheck();
        chkFantastico.setChecked(false);
        chkCienciaFiccion.setChecked(false);
        chkHistorico.setChecked(false);
    }

    public void listar(View v) {
        Intent intent = new Intent(this, List.class);
        startActivity(intent);
    }
}