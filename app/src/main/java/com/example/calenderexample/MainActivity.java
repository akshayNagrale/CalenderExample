package com.example.calenderexample;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.CalendarContract;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private TextView txtView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        txtView = findViewById(R.id.txtView);

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_CALENDAR)!= PackageManager.PERMISSION_GRANTED){


                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_CALENDAR}, 101);

        }

        String[] projection = new String[] {CalendarContract.Events._ID,
                CalendarContract.Events.TITLE,
                CalendarContract.Events.DESCRIPTION};
        Cursor cursor = getContentResolver().query(CalendarContract.Events.CONTENT_URI,
                projection, null, null, null);

        if (null != cursor){
            if (cursor.moveToFirst()) {

                String text = "";
                for (int i = 0; i < cursor.getCount(); i++) {
                    for (int j = 0; j < cursor.getColumnCount(); j++) {
                        text += cursor.getColumnName(j) + ": " + cursor.getString(j) + "\n";

                    }
                    cursor.moveToNext();
                    text += "*************\n";
                }
                txtView.setText(text);
            }
            cursor.close();
        }
    }
}