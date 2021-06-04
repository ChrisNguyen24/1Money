package com.example.sqlitedemo.Note;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlarmManager;
import android.app.DatePickerDialog;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;

import com.example.sqlitedemo.MyReceiver;
import com.example.sqlitedemo.R;
import com.example.sqlitedemo.SQLite.SQLiteStudentHelper;
import com.example.sqlitedemo.model.Note;
import com.example.sqlitedemo.model.StateManager;

import java.util.Calendar;


public class ActivityCreateStudent extends AppCompatActivity {
    private Button btAdd,btCacel;
    private EditText etTitle, etDes, etDate, moreWho,moreTime,moreLocate;
    private RadioButton rb1,rb2;
    private ImageView btMore;
    private LinearLayout linearLayoutMore;

    private int flag = 1;
    private SQLiteStudentHelper sqli;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_student);
        moreWho = findViewById(R.id.ExpandFor);
        moreTime = findViewById(R.id.ExpandTime);
        moreLocate = findViewById(R.id.ExpandLocate);

        btAdd = findViewById(R.id.cBtAdd);
        btCacel = findViewById(R.id.cBtCancel);
        etTitle = findViewById(R.id.cTitle);
        etDes = findViewById(R.id.cDescription);
        etDate = findViewById(R.id.cDate);
        rb1 = findViewById(R.id.cRb1);
        rb2 = findViewById(R.id.cRb2);

        linearLayoutMore = findViewById(R.id.ExpandMore);
        btMore = findViewById(R.id.imgMore);
        btMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(flag == 1){
                    linearLayoutMore.setVisibility(View.VISIBLE);
                    flag = 0;
                    btMore.setImageResource(R.drawable.ic_baseline_short_text_24);
                }
                else{
                    linearLayoutMore.setVisibility(View.GONE);
                    btMore.setImageResource(R.drawable.down_24);
                    flag = 1;
                }
            }
        });

        sqli = new SQLiteStudentHelper(this);
        etDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar calendar = Calendar.getInstance();
                int Year = calendar.get(Calendar.YEAR);
                int Month = calendar.get(Calendar.MONTH);
                int Day = calendar.get(Calendar.DAY_OF_MONTH);
                DatePickerDialog datePickerDialog = new DatePickerDialog(ActivityCreateStudent.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        etDate.setText(dayOfMonth+"/"+(month+1)+"/"+year);
                    }
                },Year,Month,Day);
                datePickerDialog.show();
            }
        });
        btAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Note note = new Note(etTitle.getText().toString(),etDes.getText().toString(),etDate.getText().toString());
                sqli.addSutdent(note);
                if(rb1.isChecked()){
                    /*String time_date = etDate.getText().toString();
                    String[] time_spilt = time_date.split("/");
                    int dd = Integer.parseInt(time_spilt[0]);
                    int mm = Integer.parseInt(time_spilt[1]);
                    int yy = Integer.parseInt(time_spilt[2]);
                    System.out.println("timepicker "+ dd+"/"+mm+"/"+yy);
                    Calendar calendar = Calendar.getInstance();
                    calendar.setTimeInMillis(System.currentTimeMillis());
                    calendar.set(yy,mm,dd);
                    AlarmManager am = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
                    Intent intent = new Intent(ActivityCreateStudent.this,MyReceiver.class);

                    StateManager.NotificationData notificationData = StateManager.getInstance().notificationData;
                    notificationData.Title = etTitle.getText().toString();
                    notificationData.Description = etDes.getText().toString();
                    intent.putExtra("myAction","mDoNotify");
                    intent.putExtra("Title",etTitle.getText().toString());
                    intent.putExtra("Description",etDes.getText().toString());
                    Log.e("des truoc receiver",intent.getStringExtra("Description"));
                    PendingIntent pendingIntent = PendingIntent.getBroadcast(ActivityCreateStudent.this,0,intent,0);
                    am.set(AlarmManager.RTC_WAKEUP,calendar.getTimeInMillis(),pendingIntent);*/
                    String time_date = etDate.getText().toString();
                    String [] time_spilt=time_date.split("/");
                    int dd = Integer.parseInt(time_spilt[0]);
                    int mm = Integer.parseInt(time_spilt[1])-1;
                    int yy = Integer.parseInt(time_spilt[2]);
                    System.out.println("timepicker "+ dd+"/"+mm+"/"+yy);
                    Calendar calendar = Calendar.getInstance();
                    calendar.setTimeInMillis(System.currentTimeMillis());
                    calendar.set(yy,mm,dd);
                    AlarmManager am = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
                    Intent intent = new Intent(ActivityCreateStudent.this,MyReceiver.class);

                    StateManager.NotificationData notificationData = StateManager.getInstance().notificationData;
                    notificationData.Title = etTitle.getText().toString();
                    notificationData.Description = etDes.getText().toString();
                    intent.putExtra("myAction","mDoNotify");
                    intent.putExtra("Title",etTitle.getText().toString());
                    intent.putExtra("Description",etDes.getText().toString());
                    Log.e("des truoc receiver",intent.getStringExtra("Description"));
                    //pending intent de goi myreciever chay ngam
                    PendingIntent pendingIntent = PendingIntent.getBroadcast(ActivityCreateStudent.this,0,intent,0);//dat thong bao
                    am.set(AlarmManager.RTC_WAKEUP,calendar.getTimeInMillis(),pendingIntent);


                }
                finish();
            }
        });
        btCacel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


    }


}