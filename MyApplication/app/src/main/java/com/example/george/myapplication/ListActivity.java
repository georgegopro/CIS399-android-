package com.example.george.myapplication;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ListActivity extends Activity{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_look);
        List<Map<String,Object>> _listmap = new ArrayList<Map<String,Object>>();
        Log.d("pokerShow",String.format("Num:[%d]", PokerShow.m_pokerImages.length));
        for(int i = 0; i < PokerShow.m_pokerImages.length ; i++)
        {
            Map<String,Object> _item = new HashMap<String, Object>();
            _item.put("Image", PokerShow.m_pokerImages[i]);
            _item.put("Name", PokerShow.m_pokerNames[i]);
            Log.d("pokerShow",String.format("Name:[%d][%s]",i, PokerShow.m_pokerNames[i]));
            _listmap.add(_item);
        }
        SimpleAdapter _sa = new SimpleAdapter(this,_listmap,
                R.layout.item_ui,
                new String[]{"Image","Name"},
                new int[]{R.id.list_item_image,R.id.list_item_text});
        android.widget.ListView list = (android.widget.ListView)findViewById(R.id.pokerlistview);
        list.setAdapter(_sa);
    }
}
