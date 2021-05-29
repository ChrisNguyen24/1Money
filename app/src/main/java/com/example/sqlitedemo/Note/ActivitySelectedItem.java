package com.example.sqlitedemo.Note;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;

import com.example.sqlitedemo.R;
import com.example.sqlitedemo.SQLite.SQLiteStudentHelper;
import com.example.sqlitedemo.model.Note;

import java.util.Calendar;

public class ActivitySelectedItem extends AppCompatActivity {

    private EditText etTitle, etDesc ,etDate;
    private SQLiteStudentHelper sql;
    private Button btUpdate,btDel,btCanc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selected_item);
        sql = new SQLiteStudentHelper(this);
        etTitle = findViewById(R.id.uTitle);
        etDesc = findViewById(R.id.uDescription);
        etDate = findViewById(R.id.uDate);
        btUpdate = findViewById(R.id.uBtUpdate);
        btDel = findViewById(R.id.uBtDel);
        btCanc = findViewById(R.id.uBtCancel);

        Intent intent = getIntent();
        Note c  = (Note) intent.getSerializableExtra("item");
        etTitle.setText(String.valueOf(c.getTitle()));
        etDesc.setText(String.valueOf(c.getDescription()));
        etDate.setText(String.valueOf(c.getDate()));
        etDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar calendar = Calendar.getInstance();
                int Year = calendar.get(Calendar.YEAR);
                int Month = calendar.get(Calendar.MONTH);
                int Day = calendar.get(Calendar.DAY_OF_MONTH);
                DatePickerDialog datePickerDialog = new DatePickerDialog(ActivitySelectedItem.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        etDate.setText(dayOfMonth+"/"+(month+1)+"/"+year);
                    }
                },Year,Month,Day);
                datePickerDialog.show();
            }
        });
        btDel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sql.deleteById(c.getId());
                finish();
            }
        });
        btUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Note note = new Note(c.getId(),etTitle.getText().toString(),etDesc.getText().toString(),etDate.getText().toString());
                sql.updateStudent(note);
                finish();
            }
        });
        btCanc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}