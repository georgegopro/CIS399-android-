package com.example.george.myapplication;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


public class AboutFragment extends Fragment{
    public View onCreateView (LayoutInflater inflater, ViewGroup container,
                              Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.activity_about, container, false);
        return view;
    }
}
