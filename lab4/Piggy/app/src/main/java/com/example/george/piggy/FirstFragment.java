package com.example.george.piggy;

import android.app.Fragment;
import android.content.Intent;
import android.view.View.OnClickListener;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Created by George on 2016/7/6.
 */
public class FirstFragment extends Fragment implements OnClickListener{
    private String name1 = null;
    private String name2 = null;
    private SecondFragment playGame;
    private boolean twoPaneLayout;

    private EditText n1;
    private EditText n2;

    private FirstActivity activity;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.first_fragment, container, false);
        Button button = (Button)view.findViewById(R.id.play);
        button.setOnClickListener(this);
        return view;
    }

    public void onActivityCreated(Bundle savedInstanceState){
        super.onActivityCreated(savedInstanceState);

        activity = (FirstActivity)getActivity();
        n1 = (EditText) activity.findViewById(R.id.n1);
        n2 = (EditText) activity.findViewById(R.id.n2);

        if(savedInstanceState!=null){
            name1 = n1.getText().toString();
            name2 = n2.getText().toString();
            //playGame = new SecondFragment().setname1(name1);
            //playGame = new SecondFragment().setname2(name2);
        }else{
            playGame = new SecondFragment();
        }
        this.playGame = playGame;

        //twoPaneLayout = activity.findViewById(R.id.second_fragment)! = null;
    }



    @Override
    public void onClick(View view) {
        name1 = n1.getText().toString();
        name2 = n2.getText().toString();
        if(name1.isEmpty() ||name2.isEmpty()) {
            Toast.makeText(getActivity(),"Please enter both player name",Toast.LENGTH_SHORT).show();
        }else{
            if (view.getId() == R.id.play) {
                Intent intent = new Intent(getActivity(),SecondActivity.class);
                intent.putExtra("name1",name1);
                intent.putExtra("name2",name2);
                startActivity(intent);
            }
        }
    }

}
