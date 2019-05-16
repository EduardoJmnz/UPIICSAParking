package com.example.eddyh.upiicsaparking;

import android.content.Context;
import android.graphics.Paint;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;


public class Imageslider extends Fragment {

    ImageView imagen;
    TextView titulos;

    public Imageslider() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_imageslider, container, false);
        imagen = view.findViewById(R.id.Image);
        Picasso.with(getContext()).load(getArguments().getInt("source")).into(imagen);
        return  view;
    }

}
