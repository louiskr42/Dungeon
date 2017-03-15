package com.coreelements.ce.dungeon;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class Room_3buero extends AppCompatActivity implements View.OnKeyListener {

    EditText et;
    TextView mainTV, ecoTV;
    String input;
    SharedPreferences sharedPref, levelPref, secretPref;
    SharedPreferences.Editor prefEditor, levelEditor, secretEditor;
    public Brain brain;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_main);

        et = (EditText)findViewById(R.id.editText);
        if (et != null) {
            et.setOnKeyListener(this);
        }

        mainTV = (TextView)findViewById(R.id.mainTextView);
        setMainTV(getString(R.string.room3buero_start));

        ecoTV = (TextView)findViewById(R.id.ecoTextView);

        sharedPref = this.getSharedPreferences("Time", MODE_PRIVATE);
        prefEditor = sharedPref.edit();

        levelPref = this.getSharedPreferences("Level", MODE_PRIVATE);
        levelEditor = levelPref.edit();

        secretPref = this.getSharedPreferences("Secret", MODE_PRIVATE);
        secretEditor = secretPref.edit();

        brain = new Brain();

        openSecretFloor();
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

    public void scanInput(String eingabe){
        if (eingabe.contains("uhr")){
            uhr(eingabe);
        }else if (eingabe.contains("fenster")){
            fenster(eingabe);
        }else if (eingabe.contains("tisch")){
            schreibTisch(eingabe);
        }else if (eingabe.contains("regal")){
            buecherRegal(eingabe);
        }else {
            roomActions(eingabe);
        }
    }

    public void openSecretFloor(){
        if (sharedPref.getString("time", "null").contentEquals("01:36")){
            secretEditor.putBoolean("secret", true);
            secretEditor.commit();
            setMainTV(getString(R.string.room3schlafzimmer_secret_open));
        }
    }

    public void roomActions(String eingabe){
        if (eingabe.contains("guck") || eingabe.contains("schau")){
            setMainTV(getString(R.string.room3buero_description));
        }else {
            actionFailed();
        }
    }

    public void fenster (String eingabe){
        if (eingabe.contains("öffn") || eingabe.contains("auf")){
            setMainTV(getString(R.string.room3buero_fenster_oeffnen));
        }else if (eingabe.contains("schlag") || eingabe.contains("kaput")|| eingabe.contains("zerst")){
            setMainTV(getString(R.string.room3buero_fenster_zerschlagen));
        }else {
            setMainTV(getString(R.string.room3buero_fenster_angucken));
        }
    }

    public void schreibTisch (String eingabe){
        if (eingabe.contains("such")){
            setMainTV(getString(R.string.room3buero_schreibtisch_durchsuchen));
        }else {
            setMainTV(getString(R.string.room3buero_schreibtisch_angucken));
        }
    }

    public void buecherRegal (String eingabe){
        if (eingabe.contains("buch") || eingabe.contains("bewe") || eingabe.contains("neh") || eingabe.contains("tre")){
            setMainTV(getString(R.string.room3buero_regal_action));
        }else {
            setMainTV(getString(R.string.room3buero_regal_angucken));
        }
    }

    public void uhr(String eingabe){
        if (eingabe.contains("stel") || eingabe.contains("nder")){
            nextLevel(SetTime.class);
        }else {
            String time1 = getString(R.string.time1);
            String time2 = getString(R.string.time2);
            String time = getString(R.string.room3buero_uhr_angucken1)+ " " + time1 + " " + sharedPref.getString("time", "00:00") + " " + time2 + " " + getString(R.string.room3buero_uhr_angucken2);
            setMainTV(time);
        }
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
