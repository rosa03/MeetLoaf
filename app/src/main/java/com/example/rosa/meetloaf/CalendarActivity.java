package com.example.rosa.meetloaf;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.DatePicker;
import android.widget.TextView;

import java.util.Calendar;
import java.util.Date;

public class CalendarActivity extends AppCompatActivity {

    //    private int startYear;
    private int startMonth;
    private int startDay;
    private int year;
    private int month;
    private int day;
    private Context context;
    private CalendarView calendarView;

//    DatePickerDialog datePickerDialog = new DatePickerDialog(
//            this.context, this, startYear, startMonth, startDay);

    public CalendarActivity() {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar);
        calendarView = findViewById(R.id.calendarView);
        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView calendarView, int i, int i1, int i2) {
                String date = day + "/" + month + "/" + year;
                Intent intent = new Intent(CalendarActivity.this, MainActivity.class);
                intent.putExtra("date", date);
                startActivity(intent);
            }
        });
    }

//    @Override
//    public void onDateSet(DatePicker datePicker, int day, int month, int year) {
//        datePicker = findViewById(R.id.calendarView);
//        day = datePicker.getDayOfMonth();
//        month = datePicker.getMonth();
//        year = datePicker.getYear();
//        String date = day + "/" + month + "/" + year;
//        TextView dateTextView = findViewById(R.id.dateTextView);
//        dateTextView.setText(date);
//    }
}
