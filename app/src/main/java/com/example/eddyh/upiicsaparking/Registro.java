package com.example.eddyh.upiicsaparking;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.JsonRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class Registro extends AppCompatActivity {
    private EditText et1, et2, et3, et4;
    RequestQueue requestQueue;
    JsonRequest jsonRequest;
    String boleta="";
    String folio="";
    String contraseña="";
    String contra2="";
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

        boleta = et3.getText().toString().trim();
        folio = et1.getText().toString().trim();
        contraseña = et2.getText().toString().trim();
        contra2 = et4.getText().toString().trim();

    }

    public void registrar(View v) {

        if (!boleta.equals("") || !folio.equals("") || !contraseña.equals("")) {
            if (contraseña.equals(contra2)) {
                buscarDatos(URL, boleta, folio);
            } else {
                Toast.makeText(this, "La contraseña no es la misma, verificalo", Toast.LENGTH_SHORT).show();
            }
        }else{
            Toast.makeText(this, "Llena los campos vacíos", Toast.LENGTH_SHORT).show();
        }
    }

    private void buscarDatos(final String URL, final String boleta, final String folio){
        String sum = "conexionBD.php?boleta="+boleta+"&folio="+folio;
        StringRequest stringRequest = new StringRequest(Request.Method.GET, URL+ sum, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                insertarDatos(URL);
                Toast.makeText(getApplicationContext(), "Felicidades, ahora inicia sesión", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);



            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(), "Lo lamentamos pero no eres beneficiario.\n\n No puedes registrarte" + error.toString(), Toast.LENGTH_SHORT).show();
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

    private void insertarDatos(String URL){
        String sum = "insertarDatosBD.php?boleta="+boleta+"&folio="+folio+"&contraseña="+contraseña;
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
                params.put("contraseña", contraseña);
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

