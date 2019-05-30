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
    Users user;
    String URL = "https://upiicsapark.xyz/";
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

    public void generarQR() {
        user = new Users();

        String codigo = user.getBoleta() + "\n" + user.getNombre() + "\n" + user.getModelo() + "\n" + user.getColor() + "\n" + user.getPlaca()+"\n"+user.getFolio();
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

    }

    @Override
    public void onResponse(JSONObject response) {
        user = new Users();
        JSONArray jsonArray = response.optJSONArray("datos");
        JSONObject jsonObject =null;
        try {
            jsonObject = jsonArray.getJSONObject(0);
            user.setBoleta(jsonObject.optString("boleta"));
            user.setNombre(jsonObject.optString("nombre"));
            user.setModelo(jsonObject.optString("modelo"));
            user.setPlaca(jsonObject.optString("placas"));
            user.setColor(jsonObject.optString("color"));
            user.setFolio(jsonObject.optString("folio"));
            generarQR();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }


}