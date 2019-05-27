package com.example.eddyh.upiicsaparking;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.squareup.picasso.Downloader;

public class MainActivity extends AppCompatActivity {
    private EditText et1, et2;

    private Intent intent;
    boolean validado;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        et1 = (EditText)findViewById(R.id.user);
        et2 = (EditText)findViewById(R.id.password);
        //recibirDatos();
    }

    private void recibirDatos(){
        Bundle extra = getIntent().getExtras();
        String contra = extra.getString("contraseña");
        String boleta = extra.getString("usuario");

        String user = et1.getText().toString().trim();
        String cont = et2.getText().toString().trim();

        if(user == boleta && cont == contra){
            validado = true;
        }else{
            validado = false;
        }
    }

    public void ingresar(View v){
        if(validado){
            Toast.makeText(this, "Bienvenido", Toast.LENGTH_SHORT).show();
            intent = new Intent(this, estacionamiento.class);
            startActivity(intent);
        }else{
            Toast.makeText(this, "Usuario y/o Contraseña incorrectos", Toast.LENGTH_SHORT).show();
        }
    }

    public void registrarnuevo(View view){
        Intent ven = new Intent(this, Registro.class);
        startActivity(ven);
    }



}
