package com.mrbing.lab1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.i("Tag","onCreate");

        final EditText txtn = findViewById(R.id.nameText);
        EditText txtp = findViewById(R.id.passText);
        Button btn = findViewById(R.id.btn);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(),txtn.getText().toString(),Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(MainActivity.this,Main2Activity.class);
                intent.putExtra("uname",txtn.getText().toString());
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.i("Tag","onStart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i("Tag","onResume");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.i("Tag","onPause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.i("Tag","onStop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i("Tag","onDestroy");
    }
}