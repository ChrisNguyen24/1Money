package com.example.sqlitedemo.Money;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;

import com.example.sqlitedemo.R;
import com.example.sqlitedemo.SQLite.SQLiteMoneyHelper;
import com.example.sqlitedemo.model.Money;

import java.util.Calendar;

public class ActivityNewMoney extends AppCompatActivity {
    private EditText amount,time,note;
    private Spinner spinnerCategory;
    private RadioButton typeChi,typeThu;
    private Button btAdd,btCan;
    private SQLiteMoneyHelper sqlite;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_money);
        sqlite = new SQLiteMoneyHelper(this);
        amount = findViewById(R.id.mAmount);
        time = findViewById(R.id.mDate);
        note = findViewById(R.id.mNote);
        spinnerCategory = findViewById(R.id.mCatogory);
        typeChi = findViewById(R.id.mChi);
        typeThu = findViewById(R.id.mThu);
        btAdd = findViewById(R.id.mBtAdd);
        btCan = findViewById(R.id.mBtCancel);
        btAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(typeChi.isChecked()){
                     sqlite.addMoney(new Money(amount.getText().toString(),time.getText().toString(),spinnerCategory.getSelectedItem().toString(),typeChi.getText().toString(),note.getText().toString()));
                }else{
                    sqlite.addMoney(new Money(amount.getText().toString(),time.getText().toString(),spinnerCategory.getSelectedItem().toString(),typeThu.getText().toString(),note.getText().toString()));
                }
                finish();
            }
        });

        btCan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        time.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar calendar = Calendar.getInstance();
                int yy = calendar.get(Calendar.YEAR);
                int mm = calendar.get(Calendar.MONTH);
                int dd = calendar.get(Calendar.DATE);
                DatePickerDialog dialog = new DatePickerDialog(ActivityNewMoney.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        time.setText(dd+"/"+month+"/"+year);
                    }
                },yy,mm,dd);
                dialog.show();
            }
        });
    }
}