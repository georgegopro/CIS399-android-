package com.example.george.myapplication;

import android.app.ActionBar;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterViewFlipper;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;


public class AnimationActivity extends Activity{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.animation_show);
        final AdapterViewFlipper _avf = (AdapterViewFlipper)findViewById(R.id.flipper_view);
        BaseAdapter _ba = new BaseAdapter() {
            @Override
            public int getCount() {
                return PokerShow.m_pokerImages.length;
            }

            @Override
            public Object getItem(int position) {
                return PokerShow.m_pokerImages[position];
            }

            @Override
            public long getItemId(int position) {
                return position;
            }

            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                ImageView _iv = new ImageView(AnimationActivity.this);
                _iv.setImageResource(PokerShow.m_pokerImages[position]);
                _iv.setScaleType(ImageView.ScaleType.FIT_XY);
                _iv.setLayoutParams(new ActionBar.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
                return _iv;
            }
        };
        _avf.setAdapter(_ba);
        final Button _pre = (Button)findViewById(R.id.flipper_pre);
        final Button _next = (Button)findViewById(R.id.flipper_next);
        final Button _auto = (Button)findViewById(R.id.flipper_auto);


        _pre.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                _avf.showPrevious();
                if(_auto.getText().equals("Stop Show")) {
                    _auto.setText("Begin Show");
                    _avf.stopFlipping();
                }
            }
        });
        _next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                _avf.showNext();
                if(_auto.getText().equals("Stop Show")) {
                    _auto.setText("Begin Show");
                    _avf.stopFlipping();
                }
            }
        });
        _auto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(_auto.getText().equals("Stop Show")) {
                    _auto.setText("Begin Show");
                    _avf.stopFlipping();
                }else
                {
                    _auto.setText("Stop Show");
                    _avf.startFlipping();
                }
            }
        });
    }
}
