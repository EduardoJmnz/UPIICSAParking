package com.example.eddyh.upiicsaparking;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import java.lang.reflect.Array;
import java.net.DatagramSocket;
import java.util.ArrayList;

public class RecyclerAdapted extends RecyclerView.Adapter<RecyclerAdapted.MyViewHolder> {

    private ArrayList<Menu>listaMenu;
    private    OnclickRecycler listener;

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View viewm = LayoutInflater.from(parent.getContext()).inflate(R.layout.adaptador, parent, false);
        MyViewHolder viewHolder = new MyViewHolder(viewm);

        return viewHolder;
    }


    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Menu menu = listaMenu.get(position);
        holder.bind(menu, listener);
    }

    @Override
    public int getItemCount() {
        return listaMenu.size();
    }


    public interface OnclickRecycler{
        void OnclickItemRecycler(Menu menu);
    }
    public RecyclerAdapted(ArrayList<Menu> listaMenu, OnclickRecycler listener){
        this.listaMenu = listaMenu;
        this.listener = listener;
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{

        ImageView imageView;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = (ImageView)itemView.findViewById(R.id.imagengaleria);
        }

        public void bind(final Menu menu, final OnclickRecycler listener){
            Glide.with(imageView.getContext()).load(menu.getIdImagen()).into(imageView);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    listener.OnclickItemRecycler(menu);
                }
            });
        }
    }
}
