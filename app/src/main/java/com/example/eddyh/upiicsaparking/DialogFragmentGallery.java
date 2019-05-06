package com.example.eddyh.upiicsaparking;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;


public class DialogFragmentGallery extends DialogFragment {

    View view;
    TextView titulo, capacidad;
    RecyclerView galeria;
    ImageView imagen;
    ArrayList<Menu> listaMenu;
    RecyclerAdapted adapted;
    Button mostrar;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_dialog_fragment_gallery, container, false);
        inicializarComponentes();

        listaMenu = new Menu().listaMenu();
        adapted = new RecyclerAdapted(listaMenu, new RecyclerAdapted.OnclickRecycler() {

            public void OnclickItemRecycler(Menu menu) {
                Glide.with(getContext()).load(menu.getIdImagen()).into(imagen);
                titulo.setText(menu.getTitulo());
                capacidad.setText(menu.getcapacidad());
            }
        });

        galeria.setAdapter(adapted);
        return view;
    }

    public void inicializarComponentes(){
        titulo = (TextView)view.findViewById(R.id.titulo);
        imagen = (ImageView)view.findViewById(R.id.imagen);
        galeria = (RecyclerView)view.findViewById(R.id.galeria);
        capacidad = (TextView)view.findViewById(R.id.capacidad);
        mostrar = (Button) view.findViewById(R.id.mostrarQR);
        mostrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), qr_reader.class);
                startActivity(intent);
            }
        });
        galeria.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL,false));
    }




}
