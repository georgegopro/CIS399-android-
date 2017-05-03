package com.example.george.piggy;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by George on 2016/7/6.
 */
public class SecondActivity extends AppCompatActivity{
    private String name1;
    private String name2;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        Bundle bundle = getIntent().getExtras();
        name1 = bundle.getString("name1");
        name2 = bundle.getString("name2");
        //setContentView(R.layout.second_activity);
        getFragmentManager().beginTransaction()
                .replace(android.R.id.content, new SecondFragment())
                .commit();
    }
    @Override
    protected void onNewIntent(Intent intent){
        setIntent(intent);
        super.onNewIntent(intent);
    }


    public String getName1(){
        return name1;
    }
    public String getName2(){
        return name2;
    }

}
