package com.example.attendance_manager_try;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.TimePicker;

import java.util.Calendar;

public class Session_Create extends AppCompatActivity implements DatePickerDialog.OnDateSetListener, TimePickerDialog.OnTimeSetListener {

    Button time_Set;
    TextView time_Show;

    int day,month,year,hour,minute;
    int final_day,final_month,final_year,final_hour,final_minute;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_session_create);

        time_Set = findViewById(R.id.TimeSetter);
        time_Show = findViewById(R.id.time_Shower);
        time_Set.setOnClickListener(view -> {
            Calendar cal = Calendar.getInstance();
            year = cal.get(Calendar.YEAR);
            month = cal.get(Calendar.MONTH);
            day = cal.get(Calendar.DAY_OF_MONTH);
            DatePickerDialog datePickerDialog = new DatePickerDialog(Session_Create.this,Session_Create.this,year,month,day);
            datePickerDialog.show();
        });
    }

    @Override
    public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
        final_year = i;
        final_month = i1;
        final_day = i2;

        Calendar cal = Calendar.getInstance();
        hour = cal.get(Calendar.HOUR_OF_DAY);
        minute = cal.get(Calendar.MINUTE);
        TimePickerDialog timePickerDialog = new TimePickerDialog(Session_Create.this,Session_Create.this,hour,minute, DateFormat.is24HourFormat(this));
        timePickerDialog.show();
    }

    @Override
    public void onTimeSet(TimePicker timePicker, int i, int i1) {
        final_hour = i;
        final_minute = i1;
        time_Show.setText("StartTime : " + final_hour + ":" + final_minute);
    }
}