package com.coreelements.ce.dungeon;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class Room_2 extends AppCompatActivity implements View.OnKeyListener {

    EditText    et;
    TextView    mainTV, ecoTV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_main);

        et = (EditText)findViewById(R.id.editText);
        et.setOnKeyListener(this);

        mainTV = (TextView)findViewById(R.id.mainTextView);
        setMainTV(getString(R.string.room2_start));

        ecoTV = (TextView)findViewById(R.id.ecoTextView);
    }


    public boolean onKey(View v, int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_ENTER) {
            if (event.getAction() == KeyEvent.ACTION_UP) {
                onEnterPressed();
            }
        }
        return false;
    }

    public void setMainTV(String text){
        mainTV.setText(text);
    }

    public void onEnterPressed(){
        String eingabe = et.getText().toString().replace("A", "a").replace("B", "b").replace("C", "c").replace("D", "d")
                .replace("E", "e").replace("F", "f").replace("G", "g").replace("H", "h").replace("I", "i").replace("J", "j")
                .replace("K", "k").replace("L", "l").replace("M", "m").replace("N", "n").replace("O", "o").replace("P", "p")
                .replace("Q", "q").replace("R", "r").replace("S", "s").replace("T", "t").replace("U", "u").replace("V", "v")
                .replace("W", "w").replace("X", "x").replace("Y", "y").replace("Z", "z").replace("Ä", "ä").replace("Ö", "ö")
                .replace("Ü", "ü");
        if (eingabe.contains("hal")|| eingabe.contains("ruf")||eingabe.contains("sag")){
            setMainTV(getString(R.string.room1_hallo));
        }else if (eingabe.contains("tret")){
            setMainTV(getString(R.string.room2_treten));
        }else if (eingabe.contains("türen")){
            setMainTV(getString(R.string.room2_tueren));
        }else if (eingabe.contains("tür")){
            setMainTV(getString(R.string.room2_tueren));
        }else if (eingabe.contains("büro")){
            nextLevel(Room_3buero.class);
        }else if (eingabe.contains("schlaf") && eingabe.contains("immer")){
            nextLevel(Room_3schlafzimmer.class);
        }else if (eingabe.contains("ver") && eingabe.contains("immer")){
            setMainTV(getString(R.string.room2_vergnuegungszimmer));
        }else if (eingabe.contains("tanz")){
            setMainTV(getString(R.string.room1_tanz));
        }else {
            setMainTV(getString(R.string.action_invalid));
        }
        et.setText(null);
    }

    public void nextLevel(Class level){
        startActivity(new Intent(this.getApplicationContext(), level));
        finish();
    }
}
