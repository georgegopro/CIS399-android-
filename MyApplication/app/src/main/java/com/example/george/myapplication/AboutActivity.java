package com.example.george.myapplication;

import android.app.Activity;
import android.os.Bundle;


public class AboutActivity extends Activity{
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        getFragmentManager().beginTransaction()
                .replace(android.R.id.content, new AboutFragment())
                .commit();
    }
}
