package com.example.eddyh.upiicsaparking;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText et1, et2;
    private Cursor fila;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        et1 = (EditText)findViewById(R.id.user);
        et2 = (EditText)findViewById(R.id.password);
    }

    public void ingresar(View v){
        DBHelper admin=new DBHelper(this, "instituto",null, 1);
        SQLiteDatabase db=admin.getWritableDatabase();

        String user=et1.getText().toString();
        String password=et2.getText().toString();
        fila=db.rawQuery("select usuario, password from usuarios where usuario='"+user+"' and password= '"+password+"'", null);

        //mientras fila tiene algun valor

        if(fila.moveToFirst())
        {
            String usuario=fila.getString(0);
            String contra=fila.getString(1);

            if(user.equals(usuario)&&password.equals(contra))
            {
                Intent ven = new Intent(this, Home.class);
                startActivity(ven);
                et1.setText("");
                et2.setText("");
                Toast.makeText(this, "Bienvenido", Toast.LENGTH_SHORT).show();

            }

        }else if(user.isEmpty() || password.isEmpty()){
            Toast.makeText(getApplicationContext(), "Ingresa Tus Datos", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(getApplicationContext(), "Usuario/Contraseña Incorrecto", Toast.LENGTH_SHORT).show();
        }

    }

    public void registrarnuevo(View view){
        Intent ven = new Intent(this, Registro.class);
        startActivity(ven);

    }



}
