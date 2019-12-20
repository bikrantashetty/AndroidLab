package com.mrbing.lab3;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Main2Activity extends AppCompatActivity {

    private DbHelper dbHelper;
    private Cursor cursor;
    private EditText etname,etsalary;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        etname = findViewById(R.id.etname);
        etsalary = findViewById(R.id.etsalary);
        
        dbHelper = new DbHelper(getApplicationContext());
    }

    public void insert(View v){
        String getname = etname.getText().toString();
        String getid = etsalary.getText().toString();
        dbHelper.inserRecord(getname, getid);
        Toast.makeText(Main2Activity.this, "Inserted  Successfully!\n" + getname + "\n" + getid, Toast.LENGTH_SHORT).show();
        //Toast.makeText(MainActivity.this,getid,Toast.LENGTH_LONG).show();
    }

    public void update(View v){
        String getname = etname.getText().toString();
        String getid = etsalary.getText().toString();
        boolean isUpdate = dbHelper.updateRecord(getname, getid);
        if (isUpdate == true)
            Toast.makeText(Main2Activity.this, "Record Updated Successfully..!  \n", Toast.LENGTH_LONG).show();
        else
            Toast.makeText(Main2Activity.this, "Record Not Updated Successfully..!  \n", Toast.LENGTH_LONG).show();
    }

    public void select(View v){
        cursor = dbHelper.selectRecords();
        if (cursor.moveToFirst()) {
            do {
                String strName = cursor.getString(cursor.getColumnIndex(DbHelper.NAME));
                String strSalary = cursor.getString(cursor.getColumnIndex(DbHelper.SALARY));
                Toast.makeText(Main2Activity.this, "Values are \n Name :" + strName + "\n Salary :" + strSalary, Toast.LENGTH_LONG).show();
            } while (cursor.moveToNext());
        }
        cursor.close();
    }

    public void delete(View v){
        dbHelper.deleteRecord();
        Toast.makeText(Main2Activity.this, "Record Deleted Successfully..!", Toast.LENGTH_LONG).show();
    }
}
