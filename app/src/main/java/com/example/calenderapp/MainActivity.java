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

/*
    This class creates the MonthView of the Calender Application
    This Activity is center of the application, with links to other Activities such as Year view, Settings view, and Day view
*/
public class MainActivity extends AppCompatActivity {
    //Declaring all necessary variables
    public static CalendarView calender;
    TextView date_view;
    public static String date;//variable to store date
    public static String selectedDate;//variable to store seleted date by user
    public static View v;
    public static String event;//variable to store event
    public static int month;//variable to store month
    public static boolean monthSelected;//variable to store whether if a month is selected or not
    Button settingButton, yearButton;//MainActivity has two buttons. settingButton takes to Setting view and yearButton takes to Year view
    SharedPref sharedPref;

    static int m,day,y;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        sharedPref = new SharedPref(this);//creating an instance of sharedPref class
        
        //if the night mode is on
        if(sharedPref.loadNightModeState() == true)
        {
            //set the calender application to dark theme
            setTheme(R.style.DarkTheme);
        }
        else setTheme(R.style.AppTheme);//if not, set the calender application to app theme

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        boolean alarm = (PendingIntent.getBroadcast(this, 0, new Intent("ALARM"), PendingIntent.FLAG_NO_CREATE) == null);

        //Capturing all the fields created from xml file and storing them in variables in MainActivity.java file using id's declared in xml file
        calender = (CalendarView) findViewById(R.id.calender);//the Month View itself
        date_view = (TextView) findViewById(R.id.date_view);//date view that is displayed at the top of calender application when user selects a date
        settingButton = (Button)findViewById(R.id.settingButton);//settings button that takes to settings view
        yearButton = (Button)findViewById(R.id.yearView);//year view button that takes to year view
        
        /*
            Setting OnClickListener to yearButton. Whenever the Year Button is clicked, this method is called to change the current activity,
            which is MainActivity, to another activity, which is yearView.
        */
        yearButton.setOnClickListener(new OnClickListener() {
            /**
                When the Year button is clicked, this function changes the state of current activity to a new activity
                @param: view         
            **/
            
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, yearView.class);
                startActivity(i);
            }
        });
        /*
            Setting OnClickListener to settingButton. Whenever the Settings Button is clicked, this method is called to change the current activity,
            which is MainActivity, to another activity, which is setting.
        */
        settingButton.setOnClickListener(new OnClickListener() {
            /**
                When the Setting button is clicked, this function changes the state of current activity to a new activity
                @param: view         
            **/
            
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

        /*
            Adding Listener to calender than is called when user selects a date
        */
        calender.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
                            /**
                                This method takes the selected date from the user, which consists of year, month, and day
                                and creates a single interger. Then the application changes from current Activity, which is 
                                MainActivity, to another activity, which is dayView.
                                
                                The dayView is unique to each specific day in every month, every year
                                @param: view, year, month, day
                            **/
                            @Override
                            public void onSelectedDayChange(CalendarView view, int year, int month, int dayOfMonth) {

                                String Date = (month+1) + "-" + dayOfMonth + "-" + year;
                                y = year;
                                day = dayOfMonth;
                                m = month;
                                selectedDate = Integer.toString(year) + Integer.toString(month) + Integer.toString(dayOfMonth);

                                v = view;
                                
                                date = Date;
                                    
                                //changing activity
                                Intent i = new Intent(MainActivity.this, dayView.class);
                                startActivity(i);
                            }
                        });
    }


    /**
        Function returns the date
        @return: date
    **/
    public static String getDate(){
        return date;
    }
     /**
        Function returns the selected date by user
        @return: selectedDate
    **/
    public static String getSelectedDate() {return selectedDate;}
     /**
        Function returns the year
        @return: y
    **/
    public static int getYear(){return y;}
      /**
        Function returns the month
        @return: m
    **/
    public static int getMonth(){return m;}
      /**
        Function returns the day
        @return: day
    **/
    public static int getDay(){return day;}

}
