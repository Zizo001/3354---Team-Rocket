package com.example.calenderapp;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

//importing necessary files
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.TextView;

//main class
public class MainActivity extends AppCompatActivity {
     //delcaring necessary variables
    public static CalendarView calender;
    TextView date_view;
    public static String date;
    public static String selectedDate;
    public static View v;
    public static String event;
    Button settingButton, yearButton;
    SharedPref sharedPref;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //creating an instance of SharedPref
        sharedPref = new SharedPref(this);
        //checking if the current state is nightMode.
        //if true, set theme to DarkTheam
        if(sharedPref.loadNightModeState() == true)
        {
            setTheme(R.style.DarkTheme);
        }
        else setTheme(R.style.AppTheme);//else AppTheme

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        //created claender view in the xml and that view has id as 'calender'.
        //here, we are capturing that view by findViewById and storing it in calender variable
        calender = (CalendarView) findViewById(R.id.calender);
        //capturing date_view and storing it in date_view variable
        date_view = (TextView) findViewById(R.id.date_view);
        //capturing the settingsButton and storing it in settingButton variable
        settingButton = (Button)findViewById(R.id.settingButton);
         //capturing the yearButton and storing it in yearButton variable
        yearButton = (Button)findViewById(R.id.yearView);
        
        //adding event Listner to yearButton
        yearButton.setOnClickListener(new OnClickListener() {
            //when clicked, app would take user to yearView class
            @Override
            public void onClick(View v) {
                //going into year view
                Intent i = new Intent(MainActivity.this, yearView.class);
                startActivity(i);//start yearView
            }
        });
        
        //adding event Listner to settingButton
        settingButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                //going into settings view
                Intent i = new Intent(MainActivity.this, setting.class);
                startActivity(i);//start settingsView
            }
        });

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
