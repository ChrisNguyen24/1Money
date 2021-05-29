package com.example.sqlitedemo.Money;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.sqlitedemo.R;
import com.example.sqlitedemo.SQLite.SQLiteMoneyHelper;
import com.example.sqlitedemo.model.Money;

public class ActivitySelectMoney extends AppCompatActivity {
    private EditText amount,time, note;
    private Button btUpdate,btDel, btCan;
    private Spinner spinnerCategry;
    private RadioButton radioButtonUChi,radioButtonUThu;
    private SQLiteMoneyHelper sqlite;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_money);
        initView();
        sqlite = new SQLiteMoneyHelper(this);
        Intent intent = getIntent();
        Money m = (Money) intent.getSerializableExtra("money");
        amount.setText(m.getAmount());
        time.setText(m.getDate());
        note.setText(m.getNote());
        String[] cate = getResources().getStringArray(R.array.category);
        for(int i=0;i<cate.length;i++){
            if(cate[i].equals(m.getCategory())){
                spinnerCategry.setSelection(i);
            }
        }
        if(m.getType().equals("Thu")){
            radioButtonUChi.setChecked(false);
            radioButtonUThu.setChecked(true);
        }
        else{
            radioButtonUChi.setChecked(true);
            radioButtonUThu.setChecked(false);
        }
        btCan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        btDel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sqlite.deleteById(m.getId());
                Toast.makeText(getApplicationContext(),"deleted item "+m.getId(),Toast.LENGTH_SHORT).show();
                finish();
            }
        });
        btUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int id=m.getId();
                if(radioButtonUChi.isChecked()){
                    sqlite.updateMoney(new Money(id,amount.getText().toString(),time.getText().toString(),spinnerCategry.getSelectedItem().toString(),"Chi",note.getText().toString()));
                }
                else{
                    sqlite.updateMoney(new Money(id,amount.getText().toString(),time.getText().toString(),spinnerCategry.getSelectedItem().toString(),"Thu",note.getText().toString()));
                }
                Toast.makeText(getApplicationContext(),"update item "+id,Toast.LENGTH_SHORT).show();
                finish();
            }
        });
    }

    private void initView() {
        amount = findViewById(R.id.uAmount);
        time  = findViewById(R.id.uDate);
        note = findViewById(R.id.uNote);
        btUpdate = findViewById(R.id.uBtUpdate);
        btDel = findViewById(R.id.uBtDelete);
        btCan = findViewById(R.id.uBtCancel);
        spinnerCategry = findViewById(R.id.uCatogory);
        radioButtonUChi = findViewById(R.id.uChi);
        radioButtonUThu = findViewById(R.id.uThu);
    }
}