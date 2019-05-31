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

public class MostrarQr extends AppCompatActivity implements Response.Listener<JSONObject>, Response.ErrorListener {

    ImageView image;
    String URL = "https://upiiparking.000webhostapp.com/";
    RequestQueue rq;
    JsonRequest jr;
    Context context;
    int validar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mostrar_qr);
        rq = Volley.newRequestQueue(this);
        iniciarSesion();
    }

    public void iniciarSesion() {
    context = getApplicationContext();
        SharedPreferences sharedPreferences = getSharedPreferences("Login", context.MODE_PRIVATE);
        String boleta = sharedPreferences.getString("boleta", "No hay dato boleta");
        String sum = "generarQR.php?boleta=" + boleta;
        String urlComp = URL + sum;
        jr = new JsonObjectRequest(Request.Method.GET, urlComp, null, this, this);
        rq.add(jr);
    }

    public void leerPreferences() {
        SharedPreferences sharedPreferences = getSharedPreferences("Login", getApplicationContext().MODE_PRIVATE);
        String boleta = sharedPreferences.getString("boleta", "No hay dato boleta");
        String nombre = sharedPreferences.getString("nombre", "No hay dato nombre");
        String modelo = sharedPreferences.getString("modelo", "No hay dato modelo");
        String color = sharedPreferences.getString("color", "No hay dato color");
        String placa = sharedPreferences.getString("placas", "No hay dato placa");
    }

    public void mostrarPark(View view) {
        Intent intent = new Intent(this, Principal.class);
        startActivity(intent);
    }

    public void generarQR(String nombre, String apellido, String modelo, String placas, String color) {

        String codigo = nombre+"\n"+apellido+"\n"+modelo+"\n"+placas+"\n"+color;

        //GENERAMOS EL QR CON LOS DATOS OBTENIDOS
        image = (ImageView) findViewById(R.id.image);
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
        Toast.makeText(this, error.toString(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onResponse(JSONObject response) {
        JSONArray jsonArray = response.optJSONArray("datos");
        String nombre = null, apellido= null, modelo=null, placas=null, color=null;
        try{
            for(int i = 0; i < jsonArray.length(); i++){
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                  nombre = jsonObject.getString("nombre");
                  apellido = jsonObject.getString("ap_pat");
                  modelo = jsonObject.getString("modelo");
                  placas = jsonObject.getString("placas");
                 color = jsonObject.getString("color");
            }
            guardarPreferences(nombre, apellido, modelo, placas, color);
            generarQR(nombre, apellido, modelo, placas, color);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private void guardarPreferences(String nombre, String apellido, String modelo, String placas, String color) {
        Context context = getApplicationContext();
        SharedPreferences sharedPreferences = getSharedPreferences("Login", context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("nombre", nombre);
        editor.putString("apellido", apellido);
        editor.putString("modelo", modelo);
        editor.putString("placas", placas);
        editor.putString("color", color);
        editor.commit();
    }

}