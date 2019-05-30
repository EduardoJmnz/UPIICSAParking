package com.example.eddyh.upiicsaparking;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.JsonRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.journeyapps.barcodescanner.BarcodeEncoder;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class Registro extends AppCompatActivity implements Response.Listener<JSONObject>, Response.ErrorListener{
    private EditText et1, et2, et3, et4;
    RequestQueue rq;
    JsonRequest jr;
    Button btnRegistrar;
    String boleta;
    String folio;
    String contra;
    private int validar;
    String contra2;
    String URL = "https://upiicsapark.xyz/";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);
        et1 = (EditText) findViewById(R.id.folio);
        et2 = (EditText) findViewById(R.id.password);
        et3 = (EditText) findViewById(R.id.boleta);
        et4 = (EditText) findViewById(R.id.txtConfirmar);
        btnRegistrar = (Button)findViewById(R.id.btnRegistrar);
        rq = Volley.newRequestQueue(this);
        btnRegistrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boleta = et3.getText().toString();
                folio = et1.getText().toString();
                contra = et2.getText().toString();
                contra2 = et4.getText().toString();
                if(boleta.isEmpty()){
                    Toast.makeText(getApplicationContext(), "El campo boleta esta vacío", Toast.LENGTH_SHORT).show();
                }else{
                    if(folio.isEmpty()){
                        Toast.makeText(getApplicationContext(), "El campo folio esta vacío", Toast.LENGTH_SHORT).show();
                    }else{
                        if(contra.isEmpty()){
                            Toast.makeText(getApplicationContext(), "El campo contraseña esta vacío", Toast.LENGTH_SHORT).show();
                        }else{
                            if(contra2.isEmpty()){
                                Toast.makeText(getApplicationContext(), "Confirma la contraseña", Toast.LENGTH_SHORT).show();
                            }else {
                                if((contra.equals(contra2))){
                                    iniciarSesion(URL, boleta ,folio);
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



    public void iniciarSesion(final String URL, final String boleta, final String folio){
        String sum = "conexionBD.php?boleta="+boleta+"&folio="+folio;
        String urlComp = URL + sum;
        jr = new JsonObjectRequest(Request.Method.GET, urlComp, null, this, this);
        rq.add(jr);

    }


    public void guardarPreferences(String boleta, String contra, String folio) {
        if(validar == 1){
            Context context = getApplicationContext();
            SharedPreferences sharedPreferences = getSharedPreferences("Login", context.MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString("boleta", boleta);
            editor.putString("password", contra);
            editor.putString("folio", folio);
            editor.commit();
        }else if(validar == 2){
            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
            startActivity(intent);
            Toast.makeText(getApplicationContext(), "No puede hacer uso de la aplicación \n\n Lo sentimos mucho :(", Toast.LENGTH_SHORT).show();
            finish();
        }
    }

    public void cancelar (View view){
        finish();
    }


    @Override
    public void onErrorResponse(VolleyError error) {
        validar = 2;
        Toast.makeText(this, "No eres beneficiario, lo lamentamos\nNo puedes usar la app", Toast.LENGTH_SHORT).show();
        finish();
    }

    @Override
    public void onResponse(JSONObject response) {
        Users user = new Users();
        validar = 1;
        guardarPreferences(boleta, contra, folio);
        Toast.makeText(getApplicationContext(), "Felicidades, ahora inicia sesión", Toast.LENGTH_SHORT).show();
        JSONArray jsonArray = response.optJSONArray("datos");
        JSONObject jsonObject = null;
        try {
            jsonObject = jsonArray.getJSONObject(0);
            user.setBoleta(jsonObject.optString("boleta"));
            user.setFolio(jsonObject.optString("folio"));

        } catch (JSONException e) {
            e.printStackTrace();
        }
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(intent);
    }


}

