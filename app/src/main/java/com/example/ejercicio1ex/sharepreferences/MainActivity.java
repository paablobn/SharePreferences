package com.example.ejercicio1ex.sharepreferences;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private SharedPreferences sharedPreferences;

    private EditText txtNombre, txtEdad;
    private Button btnGuardar, btnLimpiar;
    private ImageButton btnEliminarNombre, btnEliminarEdad;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Asociar a la variable el fichero xml (usuario.xml)
        sharedPreferences = getSharedPreferences(Constantes.USER, MODE_PRIVATE);

        txtNombre = findViewById(R.id.txtNombre);
        txtEdad = findViewById(R.id.txtEdad);
        btnGuardar = findViewById(R.id.btnGuardar);
        btnLimpiar = findViewById(R.id.btnLimpiar);
        btnEliminarNombre = findViewById(R.id.btnEliminaNombre);
        btnEliminarEdad = findViewById(R.id.btnEliminaEdad);

        inicializarValores();

        btnLimpiar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.clear();
                editor.apply();
            }
        });

        btnEliminarNombre.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.remove(Constantes.NOMBRE);
                editor.remove(Constantes.EDAD);
                editor.apply();
            }
        });

        btnGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!txtNombre.getText().toString().isEmpty() && !txtEdad.getText().toString().isEmpty()) {
                    // Escribir -> editor de sharedpreferences
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    // Inserto elementos
                    editor.putString(Constantes.NOMBRE, txtNombre.getText().toString());
                    editor.putInt(Constantes.EDAD, Integer.parseInt(txtEdad.getText().toString()));
                    editor.apply();
                    Toast.makeText(MainActivity.this, "GUARDADO", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(MainActivity.this, "RELLENA DATOS", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void inicializarValores() {
        String nombre = sharedPreferences.getString(Constantes.NOMBRE, null);
        int edad = sharedPreferences.getInt(Constantes.EDAD, -1);

        if (nombre != null) {
            txtNombre.setText(nombre);
        }
        if (edad != -1) {
            txtEdad.setText(String.valueOf(edad));
        }
    }
}