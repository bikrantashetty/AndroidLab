package com.mrbing.lab1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class Main2Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        String str = getIntent().getStringExtra("uname");
        TextView textView = findViewById(R.id.tvun);
        textView.setText(str);
    }
}