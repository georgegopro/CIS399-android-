package com.example.george.piggy;

import android.app.Activity;
import android.app.FragmentManager;
import android.os.Bundle;

/**
 * Created by George on 2016/7/3.
 */
public class SettingsActivity extends Activity{
    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);

        //Set the view for the activity using xml
        setContentView(R.layout.setting_activity);
    }
}
