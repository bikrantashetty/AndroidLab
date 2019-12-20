package com.mrbing.lab6;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import android.app.Notification;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private NotificationManagerCompat mNotificationManagerCompat;
    public static final String CID = "channe1";
    String phone, name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mNotificationManagerCompat = NotificationManagerCompat.from(this);
    }

    public void notif() {
        Notification not1 = new NotificationCompat.Builder(this, CID)
                .setContentTitle(name)
                .setContentText(phone)
                .setSmallIcon(R.drawable.ic_launcher_foreground)
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .build();
        mNotificationManagerCompat.notify(1, not1);
    }

    public void contacts(View v) {
        Intent intent = new Intent(Intent.ACTION_PICK, ContactsContract.CommonDataKinds.Phone.CONTENT_URI);
        startActivityForResult(intent, 1);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onActivityResult(int requestCode, int resultCode,Intent data) {
        if(resultCode==RESULT_OK)
            if(requestCode == 1)
                contactpicked(data);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public void contactpicked(Intent data){
        Cursor cursor;
        try{
            Uri uri=data.getData();
            cursor=getContentResolver().query(uri,null,null,null);
            cursor.moveToFirst();
            int temp = cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER);
            phone=cursor.getString(temp);

            temp = cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME);
            name=cursor.getString(temp);

            Toast.makeText(getApplicationContext(),name+"\n"+phone,Toast.LENGTH_SHORT).show();
            notif();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}