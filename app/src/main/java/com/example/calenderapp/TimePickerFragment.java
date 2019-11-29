package com.example.calenderapp;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.widget.Button;
import android.widget.TimePicker;

import androidx.fragment.app.DialogFragment;

import java.util.Calendar;

public class TimePickerFragment extends DialogFragment implements TimePickerDialog.OnTimeSetListener {
    public static String time;
    public static String ampm;
    public static boolean selectedTime;
    public static int min;
    public static int h, hour;
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Use the current time as the default values for the picker
        selectedTime = false;
        final Calendar c = Calendar.getInstance();
        int hour = c.get(Calendar.HOUR_OF_DAY);
        int h = c.get(Calendar.HOUR);
        int minute = c.get(Calendar.MINUTE);

        // Create a new instance of TimePickerDialog and return it
        return new TimePickerDialog(getActivity(), AlertDialog.THEME_HOLO_DARK, this, hour, minute, false);
    }

        //method check for time set and sets am and pm
    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
        hour = hourOfDay;
        min = minute;
        selectedTime = true;

       int hour = hourOfDay;
        int minutes = minute;
        String timeSet = "";
        if (hour > 12) {
            hour -= 12;
            timeSet = "pm";
        } else if (hour == 0) {
            hour += 12;
            timeSet = "am";
        } else if (hour == 12){
            timeSet = "pm";
        }else{
            timeSet = "am";
        }

        String min = "";
        if (minutes < 10)
            min = "0" + minutes ;
        else
            min = String.valueOf(minutes);

                //creating the time string
         time = new StringBuilder().append(hour).append(':')
                .append(min ).append(" ").toString();

            //creating the am or pm
         ampm = new StringBuilder().append(timeSet).toString();

         eventView.timeButton.setText(time +" " + ampm);        //setting the timebutton with the selected time
    }

    public static String getTime(){
        return time;
    }
    public static String getAmPm(){return ampm;}
    public static int getHour() {return hour;}
    public static int get12Hr(){return h;};
    public static int getMin() {return min;}

    public static boolean isTimeSelected(){
        return selectedTime;
    }       //return isTimeSelected
}
