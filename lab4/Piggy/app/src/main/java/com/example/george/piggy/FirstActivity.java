package com.example.george.piggy;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by George on 2016/7/6.
 */
public class FirstActivity extends AppCompatActivity{

    private SecondFragment playGame;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.first_activity);
    }
}
