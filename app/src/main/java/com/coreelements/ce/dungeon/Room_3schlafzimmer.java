package com.coreelements.ce.dungeon;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class Room_3schlafzimmer extends AppCompatActivity implements View.OnKeyListener {

    EditText et;
    TextView mainTV, ecoTV;
    String input;
    SharedPreferences sharedPref, levelPref;
    SharedPreferences.Editor prefEditor, levelEditor;
    public Brain brain;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_main);

        et = (EditText)findViewById(R.id.editText);
        et.setOnKeyListener(this);

        mainTV = (TextView)findViewById(R.id.mainTextView);
        setMainTV(getString(R.string.room3schlafzimmer_start));

        ecoTV = (TextView)findViewById(R.id.ecoTextView);

        sharedPref = this.getSharedPreferences("Time", MODE_PRIVATE);
        prefEditor = sharedPref.edit();

        levelPref = this.getSharedPreferences("Level", MODE_PRIVATE);
        levelEditor = levelPref.edit();

        brain = new Brain();
    }

    public boolean onKey(View v, int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_ENTER) {
            if (event.getAction() == KeyEvent.ACTION_UP) {
                input = et.getText().toString().replace("A", "a").replace("B", "b").replace("C", "c").replace("D", "d")
                        .replace("E", "e").replace("F", "f").replace("G", "g").replace("H", "h").replace("I", "i").replace("J", "j")
                        .replace("K", "k").replace("L", "l").replace("M", "m").replace("N", "n").replace("O", "o").replace("P", "p")
                        .replace("Q", "q").replace("R", "r").replace("S", "s").replace("T", "t").replace("U", "u").replace("V", "v")
                        .replace("W", "w").replace("X", "x").replace("Y", "y").replace("Z", "z").replace("Ä", "ä").replace("Ö", "ö")
                        .replace("Ü", "ü");
                scanInput(input);
            }
        }
        return false;
    }

    public void  scanInput(String eingabe){
        if (eingabe.contains("bet")){
            bett(eingabe);
        }else if (eingabe.contains("tisch")){
            nachtTisch(eingabe);
        }else if (eingabe.contains("lampe")){
            lampe(eingabe);
        }else if (eingabe.contains("fenster")){
            fenster(eingabe);
        }else if (eingabe.contains("notiz") || eingabe.contains("seit") || eingabe.contains("papi") || eingabe.contains("blatt")){
            notiz();
        }else {
            roomActions(eingabe);
        }
    }

    public void roomActions(String eingabe){
        if (eingabe.contains("guck") || eingabe.contains("schau")){
            setMainTV(getString(R.string.room3schlafzimmer_description));
        }else {
            actionFailed();
        }
    }

    public void bett(String eingabe){
        if (eingabe.contains("leg") || eingabe.contains("setz") || eingabe.contains("sitz") || eingabe.contains("ruh")){
            setMainTV(getString(R.string.room3schlafzimmer_bett_hinlegen));
        }else {
            setMainTV(getString(R.string.room3schlafzimmer_bett_angucken));
        }
    }

    public void nachtTisch(String eingabe){
        if (eingabe.contains("durch") || eingabe.contains("such") || eingabe.contains("öff") || eingabe.contains("oeff") || eingabe.contains("schub") || eingabe.contains("auf")){
            setMainTV(getString(R.string.room3schlafzimmer_nachttisch_durchsuchen));
        }else {
            setMainTV(getString(R.string.room3schlafzimmer_nachttisch_angucken));
        }
    }

    public void lampe(String eingabe){
        if (eingabe.contains("aus")){
            setMainTV(getString(R.string.room3schlafzimmer_lampe_ausschalten));
        }else if (eingabe.contains("ein")){
            setMainTV(getString(R.string.room3schlafzimmer_lampe_einschalten));
        }else {
            setMainTV(getString(R.string.room3schlafzimmer_lampe_angucken));
        }
    }

    public void fenster(String eingabe){
        if (eingabe.contains("auf") || eingabe.contains("schl") || eingabe.contains("öff") || eingabe.contains("auf")){
            setMainTV(getString(R.string.room3schlafzimmer_fenster_oeffnen));
        }else if (eingabe.contains("zer") || eingabe.contains("schl") || eingabe.contains("brech") || eingabe.contains("spri") || eingabe.contains("kap")){
            setMainTV(getString(R.string.room3schlafzimmer_fenster_zerschlagen));
        }else {
            setMainTV(getString(R.string.room3schlafzimmer_fenster_angucken));
        }
    }

    public void notiz(){
        setMainTV(getString(R.string.room3schlafzimmer_notiz_lesen));
    }

    public void setMainTV(String text){
        mainTV.setText(text);
    }

    public void actionFailed(){
        setMainTV(getString(R.string.action_invalid));
    }

    public void nextLevel(Class level){
        startActivity(new Intent(this.getApplicationContext(), level));
        finish();
    }

    @Override
    public void onPause(){
        super.onPause();
        levelEditor.putString("level", "3b");
        levelEditor.commit();
    }

}
