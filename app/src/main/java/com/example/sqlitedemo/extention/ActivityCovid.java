package com.example.sqlitedemo.extention;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.example.sqlitedemo.R;
import com.example.sqlitedemo.model.Covid;

public class ActivityCovid extends AppCompatActivity {
    private TextView cases, recovered,critical, active, todaycases, deaths, todaydeaths, countryaffect;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_covid);
        cases = findViewById(R.id.coCase);
        recovered = findViewById(R.id.coRecovered);
        critical = findViewById(R.id.coCritical);
        active = findViewById(R.id.coActive);
        todaycases = findViewById(R.id.coTodayCases);
        deaths = findViewById(R.id.coDeaths);
        todaydeaths = findViewById(R.id.coTodayDeaths);
        countryaffect = findViewById(R.id.coAffectedCountries);
        Intent intent = getIntent();
        Covid c = (Covid) intent.getSerializableExtra("covid");
        cases.setText(String.valueOf(c.getCases()));
        recovered.setText(String.valueOf(c.getRecovered()));
        critical.setText(String.valueOf(c.getCritical()));
        active.setText(String.valueOf(c.getActive()));
        todaycases.setText(String.valueOf(c.getTodayCases()));
        deaths.setText(String.valueOf(c.getDeaths()));
        todaydeaths.setText(String.valueOf(c.getTodayDeaths()));
        countryaffect.setText(String.valueOf(c.getAffectedCountries()));
    }
}