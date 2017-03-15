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

public class SetTime extends AppCompatActivity implements View.OnKeyListener {

    EditText et;
    TextView mainTV, ecoTV;
    String input;
    SharedPreferences sharedPref;
    SharedPreferences.Editor prefEditor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_main);

        et = (EditText)findViewById(R.id.editText);
        et.setOnKeyListener(this);

        mainTV = (TextView)findViewById(R.id.mainTextView);
        setMainTV(getString(R.string.room3buero_uhr_zeitumstellen));

        ecoTV = (TextView)findViewById(R.id.ecoTextView);

        sharedPref = this.getSharedPreferences("Time", MODE_PRIVATE);
        prefEditor = sharedPref.edit();

        setMainTV(getString(R.string.room3buero_uhr_zeitumstellen));
    }



    public void setMainTV(String text){
        mainTV.setText(text);
    }

    @Override
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
        if (eingabe.contains(":") && eingabe.length() == 5){
            prefEditor.putString("time", eingabe);
            prefEditor.commit();

            nextLevel(Room_3buero.class);

        }else {
            setMainTV(getString(R.string.room3buero_uhr_zeitfehlgeschlagen) + " " + getString(R.string.room3buero_uhr_zeitumstellen));
        }
    }

    public void nextLevel(Class level){
        startActivity(new Intent(this.getApplicationContext(), level));
        finish();
    }
}
