package com.example.eddyh.upiicsaparking;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


public class Imagenes extends Fragment {
    ViewPager mPager;
    ImageAdapter adapter;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mPager = getView().findViewById(R.id.pager);
        adapter = new ImageAdapter(getFragmentManager());
        mPager.setAdapter(adapter);
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_imagenes, container, false);
    }
}
