package com.example.calenderapp;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CalendarView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    //delcaring necessary variables
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

        //created claender view in the xml and that view has id as 'calender'.
        //here, we are capturing that view by findViewById and storing it in calender variable
        calender = (CalendarView) findViewById(R.id.calender);
        //capturing date_view and storing it in date_view app
        date_view = (TextView) findViewById(R.id.date_view);

        //setOnDateChangeListener() is called upon change of the selected day
        calender.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
                            //Function is called when clicked on a date.
                            // onSelectedDayChange() takes in calender view, year, month, day and takes user to daysView
                            //this dayView is unique to each day
                            @Override
                            public void onSelectedDayChange(CalendarView view, int year, int month, int dayOfMonth) {
                                //Date variable contains month, day, and year
                                String Date = (month+1) + "-" + dayOfMonth + "-" + year;

                                //selected date contains data in integer
                                selectedDate = Integer.toString(year) + Integer.toString(month) + Integer.toString(dayOfMonth);
                                //capturing the view
                                v = view;
                                // set this date in TextView for Display
                                date = Date;

                                //going into date view
                                Intent i = new Intent(MainActivity.this, dayView.class);
                                startActivity(i);//starts dateView class

                            }
                        });
    }


    //returns date 
    public static String getDate(){
        return date;
    }
    //returns the selectedDate
    public static String getSelectedDate() {return selectedDate;}


}
