package com.example.calenderapp;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import java.util.Calendar;

public class eventView extends AppCompatActivity{
    Button addEvent;
    private EditText eventText;
    myDBAdapter helper;
    Button backBack;

    public void showTimePickerDialog(View v) {
        DialogFragment newFragment = new TimePickerFragment();
        newFragment.show(getSupportFragmentManager(), "timePicker");
    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.event_view);

        helper = new myDBAdapter(this);

        eventText = (EditText) findViewById(R.id.eventText);

        backBack = (Button) findViewById(R.id.button4);
        backBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(eventView.this, dayView.class);
                startActivity(i);
            }
        });


        addEvent = (Button) findViewById(R.id.addBut);
        addEvent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MainActivity.event = eventText.getText().toString();
                if(eventText.getText().toString().equals("")){
                    Toast toast = Toast.makeText(getApplicationContext(), "Need to add Event description!!", Toast.LENGTH_SHORT);
                    toast.setGravity(Gravity.CENTER, 0, 0);
                    toast.show();
                }

                if(TimePickerFragment.isTimeSelected()) {
                    if(dayView.getExists()){
                        helper.updateEvent(dayView.getBoxId(), eventText.getText().toString(), TimePickerFragment.getTime(), TimePickerFragment.getAmPm());
                        Intent i = new Intent(eventView.this, dayView.class);
                        startActivity(i);
                    }else {
                        if (helper.checkTimeOverlap(TimePickerFragment.getTime(), TimePickerFragment.getAmPm())) {
                            Toast toast = Toast.makeText(getApplicationContext(), "Time Overlap!! Select Different Time!!", Toast.LENGTH_SHORT);
                            toast.setGravity(Gravity.CENTER, 0, 0);
                            toast.show();
                        } else {
                            helper.insertData();
                            Intent i = new Intent(eventView.this, dayView.class);
                            startActivity(i);

                        }
                    }
                }else{
                    Toast toast = Toast.makeText(getApplicationContext(), "Need to select a Time for the Event!!", Toast.LENGTH_SHORT);
                    toast.setGravity(Gravity.CENTER, 0, 0);
                    toast.show();
                }
            }
        });

    }

}
