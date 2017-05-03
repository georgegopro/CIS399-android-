package com.example.george.piggy;

import android.content.SharedPreferences;
import android.content.SharedPreferences.OnSharedPreferenceChangeListener;
import android.os.Bundle;
import android.preference.PreferenceFragment;
import android.preference.PreferenceManager;

/**
 * Created by George on 2016/7/3.
 */
public class SettingsFragment extends PreferenceFragment implements SharedPreferences.OnSharedPreferenceChangeListener{
    private SharedPreferences savedValues;
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.preferences);
        savedValues = PreferenceManager.getDefaultSharedPreferences(getActivity());
    }
    @Override
    public void onResume(){
        super.onResume();
        savedValues.registerOnSharedPreferenceChangeListener(this);
    }
    @Override
    public void onPause(){
        savedValues.unregisterOnSharedPreferenceChangeListener(this);
        super.onPause();
    }

    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String s) {
        SecondFragment secondFragment=
                (SecondFragment) getFragmentManager().findFragmentById(R.id.second_fragment);
        if(secondFragment!=null){
            secondFragment.onResume();
        }


    }
}



