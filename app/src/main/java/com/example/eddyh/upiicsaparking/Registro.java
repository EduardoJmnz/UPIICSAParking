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

import java.util.Map;

public class Registro extends AppCompatActivity implements Response.Listener<JSONObject>, Response.ErrorListener {
    private EditText  et1, et2, et3, et4;
    private  Intent intent;
    RequestQueue requestQueue;
    JsonRequest jsonRequest;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);
        preparedComponents();
        requestQueue = Volley.newRequestQueue(this);
    }
    public void preparedComponents(){
        et1 = (EditText)findViewById(R.id.folio);
        et2 = (EditText)findViewById(R.id.password);
        et3 = (EditText)findViewById(R.id.boleta);
        et4 = (EditText)findViewById(R.id.txtConfirmar);

    }

    public void registrar(View v) {
        String boleta = et3.getText().toString().trim();
        String folio = et1.getText().toString().trim();
        String contraseña = et2.getText().toString().trim();
        String contra2 = et4.getText().toString().trim();

        if(boleta != "" || folio != "" || contraseña != ""|| contra2 != "")
            if(contraseña == contra2){
                intent = new Intent(this, MainActivity.class);
                intent.putExtra("contraseña", contraseña);
                intent.putExtra("usuario", boleta);
                iniciarSesion(boleta, folio);
            }else{
                Toast.makeText(this, "La contraseña no es la misma, escribela de nuevo", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(this, "Llena los campos vacíos", Toast.LENGTH_SHORT).show();
        }

    }

    private void iniciarSesion(String boleta, String folio) {
        String url = "https://upiiparking.000webhostapp.com/conexionBD.php?boleta="+boleta+"&folio="+folio;
        jsonRequest = new JsonObjectRequest(Request.Method.GET, url, null, this, this);
        requestQueue.add(jsonRequest);
    }

    public void cancelar(View view){
        finish();
    }


    @Override
    public void onErrorResponse(VolleyError error) {
        Toast.makeText(this, "No eres beneficiario , lo lamentamos.\n"+error.toString(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onResponse(JSONObject response) {
        Users data = new Users();
        Toast.makeText(this, "Felicidades, ahora inicia sesión", Toast.LENGTH_SHORT).show();
        int validado = 1;
        JSONArray jsonArray = response.optJSONArray("datos");
        JSONObject jsonObject1 = null;
        try {
            jsonObject1 = jsonArray.getJSONObject(0);
            data.setBoleta(jsonObject1.optString("boleta"));
            data.setFolio(jsonObject1.optString("folio"));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        startActivity(intent);
    }
}
