package com.example.george.piggy;

import android.app.Fragment;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.HashMap;
import java.util.Random;

/**
 * Created by George on 2016/7/6.
 */
public class SecondFragment extends Fragment implements View.OnClickListener{
    private final int goal_100 = 100;
    private boolean isPlayer1 = true;
    private boolean finishGame = false;
    private String who= "Player1's turn";
    private String imageName = "die";
    private String name1 = "";
    private String name2 = "";
    private int player1Score = 0;
    private int player2Score = 0;
    private int turnScore = 0;

    private TextView t1;
    private TextView t2;
    private TextView player;
    private TextView turnPoint;
    private TextView n1;
    private TextView n2;
    private Button roll;
    private Button holl;
    private Button newg;
    private ImageView imageView;
    private RelativeLayout bg;

    private HashMap<String, Drawable> drawableMap = new HashMap<String, Drawable>();
    private Random random;

    private boolean useBackground = false;
    private SharedPreferences savedValues;
    private int goal = goal_100;


    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        PreferenceManager.setDefaultValues(getActivity(), R.xml.preferences, false);
        savedValues = PreferenceManager.getDefaultSharedPreferences(getActivity());
        setHasOptionsMenu(true);
    }


    @Override
    public View onCreateView(LayoutInflater inflate, ViewGroup container, Bundle savedInstanceState) {

        View view = inflate.inflate(R.layout.second_fragment,container,false);
        t1 = (TextView) view.findViewById(R.id.t1);
        t2 = (TextView) view.findViewById(R.id.t2);
        n1 = (TextView) view.findViewById(R.id.n1);
        n2 = (TextView) view.findViewById(R.id.n2);
        player = (TextView) view.findViewById(R.id.player);
        turnPoint = (TextView) view.findViewById(R.id.turnPoint);
        roll = (Button) view.findViewById(R.id.roll);
        holl = (Button) view.findViewById(R.id.holl);
        newg = (Button) view.findViewById(R.id.newg);
        bg = (RelativeLayout) view.findViewById(R.id.bg);
        imageView = (ImageView) view.findViewById(R.id.imageView);

        drawableMap.put("die", getResources().getDrawable(R.drawable.die1));
        drawableMap.put("die1", getResources().getDrawable(R.drawable.die1));
        drawableMap.put("die2", getResources().getDrawable(R.drawable.die2));
        drawableMap.put("die3", getResources().getDrawable(R.drawable.die3));
        drawableMap.put("die4", getResources().getDrawable(R.drawable.die4));
        drawableMap.put("die5", getResources().getDrawable(R.drawable.die5));
        drawableMap.put("die6", getResources().getDrawable(R.drawable.die6));
        random = new Random();


        roll.setOnClickListener(this);
        holl.setOnClickListener(this);
        newg.setOnClickListener(this);
        SecondActivity sA = (SecondActivity)getActivity();
        name1 = sA.getName1();
        name2 = sA.getName2();
        n1.setText(name1);
        n2.setText(name2);
        return view;
    }



    private void setPlayer1Score(final int newScore) {
        player1Score = newScore;
        t1.setText(String.valueOf(newScore));
    }
    private void setPlayer2Score(final int newScore) {
        player2Score = newScore;
        t2.setText(String.valueOf(newScore));
    }
    private void setPlayer(final String newPlayer) {
        who = newPlayer;
        player.setText(newPlayer);
    }
    private void setTurnTotal(final int newTotal) {
        turnScore = newTotal;
        turnPoint.setText(String.valueOf(newTotal));
    }
    private void setImage(final String newImage){
        imageName = newImage;
        imageView.setImageDrawable(drawableMap.get(imageName));
    }
    private void changePlayer(){
        if (isPlayer1) {
            isPlayer1 = !isPlayer1;
            setPlayer(name2+"'s turn");
        }else if(!isPlayer1) {
            isPlayer1 = true;
            setPlayer(name1+"'s turn");
        }
    }
    private void roll(){
        int roll = random.nextInt(6)+1;
        setImage("die"+roll);
        if (roll ==1){
            setTurnTotal(0);
            changePlayer();
        }
        else{
            setTurnTotal(turnScore + roll);
        }
    }

    private void holl(){
        if (isPlayer1) {
            setPlayer1Score(player1Score + turnScore);
        } else {
            setPlayer2Score(player2Score + turnScore);
        }
        setTurnTotal(0);
        if (player1Score >= goal ||player2Score >= goal){
            endGame();
        } else{
            changePlayer();
        }
    }

    public void onPause(){
        SharedPreferences.Editor editor = savedValues.edit();
        editor.putInt("player1Score",player1Score);
        editor.putInt("player2Score",player2Score);
        editor.putInt("turnScore",turnScore);
        editor.putBoolean("isPlayer1", isPlayer1);
        editor.putBoolean("finishGame", finishGame);
        editor.putString("who",who);
        editor.putString("imageName", imageName);
        editor.putString("name1", name1);
        editor.putString("name2", name2);
        super.onPause();
    }
    public void onResume(){
        super.onResume();
        String g = savedValues.getString("savedValues_goal","");
        try{
            goal = Integer.parseInt(g);
        }catch(Exception e){
        }

        String size = savedValues.getString("savedValues_size","");
        setTextSize(size);


        useBackground =savedValues.getBoolean("use_background",false);
        RelativeLayout color_background = bg;
        String color_key =savedValues.getString("pref_color","red");
        if(color_key.equals("red")){
            color_background.setBackgroundColor(Color.RED);
        }
        else if(color_key.equals("blue")){
            color_background.setBackgroundColor(Color.BLUE);
        }
        else{
            color_background.setBackgroundColor(Color.YELLOW);
        }


        setPlayer1Score(savedValues.getInt("player1Score",player1Score));
        setPlayer2Score(savedValues.getInt("player2Score",player2Score));
        setTurnTotal(savedValues.getInt("turnScore",turnScore));
        isPlayer1 = savedValues.getBoolean("isPlayer1",isPlayer1);
        finishGame = savedValues.getBoolean("finishGame",finishGame);
        setPlayer(savedValues.getString("who",who));
        setImage(savedValues.getString("imageName",imageName));
    }

    private void setTextSize(String size){
        if(size.equals("20")){
            t1.setTextSize(20);
            t2.setTextSize(20);
            n1.setTextSize(20);
            n2.setTextSize(20);
            player.setTextSize(20);
            turnPoint.setTextSize(20);
        }
        if(size.equals("25")){
            t1.setTextSize(25);
            t2.setTextSize(25);
            n1.setTextSize(25);
            n2.setTextSize(25);
            player.setTextSize(25);
            turnPoint.setTextSize(25);

        }
        if(size.equals("30")){
            t1.setTextSize(30);
            t2.setTextSize(30);
            n1.setTextSize(30);
            n2.setTextSize(30);
            player.setTextSize(30);
            turnPoint.setTextSize(30);
        }
        else{

        }
    }

    private void newg(){
        setPlayer1Score(0);
        setPlayer2Score(0);
        setPlayer("Player1's turn");
        setTurnTotal(0);
        setImage("die");
        isPlayer1=true;
        finishGame = false;
    }

    private void endGame(){
        if(player2Score >=goal){
            if(player1Score > player2Score){
                setPlayer(name1+"'s win");
                finishGame = true;
            }
            if(player2Score > player1Score){
                setPlayer(name2+"'s win");
                finishGame = true;
            }
            if(player2Score == player1Score){
                setPlayer("Both players win");
                finishGame = true;
            }
        }
        if (player1Score >=goal){
            if(isPlayer1){
                changePlayer();
            }else {
                setPlayer(name1 + "'s win");
                finishGame = true;
            }
        }
    }

    @Override
    public void onClick(View view) {
        if(name1 == null ||name2 ==null){
            setPlayer("Enter the name");
        }else {
            switch (view.getId()) {
                case R.id.roll:
                    if (finishGame == true) {
                        setPlayer("clik New game");
                    } else
                        roll();
                    break;
            }
            switch (view.getId()) {
                case R.id.holl:
                    if (finishGame == true) {
                        setPlayer("clik New game");
                    } else
                        holl();
                    break;
            }
            switch (view.getId()) {
                case R.id.newg:
                    newg();
                    break;
            }
        }
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        // attempt to get an instance of the SettingsFragment object
        SettingsFragment settingsFragment = (SettingsFragment) getFragmentManager()
                .findFragmentById(R.id.settings_fragment);

        // display the appropriate menu
        if (settingsFragment == null) {
            // SettingsFragment is not in the layout
            inflater.inflate(R.menu.second_fragment, menu);
        } else {
            // SettingsFragment is in the layout
            inflater.inflate(R.menu.first_activity, menu);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_settings:
                startActivity(new Intent(getActivity(), SettingsActivity.class));
                return true;
            case R.id.menu_about:
                startActivity(new Intent(getActivity(), AboutActivity.class));
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
