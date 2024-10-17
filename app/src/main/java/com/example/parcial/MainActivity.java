package com.example.parcial;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ListView lvPaises;
    ViewPager vp;
    LinearLayout pag1,pag2,pag3;
    WebView wv;
    EditText txtUrl,txtNombre,txtCorreo,txtEdad;
    TextView lbDatos;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        vp = findViewById(R.id.vp);

        vp.setAdapter(new AdminPages());
    }

    //Metodo para inflar los menus

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu,menu);
        return true;
    }

    //metodo para selecionar los menus
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id  = item.getItemId();
        if (id == R.id.itmDestino) {
            vp.setCurrentItem(0);
        }else if (id == R.id.itmTravel) {
            vp.setCurrentItem(1);
        }else if (id == R.id.itmReserva) {
            vp.setCurrentItem(2);
        }
        Toast.makeText(this, ""+item, Toast.LENGTH_SHORT).show();
        return super.onOptionsItemSelected(item);
    }

    //metodo para vincular los pag
    public class AdminPages extends PagerAdapter{

        @Override
        public int getCount() {
            return 3;
        }

        @Override
        public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
            return view == object;
        }

        @Override
        public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
            container.removeView((View) object);
        }

        @NonNull
        @Override
        public Object instantiateItem(@NonNull ViewGroup container, int position) {
            View paginaActual = null;
            switch (position){
                case 0:
                    pag1 = (LinearLayout) LayoutInflater.from(MainActivity.this).inflate(R.layout.pag1,container,false);
                    paginaActual = pag1;
                    cargarPage1();
                    break;
                case 1:
                    pag2 = (LinearLayout) LayoutInflater.from(MainActivity.this).inflate(R.layout.pag2,container,false);
                    paginaActual = pag2;
                    cargarPage2();
                    break;
                case 2:
                    pag3 = (LinearLayout) LayoutInflater.from(MainActivity.this).inflate(R.layout.pag3,container,false);
                    paginaActual = pag3;
                    cargarPage3();
                    break;
            }
            //visualizar la view
            container.addView(paginaActual);
            return paginaActual;
        }
    }

    public void cargarPage1(){
        lvPaises = pag1.findViewById(R.id.lvPaises);

        ArrayList<String> paises = new ArrayList<>();
        paises.add("Perú");
        paises.add("Brazil");
        paises.add("Chile");
        paises.add("Argentina");
        paises.add("Bolivia");

        ArrayAdapter<String>adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,paises);
        lvPaises.setAdapter(adapter);

        lvPaises.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(MainActivity.this, ""+lvPaises.getItemAtPosition(i), Toast.LENGTH_SHORT).show();
            }
        });

    }

    public void cargarPage2(){
        txtUrl = pag2.findViewById(R.id.txtUrl);
        wv = pag2.findViewById(R.id.wv);

        txtUrl.setText("www.ytuqueplanes.com");
        cargar(null);
    }

    public void cargar(View v){
        String url = txtUrl.getText().toString();
       
        wv.setWebViewClient(new WebViewClient());
        wv.loadUrl("https://"+url);

    }

    public void cargarPage3(){
        txtNombre = pag3.findViewById(R.id.txtNombre);
        txtCorreo = pag3.findViewById(R.id.txtCorreo);
        txtEdad = pag3.findViewById(R.id.txtEdad);
        lbDatos = pag3.findViewById(R.id.lbDatos);
    }

    public void guardarDatos(View v){
        SharedPreferences sharedPreferences = getSharedPreferences("Datos",MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        String nombre = txtNombre.getText().toString();
        String correo = txtCorreo.getText().toString();
        String edad = txtEdad.getText().toString();

        editor.putString("nombre",nombre);
        editor.putString("correo",correo);
        editor.putString("edad", edad);
        editor.apply();

        Toast.makeText(this, "Se guardo Correctamente", Toast.LENGTH_SHORT).show();

        txtNombre.setText(nombre);
        txtCorreo.setText(correo);
        txtEdad.setText(edad);
    }

    public void mostrarDatos(View v){
        SharedPreferences sharedPreferences = getSharedPreferences("Datos",MODE_PRIVATE);
        String nombre = sharedPreferences.getString("nombre", "no hay Nombre");
        String correo = sharedPreferences.getString("correo", "no hay correo");
        String edad = sharedPreferences.getString("edad", "no hay edad");

        lbDatos.setText("Nombre: "+nombre+"\nCorreo: "+correo+"\nEdad: "+edad);
    }
}