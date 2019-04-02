package com.example.eddyh.upiicsaparking;

import java.util.ArrayList;

public class Menu {
    private int idImagen;
    private String titulo;
    private String capacidad;

    public Menu(){
        idImagen = 0;
        titulo = "";
        capacidad = "";
    }
    public Menu(int idImagen, String titulo, String capacidad){
        this.idImagen = idImagen;
        this.titulo = titulo;
        this.capacidad = capacidad;
    }

    public int getIdImagen() {
        return idImagen;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getcapacidad(){return capacidad;}

    public ArrayList<Menu> listaMenu(){


        Menu menu;
        ArrayList<Menu> lista = new ArrayList<Menu>();
        Integer[] idImagenes = new Integer[]{R.drawable.estacionamiento1, R.drawable.estacionamiento2};
        String[] titulos = new String[]{"Estacionamiento de Ingenierias", "Estacionamiento de Pesados"};
        String[] capacidad = new String[]{"Capacidad total: 130 lugares","Capacidad total: 80 lugares"};

        for(int i= 0; i<idImagenes.length;i++){
            menu = new Menu(idImagenes[i], titulos[i], capacidad[i]);
            lista.add(menu);
        }

        return lista;
    }
}
