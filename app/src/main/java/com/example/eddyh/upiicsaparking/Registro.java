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
    private static String URL_REGIST = "";
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
        iniciarSesion();
    }

    private void iniciarSesion() {
        URL_REGIST = "";
        jsonRequest = new JsonObjectRequest(Request.Method.GET, URL_REGIST, null, this, this);
        requestQueue.add(jsonRequest);
    }

    public void cancelar(View view){
        finish();
    }


    @Override
    public void onErrorResponse(VolleyError error) {
        Toast.makeText(getBaseContext(), "No eres beneficiario , lo lamentamos."+error.toString(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onResponse(JSONObject response) {
        Users data = new Users();
        Toast.makeText(getBaseContext(), "Felicidades, ahora inicia sesión", Toast.LENGTH_SHORT).show();
        JSONArray jsonArray = response.optJSONArray("datos");
        JSONObject jsonObject = null;
        try {
            jsonObject = jsonArray.getJSONObject(0);
            data.setBoleta(jsonObject.optString("boleta"));
            data.setFolio(jsonObject.optString("folio"));
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
