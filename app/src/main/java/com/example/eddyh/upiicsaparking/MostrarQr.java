package com.example.eddyh.upiicsaparking;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.JsonRequest;
import com.android.volley.toolbox.Volley;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.journeyapps.barcodescanner.BarcodeEncoder;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MostrarQr extends AppCompatActivity implements Response.Listener<JSONObject>, Response.ErrorListener  {

    ImageView image;
    Users user;
    String URL = "https://upiicsapark.xyz/";
    RequestQueue rq;
    JsonRequest jr;
    int validar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mostrar_qr);
        rq = Volley.newRequestQueue(this);
        iniciarSesion();
    }
    public void iniciarSesion(){
        SharedPreferences sharedPreferences  = getSharedPreferences("Login",getApplicationContext().MODE_PRIVATE);
        String boleta = sharedPreferences.getString("boleta", "No hay dato boleta");
        String sum = "generarQR.php?boleta="+boleta;
       String urlComp = URL + sum;
       jr = new JsonObjectRequest(Request.Method.GET, urlComp, null, this, this);
        rq.add(jr);
    }

    public void leerPreferences(){
        SharedPreferences sharedPreferences  = getSharedPreferences("Login",getApplicationContext().MODE_PRIVATE);
        String boleta = sharedPreferences.getString("boleta", "No hay dato boleta");
        String nombre = sharedPreferences.getString("nombre", "Np hay dato nombre");
        String apaterno = sharedPreferences.getString("ap_pat", "No hay dato apellido");
        String modelo = sharedPreferences.getString("modelo", "No hay dato modelo");
        String color = sharedPreferences.getString("color", "No hay dato color");
        String placa = sharedPreferences.getString("placa", "No hay dato placa");

        generarQR(boleta, nombre, apaterno, modelo, color, placa);

    }

    public void mostrarPark(View view) {
        Intent intent = new Intent(this, Principal.class);
        startActivity(intent);
    }

    public void generarQR(String boleta, String nombre, String apaterno, String modelo, String color, String placa){

        String codigo = boleta +"\n"+nombre+"\n"+apaterno+"\n"+modelo+"\n"+color+"\n"+placa;
        //GENERAMOS EL QR CON LOS DATOS OBTENIDOS

        MultiFormatWriter multiFormatWriter = new MultiFormatWriter();
        try {
            BitMatrix bitMatrix = multiFormatWriter.encode(codigo, BarcodeFormat.QR_CODE, 200, 200);
            BarcodeEncoder barcodeEncoder = new BarcodeEncoder();
            Bitmap bitmap = barcodeEncoder.createBitmap(bitMatrix);
            image.setImageBitmap(bitmap);
        } catch (WriterException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onErrorResponse(VolleyError error) {
        validar = 2;
        Toast.makeText(this, "Tenemos un problema con la BD \n Inténtalo más tarde",Toast.LENGTH_SHORT ).show();
    }

    @Override
    public void onResponse(JSONObject response) {

        user = new Users();
        validar = 1;
        JSONArray jsonArray = response.optJSONArray("datos");
        JSONObject jsonObject = null;
        try {
            jsonObject = jsonArray.getJSONObject(0);
            user.setModelo(jsonObject.optString("modelo"));
            user.setPlaca(jsonObject.optString("placa"));
            user.setColor(jsonObject.optString("color"));
            user.setNombre(jsonObject.optString("nombre"));
            user.setAp_pat(jsonObject.optString("apaterno"));

        } catch (JSONException e) {
            e.printStackTrace();
        }
        guardarPreferences();

    }

    public void guardarPreferences(){
        String name, apaterno, modelo, placa, color;
        user = new Users();

        name = user.getNombre();
        apaterno = user.getAp_pat();
        modelo = user.getModelo();
        placa = user.getPlaca();
        color = user.getColor();

        if(validar == 1){
            Context context = getApplicationContext();
            SharedPreferences sharedPreferences = getSharedPreferences("Login", getApplicationContext().MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString("nombre", name);
            editor.putString("ap_pat", apaterno);
            editor.putString("modelo", modelo);
            editor.putString("placa", placa);
            editor.putString("color", color);
            editor.commit();
            leerPreferences();
        }else if(validar == 2){
            Toast.makeText(getApplicationContext(), "Algo ocurrió mal \nInténtalo más tarde", Toast.LENGTH_SHORT).show();
        }
    }
}
