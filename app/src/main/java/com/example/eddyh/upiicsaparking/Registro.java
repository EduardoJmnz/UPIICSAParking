package com.example.eddyh.upiicsaparking;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
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


import java.util.HashMap;
import java.util.Map;

public class Registro extends AppCompatActivity {
    private EditText et1, et2, et3, et4;
    RequestQueue requestQueue;
    Button btnRegistrar;
    String boleta;
    String folio;
    String contra;
    String contra2;
    String URL = "https://upiiparking.000webhostapp.com/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);
        preparedComponents();
    }

    public void preparedComponents() {
        et1 = (EditText) findViewById(R.id.folio);
        et2 = (EditText) findViewById(R.id.password);
        et3 = (EditText) findViewById(R.id.boleta);
        et4 = (EditText) findViewById(R.id.txtConfirmar);
        btnRegistrar = (Button)findViewById(R.id.btnRegistrar);


        btnRegistrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(et3.getText().toString().isEmpty()){
                    Toast.makeText(getApplicationContext(), "El campo boleta esta vacío", Toast.LENGTH_SHORT).show();
                }else{
                    if(et1.getText().toString().isEmpty()){
                        Toast.makeText(getApplicationContext(), "El campo folio esta vacío", Toast.LENGTH_SHORT).show();
                    }else{
                        if(et2.getText().toString().isEmpty()){
                            Toast.makeText(getApplicationContext(), "El campo contraseña esta vacío", Toast.LENGTH_SHORT).show();
                        }else{
                            if(et4.getText().toString().isEmpty()){
                                Toast.makeText(getApplicationContext(), "Confirma la contraseña", Toast.LENGTH_SHORT).show();
                            }else {
                                if((et2.getText().toString()).equals(et4.getText().toString())){
                                    buscarDatos(URL, boleta = et3.getText().toString(), folio = et1.getText().toString());
                                }else{
                                    Toast.makeText(getApplicationContext(), "Las contraseñas no son iguales", Toast.LENGTH_SHORT).show();
                                }
                            }
                        }
                    }
                }
            }
        });

    }

    private void buscarDatos(final String URL, final String boleta, final String folio){
        String sum = "conexionBD.php?boleta="+boleta+"&folio="+folio;
        StringRequest stringRequest = new StringRequest(Request.Method.GET, URL+ sum, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                insertarDatos(URL, boleta, folio, contra = et2.getText().toString());
                startActivity(intent);
                Toast.makeText(getApplicationContext(), "Felicidades eres beneficiario, ahora inicia sesión", Toast.LENGTH_SHORT).show();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(), "Lo lamentamos pero no eres beneficiario.\n\n" + error.toString(), Toast.LENGTH_SHORT).show();
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                params.put("boleta", boleta);
                params.put("folio", folio);
                return params;
            }
        };

        requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);

    }

    private void insertarDatos(String URL, final String boleta, final String folio, final String contra){
        String sum = "insertarDatosBD.php?boleta="+boleta+"&folio="+folio+"&contraseña="+contra;
        StringRequest stringRequest = new StringRequest(Request.Method.GET, URL + sum, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(), "INSERTAR DATOS:\n\n"+error.toString(), Toast.LENGTH_SHORT).show();
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                params.put("boleta", boleta);
                params.put("folio", folio);
                params.put("contraseña", contra);
                return params;
            }
        };
        requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
        }

    public void cancelar (View view){
        finish();
    }
}

