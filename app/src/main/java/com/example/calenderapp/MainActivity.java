package com.example.calenderapp;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.TextView;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {
    public static CalendarView calender;
    TextView date_view;
    public static String date;
    public static String selectedDate;
    public static View v;
    public static String event;
    public static int month;
    public static boolean monthSelected;
    Button settingButton, yearButton;
    SharedPref sharedPref;

    static int m,day,y;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        sharedPref = new SharedPref(this);
        if(sharedPref.loadNightModeState() == true)
        {
            setTheme(R.style.DarkTheme);
        }
        else setTheme(R.style.AppTheme);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        boolean alarm = (PendingIntent.getBroadcast(this, 0, new Intent("ALARM"), PendingIntent.FLAG_NO_CREATE) == null);


        calender = (CalendarView) findViewById(R.id.calender);
        date_view = (TextView) findViewById(R.id.date_view);
        settingButton = (Button)findViewById(R.id.settingButton);
        yearButton = (Button)findViewById(R.id.yearView);
        yearButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, yearView.class);
                startActivity(i);
            }
        });
        settingButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, setting.class);
                startActivity(i);
            }
        });

        if(monthSelected) {
            Calendar c = Calendar.getInstance();
            int year = c.get(Calendar.YEAR);
            c.set(year, month, 1);
            calender.setDate(c.getTimeInMillis());
        }

        calender.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
                            @Override
                            public void onSelectedDayChange(CalendarView view, int year, int month, int dayOfMonth) {

                                String Date = (month+1) + "-" + dayOfMonth + "-" + year;
                                y = year;
                                day = dayOfMonth;
                                m = month;
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
    public static int getYear(){return y;}
    public static int getMonth(){return m;}
    public static int getDay(){return day;}

}
