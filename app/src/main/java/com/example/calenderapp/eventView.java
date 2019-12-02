package com.example.calenderapp;
import android.app.AlarmManager;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Build;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.fragment.app.DialogFragment;
import java.util.Calendar;
import java.util.TimeZone;


//EventView class used to get user event and time
public class eventView extends AppCompatActivity{
    Button addEvent;                    //button for addEvent
    private EditText eventText;         //edittext for user to enter event description
    myDBAdapter helper;                 //getting the database helper
    Button backBack;                    //backbutton to go back instead of adding an event
    static Button timeButton;           //timebutton to show time picker
    static String event;                //holds event text
    static eventView eventInstance;
    static boolean deleteStatus;
    SharedPref sharedPref;
    //this methods creates the time picker dialog
    public void showTimePickerDialog(View v) {
        DialogFragment newFragment = new TimePickerFragment();
        newFragment.show(getSupportFragmentManager(), "timePick");
    }

    //onCreate method
    protected void onCreate(Bundle savedInstanceState) {
        //checking to see what the last theme was and setting it as the current
        sharedPref = new SharedPref(this);//creating an instance of sharedPref class

        //if the night mode is on
        if(sharedPref.loadNightModeState() == true)
        {
            //set the calender application to dark theme
            setTheme(R.style.DarkTheme);
        }
        else setTheme(R.style.AppTheme);//if not, set the calender application to app theme

        super.onCreate(savedInstanceState);

        eventInstance = this;

        setContentView(R.layout.event_view);        //running the eventView xml

        if(deleteStatus){removeReminder(helper.getRemoveId());}

        createNotificationChannel();

        helper = new myDBAdapter(this);     //new object for database helper

        String textStyle = setting.getTextStyle();  //getting the font style

        eventText = (EditText) findViewById(R.id.eventText);    //event edittext

        //checking for the font style and setting current style to it
        if(textStyle.equals("Bold")) {
            eventText.setTypeface(eventText.getTypeface(), Typeface.BOLD);
        }
        if(textStyle.equals("Regular")) {
            eventText.setTypeface(eventText.getTypeface(),Typeface.NORMAL);
        }
        if(textStyle.equals("Italic")) {
            eventText.setTypeface(eventText.getTypeface(),Typeface.ITALIC);
        }

        int alaramId = helper.getAlaramId()+1;
        final Intent intent = new Intent(eventView.this, AlarmReceiver.class);
        final PendingIntent pendingIntent = PendingIntent.getBroadcast(eventView.this, alaramId, intent, 0);
        final AlarmManager alarmManager = (AlarmManager)getSystemService(ALARM_SERVICE);


        timeButton = (Button)findViewById(R.id.button2);        //creating the time button
        backBack = (Button) findViewById(R.id.button4);         //creating the back button

        //on clik the back button go back to the dayView
        backBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(eventView.this, dayView.class);
                startActivity(i);
            }
        });


        addEvent = (Button) findViewById(R.id.addBut);      //creating the addEvent button
        //on click the addEvent button set alaram and insert into the database
        addEvent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MainActivity.event = eventText.getText().toString();
                event = eventText.getText().toString();


                //if no event description then error
                if(eventText.getText().toString().equals("")){
                    Toast toast = Toast.makeText(getApplicationContext(), "Need to add Event description!!", Toast.LENGTH_SHORT);
                    toast.setGravity(Gravity.CENTER, 0, 0);
                    toast.show();
                }else{
                //if time is selected
                if(TimePickerFragment.isTimeSelected()) {
                    //if event already exists then just update with new event description for that textview
                    if(dayView.getExists()){
                            int hour = TimePickerFragment.getHour();
                            int minute = TimePickerFragment.getMin();

                            Calendar c = Calendar.getInstance();
                            c.setTimeInMillis(System.currentTimeMillis());
                            c.set(Calendar.YEAR, MainActivity.getYear());
                            c.set(Calendar.MONTH, MainActivity.getMonth());
                            c.set(Calendar.DAY_OF_MONTH, MainActivity.getDay());
                            c.set(Calendar.HOUR_OF_DAY, hour);
                            c.set(Calendar.MINUTE, minute);
                            c.set(Calendar.SECOND, 0);

                            long alarmTime = c.getTimeInMillis();

                            alarmManager.set(AlarmManager.RTC_WAKEUP, alarmTime, pendingIntent);
                            Toast.makeText(getApplicationContext(), "Done!", Toast.LENGTH_SHORT).show();
                        helper.updateEvent(dayView.getBoxId(), eventText.getText().toString(), TimePickerFragment.getTime(), TimePickerFragment.getAmPm());
                        Intent i = new Intent(eventView.this, dayView.class);
                        startActivity(i);
                    }//else check to see time overrlap
                    else {
                        //if time overrlap then error
                        if (helper.checkTimeOverlap(TimePickerFragment.getTime(), TimePickerFragment.getAmPm())) {
                            Toast toast = Toast.makeText(getApplicationContext(), "Time Overlap!! Select Different Time!!", Toast.LENGTH_SHORT);
                            toast.setGravity(Gravity.CENTER, 0, 0);
                            toast.show();
                        } //else set alaram and insert into the database
                        else {
                            int hour = TimePickerFragment.getHour();
                            int minute = TimePickerFragment.getMin();

                            Calendar c = Calendar.getInstance();
                            c.setTimeInMillis(System.currentTimeMillis());
                            c.set(Calendar.YEAR, MainActivity.getYear());
                            c.set(Calendar.MONTH, MainActivity.getMonth());
                            c.set(Calendar.DAY_OF_MONTH, MainActivity.getDay());
                            c.set(Calendar.HOUR_OF_DAY, hour);
                            c.set(Calendar.MINUTE, minute);
                            c.set(Calendar.SECOND, 0);

                            long alarmTime = c.getTimeInMillis();

                            alarmManager.set(AlarmManager.RTC_WAKEUP, alarmTime, pendingIntent);
                            Toast.makeText(getApplicationContext(), "Done!", Toast.LENGTH_SHORT).show();

                            helper.insertData();
                            Intent i = new Intent(eventView.this, dayView.class);
                            startActivity(i);

                        }
                    }
                } //else error and ask for time
                else{
                    Toast toast = Toast.makeText(getApplicationContext(), "Need to select a Time for the Event!!", Toast.LENGTH_SHORT);
                    toast.setGravity(Gravity.CENTER, 0, 0);
                    toast.show();
                }
            }
            }
        });
    }
    //method to remove a reminder when deleting an event
    public void removeReminder (int alaramId){
        final Intent intent = new Intent(eventView.this, AlarmReceiver.class);
        final PendingIntent pendingIntent = PendingIntent.getBroadcast(eventView.this, alaramId, intent, 0);
        final AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
        //check to see if the alaram is already exist
        boolean alaramStatus = (PendingIntent.getBroadcast(eventView.this, alaramId, intent, PendingIntent.FLAG_NO_CREATE) != null);

        //if it does then disable it since we're deleting the event
        if(alaramStatus) {
            alarmManager.cancel(pendingIntent);
        }
    }

    //creating notification channel
    private void createNotificationChannel(){
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            CharSequence name = "MyCalendar";
            String description = "Channel for Event Reminder";
            int importance = NotificationManager.IMPORTANCE_HIGH;
            NotificationChannel channel = new NotificationChannel("myCalendar", name, importance);
            channel.setDescription(description);

            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }
    }

    public static eventView getInstance(){
        return eventInstance;
    }


}
