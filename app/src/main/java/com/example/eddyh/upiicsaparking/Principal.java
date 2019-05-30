package com.example.eddyh.upiicsaparking;

import android.content.Intent;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class Principal extends AppCompatActivity {

    ViewPager mPager;
    ImageAdapter adapter;
    Button button;
    TextView disponibles, capacidad;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);
        mPager = findViewById(R.id.pager);
        adapter = new ImageAdapter(getSupportFragmentManager());
        mPager.setAdapter(adapter);

        capacidad = (TextView)findViewById(R.id.capacidad);
        capacidad.setText(R.string.capacidad_inge);
        LugaresDisponibles();

        button = (Button)findViewById(R.id.QR);
    }

    private void LugaresDisponibles() {
        disponibles = (TextView)findViewById(R.id.lugaresDisponibles);
        disponibles.setText("Lugares disponibles: 30");
    }


    public void mostrarQR(View view) {
        Intent intent = new Intent(this, MostrarQr.class);
        startActivity(intent);
        disponibles.setText("Lugares disponibles : 29");
    }
}
