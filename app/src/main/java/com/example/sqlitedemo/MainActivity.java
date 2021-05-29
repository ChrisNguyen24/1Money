package com.example.sqlitedemo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

import com.example.sqlitedemo.Money.ActivityNewMoney;
import com.example.sqlitedemo.SQLite.SQLiteStudentHelper;
import com.example.sqlitedemo.adapter.RevAdapter;
import com.example.sqlitedemo.fragmentBottom.FragmentButtomAdapter;
import com.example.sqlitedemo.model.Cong;
import com.example.sqlitedemo.Note.ActivityCreateStudent;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class MainActivity extends AppCompatActivity {
    private Cong c;
    //btnav
    private ViewPager viewPager;
    private BottomNavigationView bottomNavigationView;
    private FragmentButtomAdapter buttomAdapter;
    //fab
    private FloatingActionButton fab,fabAdd,fabSearch;
    private boolean flag =true;
    private boolean fSearch = true;
    private EditText etSearch;

    //sql
    private SQLiteStudentHelper sql;
    //revadapter
    //rev
    private RecyclerView recyclerView;
    private RevAdapter revAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        sql = new SQLiteStudentHelper(this);
        revAdapter = new RevAdapter(this);
        //btnav
        buttomAdapter = new FragmentButtomAdapter(getSupportFragmentManager(), FragmentButtomAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        viewPager.setAdapter(buttomAdapter);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.mHome:viewPager.setCurrentItem(0);break;
                    case R.id.mMail:viewPager.setCurrentItem(1);break;
                    case R.id.mSearch:viewPager.setCurrentItem(2);break;
                    case R.id.mNoty:viewPager.setCurrentItem(3);break;
                }
                return true;
            }
        });

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                switch (position){
                    case 0:bottomNavigationView.getMenu().findItem(R.id.mHome).setChecked(true);break;
                    case 1:bottomNavigationView.getMenu().findItem(R.id.mMail).setChecked(true);break;
                    case 2:bottomNavigationView.getMenu().findItem(R.id.mSearch).setChecked(true);break;
                    case 3:bottomNavigationView.getMenu().findItem(R.id.mNoty).setChecked(true);break;
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        //fab
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (flag) {
                    fabAdd.show();
                    fabSearch.show();
                    fab.animate().rotation(270f);
                    fab.setImageResource(R.drawable.ic_baseline_close_24);
                    flag = false;

                } else {
                    fabAdd.hide();
                    fabSearch.hide();
                    fab.animate().rotation(360f);
                    fab.setImageResource(R.drawable.ic_baseline_add_24);
                    flag = true;
                    etSearch.setVisibility(View.GONE);
                }
            }
        });
        fabSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ActivityNewMoney.class);
                startActivity(intent);
            }
        });
        fabAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ActivityCreateStudent.class);
                startActivity(intent);
            }
        });
    }

    private void initView() {
        viewPager = findViewById(R.id.viewPager);
        bottomNavigationView = findViewById(R.id.btnav);
        fab = findViewById(R.id.fab);
        fabAdd = findViewById(R.id.fabAdd);
        fabSearch = findViewById(R.id.fabSearch);
        etSearch = findViewById(R.id.etSearch);
        recyclerView = findViewById(R.id.rev);
    }

}