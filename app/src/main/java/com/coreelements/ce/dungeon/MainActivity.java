package com.coreelements.ce.dungeon;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {


    TextView    startTV;
    private MainCommands    mainCommands;
    private Brain lE;
    SharedPreferences levelPref;
    SharedPreferences.Editor levelEditor;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_main);

        mainCommands = new MainCommands();
        //nextLevel(Room_1.class);

        lE = new Brain();

        levelPref = this.getSharedPreferences("Level", MODE_PRIVATE);
        levelEditor = levelPref.edit();

        //lE.lol();

        if (levelPref.getString("level", "") == "3b"){
            nextLevel(Room_3buero.class);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {
        nextLevel(Room_1.class);
    }

    public void nextLevel(Class level){
        startActivity(new Intent(this.getApplicationContext(), level));
        finish();
    }
}
