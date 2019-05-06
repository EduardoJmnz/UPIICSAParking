package com.example.eddyh.upiicsaparking;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Handler;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;



import static android.Manifest.permission.CAMERA;
import static android.Manifest.permission.WRITE_EXTERNAL_STORAGE;

public class Splash extends AppCompatActivity {


    private  final int REQUEST_CODE_ASK_PERMISSION = 100;
    private int avanzar = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        solicitarPermisos();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if(avanzar == 1){
                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                    startActivity(intent);
                }else{
                    finish();
                }
            }
        },4000);
    }

    private void solicitarPermisos(){
        int permisosCamera = ActivityCompat.checkSelfPermission(this, CAMERA);
        int permisosStorage = ActivityCompat.checkSelfPermission(this, WRITE_EXTERNAL_STORAGE);
        if(permisosCamera!= PackageManager.PERMISSION_GRANTED || permisosStorage != PackageManager.PERMISSION_GRANTED){
            if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
                requestPermissions(new String[]{WRITE_EXTERNAL_STORAGE, CAMERA}, REQUEST_CODE_ASK_PERMISSION);
                avanzar = 1;
            }

        }else{

        }

    }








}
