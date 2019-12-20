package com.mrbing.lab3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    SharedPreferences sf;
    EditText name;
    TextView textDisplay;
    public static final String preference = "pref";
    public static final String saveit = "savekey";
    private static final String KEY_FIRSTNAME="firstname key";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        name = findViewById(R.id.name);
        textDisplay = findViewById(R.id.textDisplay);
        sf = getSharedPreferences(preference, Context.MODE_PRIVATE);

        if(savedInstanceState!=null){
            String savedname = savedInstanceState.getString(KEY_FIRSTNAME);
            textDisplay.setText(savedname);
        }
        else
            Toast.makeText(this, "new entry", Toast.LENGTH_LONG).show();

        if(sf.contains(saveit))
            name.setText(sf.getString(saveit,""));
    }

    public void onSaveInstanceState(Bundle savedInstanceState){
        savedInstanceState.putString(KEY_FIRSTNAME, textDisplay.getText().toString());
        super.onSaveInstanceState(savedInstanceState);
    }

    public void sav(View view) {
        String store=name.getText().toString();
        SharedPreferences.Editor ed=sf.edit();
        ed.putString(saveit,store);
        ed.commit();
    }

    public void del(View v) {
        name = findViewById(R.id.name);
        name.setText("");
    }

    public void ret(View v) {
        name = findViewById(R.id.name);
        sf = getSharedPreferences(preference, Context.MODE_PRIVATE);
        if(sf.contains(saveit))
            name.setText(sf.getString(saveit,""));
    }

    public void display (View v){
        textDisplay.setText(name.getText().toString());
    }

    public void nxt(View v){
        Intent intent = new Intent(MainActivity.this,Main2Activity.class);
        startActivity(intent);
    }
}
