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

public class Registro extends AppCompatActivity {
    EditText et1, et2, et3, et4;
    Button b1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);

        et1 = (EditText)findViewById(R.id.user);
        et2 = (EditText)findViewById(R.id.password);
        et3 = (EditText)findViewById(R.id.txtId);
        et4 = (EditText)findViewById(R.id.txtConfirmar);
        b1 = (Button)findViewById(R.id.btnRegistrar);

    }

    public void registrar(View v) {
        DBHelper admin = new DBHelper(this, "instituto", null, 1);
        SQLiteDatabase db = admin.getWritableDatabase();

        String usuario = et1.getText().toString();
        String password = et2.getText().toString();
        String codigo = et3.getText().toString();
        String cpassword = et4.getText().toString();


        if (usuario.isEmpty() || codigo.isEmpty() || password.isEmpty()) {

            Toast.makeText(this, "Ingresa Tus Datos", Toast.LENGTH_SHORT).show();
        }
        else if(password.equals(cpassword))
        {
            ContentValues values = new ContentValues();
            values.put("codigo", codigo);
            values.put("usuario", usuario);
            values.put("password", password);

            db.insert("usuarios", null, values);
            db.close();

            Toast.makeText(this, "Registro Exitoso", Toast.LENGTH_SHORT).show();

            Intent ven = new Intent(this, MainActivity.class);
            startActivity(ven);
        }
        else
        {
            Toast.makeText(this, "La Contraseña No Coincide", Toast.LENGTH_SHORT).show();
        }
    }

    public void cancelar(View view){
        finish();
    }


}
