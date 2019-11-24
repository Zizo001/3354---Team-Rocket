package com.example.calenderapp;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CalendarView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    public static CalendarView calender;
    TextView date_view;
    public static String date;
    public static String selectedDate;
    public static View v;
    public static String event;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        calender = (CalendarView) findViewById(R.id.calender);
        date_view = (TextView) findViewById(R.id.date_view);


        calender.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
                            @Override
                            public void onSelectedDayChange(CalendarView view, int year, int month, int dayOfMonth) {

                                String Date = (month+1) + "-" + dayOfMonth + "-" + year;

                                selectedDate = Integer.toString(year) + Integer.toString(month) + Integer.toString(dayOfMonth);

                                v = view;
                                // set this date in TextView for Display
                                date = Date;

                                Intent i = new Intent(MainActivity.this, dayView.class);
                                startActivity(i);

                            }
                        });
    }


    public static String getDate(){
        return date;
    }
    public static String getSelectedDate() {return selectedDate;}


}
