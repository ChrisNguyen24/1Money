package com.example.sqlitedemo.extention;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.sqlitedemo.R;

public class ActivityTax extends AppCompatActivity {
    private EditText salary;
    private Spinner spBH,spPT;
    private Button btTinh;
    private TextView dong,salaryAfter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tax);
        initView();
        btTinh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                float sl = Float.parseFloat(salary.getText().toString());
                float bh = Float.parseFloat(spBH.getSelectedItem().toString());
                int pt = Integer.parseInt(spPT.getSelectedItem().toString())*4400;
                System.out.println(pt);
                System.out.println(bh*0.105);
                float dongThue = (float) (sl-(11000+pt+bh*0.105));
                if(dongThue>0){
                    dong.setText("Thu nhap chiu thue: "+String.valueOf(String.format("%.3f",dongThue))+" VND");
                }
                else{
                    dong.setText("Khong phai dong thue");
                }
                //Toast.makeText(ActivityTax.this,"test  "+dongThue,Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void initView() {
        salary = findViewById(R.id.taxSalary);
        spBH = findViewById(R.id.spinnerBH);
        spPT = findViewById(R.id.spinnerPT);
        btTinh  = findViewById(R.id.taxBtTinh);
        dong = findViewById(R.id.taxTaxRes);
        salaryAfter = findViewById(R.id.taxTaxIncom);
    }
}