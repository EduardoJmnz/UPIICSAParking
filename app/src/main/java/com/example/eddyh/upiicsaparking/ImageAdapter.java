package com.example.eddyh.upiicsaparking;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

public class ImageAdapter extends FragmentStatePagerAdapter {
    int [] imagenes = new int[]{R.drawable.estacionamiento1 ,R.drawable.estacionamiento2};

    public ImageAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int i) {
        Imageslider fragment = new Imageslider();
        Bundle bundle = new Bundle();
        bundle.putInt("source", imagenes[i]);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public int getCount() {
        return imagenes.length;
    }
}
