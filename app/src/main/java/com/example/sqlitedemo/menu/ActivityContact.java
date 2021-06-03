package com.example.sqlitedemo.menu;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.sqlitedemo.MainActivity;
import com.example.sqlitedemo.R;

public class ActivityContact extends AppCompatActivity {
    private ImageButton imageButtonBack,imageButtonFb, imageButtonCall;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact);
        imageButtonBack = findViewById(R.id.contactBack);
        imageButtonBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        imageButtonFb = findViewById(R.id.contactBtnFB);
        imageButtonFb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent fb = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.facebook.com/cong175"));
                startActivity(fb);
                finish();
            }
        });
        imageButtonCall = findViewById(R.id.contactBtnCall);
        imageButtonCall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse("tel:" + "0339079168"));
                startActivity(intent);
            }
        });
    }
}