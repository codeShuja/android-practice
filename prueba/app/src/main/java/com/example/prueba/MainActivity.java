package com.example.prueba;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;


public class MainActivity extends AppCompatActivity {

    EditText txtNombre,txtAutor,txtPrecio,txtId;
    DB admin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtId = findViewById(R.id.txtId);
        txtNombre = findViewById(R.id.txtNombre);
        txtAutor = findViewById(R.id.txtAutor);
        txtPrecio = findViewById(R.id.txtPrecio);
    admin = new DB(this,"LibrosDB",null,1);
    admin.getReadableDatabase();
    }

    public void guardar(View v){
        SQLiteDatabase db = admin.getWritableDatabase();
        String nombre = txtNombre.getText().toString();
        String autor = txtAutor.getText().toString();
        String precio = txtPrecio.getText().toString();
        ContentValues values = new ContentValues();
        values.put("nombre",nombre);
        values.put("autor",autor);
        values.put("precio",precio);
        db.insert("libros",null,values);
        limpiar();
    }

    public void buscar(View v){
        String id = txtId.getText().toString();
        SQLiteDatabase db = admin.getReadableDatabase();
        Cursor cursor = db.rawQuery("select nombre,autor,precio from libros where id = ?", new String[]{id});
        if (cursor.moveToFirst()) {
            txtNombre.setText(cursor.getString(0));
            txtAutor.setText(cursor.getString(1));
            txtPrecio.setText(cursor.getString(2));
        }else {
            Toast.makeText(this, "Ocurrio un Error", Toast.LENGTH_SHORT).show();
            limpiar();
        }
        cursor.close();
    }

    public void actualizar(View v) {
        String id = txtId.getText().toString();
        String nombre = txtNombre.getText().toString();
        String autor = txtAutor.getText().toString();
        String precio = txtPrecio.getText().toString();

        SQLiteDatabase db = admin.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("nombre", nombre);
        values.put("autor", autor);
        values.put("precio", precio);

        int n = db.update("libros", values, "id = ?", new String[]{id});
        if (n > 0) {
            Toast.makeText(this, "Se actualizó correctamente", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Ocurrió un error al actualizar", Toast.LENGTH_SHORT).show();
        }
        limpiar();
    }

    public void eliminar(View v) {
        String id = txtId.getText().toString();
        SQLiteDatabase db = admin.getWritableDatabase();

        int n = db.delete("libros", "id = ?", new String[]{id});
        if (n > 0) {
            Toast.makeText(this, "Se eliminó correctamente", Toast.LENGTH_SHORT).show();
            limpiar();
        } else {
            Toast.makeText(this, "Ocurrió un error al eliminar", Toast.LENGTH_SHORT).show();
        }
    }

    public void  limpiar(){
        txtNombre.setText("");
        txtPrecio.setText("");
        txtAutor.setText("");
        txtId.setText("");
    }
    public void listarLibros(View v){
        Intent i = new Intent(this,List.class);
        startActivity(i);
    }
}