package com.example.eddyh.upiicsaparking;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.squareup.picasso.Downloader;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    private EditText et1, et2;
    private Context context;
    private Intent intent;
    String boletatxt, contraseña;

    public  static final String boleta ="boleta";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        et1 = (EditText)findViewById(R.id.user);
        et2 = (EditText)findViewById(R.id.password);
    }


    public void ingresar(View v){
       leerPreferences();
    }


    private void leerPreferences(){
            SharedPreferences sharedPreferences  = getSharedPreferences("Login",context.MODE_PRIVATE);
            String user = sharedPreferences.getString("boleta", "N/A");
            String pass = sharedPreferences.getString("password", "N/a");
            if(user.equals(et1.getText().toString()) && pass.equals(et2.getText().toString())){
                Intent intent = new Intent(getApplicationContext(), Principal.class);
                Toast.makeText(getApplicationContext(), "Bienvenido ", Toast.LENGTH_SHORT).show();
                startActivity(intent);
            }else{
                Toast.makeText(getApplicationContext(), "Usuario y/o Contraseña incorrectos", Toast.LENGTH_SHORT).show();
            }
    }

    public void registrarnuevo(View view){
        Intent ven = new Intent(this, Registro.class);
        startActivity(ven);
    }

}
