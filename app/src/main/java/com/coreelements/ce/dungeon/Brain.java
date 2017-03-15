package com.coreelements.ce.dungeon;

import android.content.Intent;
import android.content.SharedPreferences;
import android.widget.TextView;

/**
 * Created by Private on 16.11.16.
 */
public class Brain extends LevelExample {

    SharedPreferences levelPref;
    SharedPreferences.Editor levelEditor;

    public Brain(){
        //levelPref = this.getSharedPreferences("Level", MODE_PRIVATE);
        //levelEditor = levelPref.edit();
    }

    public void saveLevel(String level){
        levelEditor.putString("level", level);
        levelEditor.commit();
        nextLevel(Room_1.class);
    }

    public void lol(){
        nextLevel(Room_3buero.class);
    }
}
