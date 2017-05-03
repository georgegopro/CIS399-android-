package com.example.george.piggy;

import android.os.Bundle;
import android.app.Activity;

/**
 * Created by George on 2016/7/3.
 */
public class AboutActivity extends Activity{
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        getFragmentManager().beginTransaction()
                .replace(android.R.id.content, new AboutFragment())
                .commit();
    }
}
