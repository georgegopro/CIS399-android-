package com.example.george.myapplication;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class StackA extends Activity{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.stack_look);
        List<Map<String, Object>> _listmap = new ArrayList<Map<String, Object>>();
        for (int i = 0; i < PokerShow.m_pokerImages.length; i++) {
            Map<String, Object> _item = new HashMap<String, Object>();
            _item.put("Image", PokerShow.m_pokerImages[i]);
            _listmap.add(_item);
        }
        SimpleAdapter _sa = new SimpleAdapter(this, _listmap,
                R.layout.item_ui,
                new String[]{"Image"},
                new int[]{R.id.list_item_image});
        final android.widget.StackView _sv = (android.widget.StackView)findViewById(R.id.stack_view);
        _sv.setAdapter(_sa);
        Button _up = (Button)findViewById(R.id.stack_up);
        Button _down = (Button)findViewById(R.id.stack_down);
        _up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                _sv.showPrevious();
            }
        });
        _down.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                _sv.showNext();
            }
        });
    }

}
