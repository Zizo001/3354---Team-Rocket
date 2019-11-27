package com.example.calenderapp;
import android.app.DatePickerDialog;
import android.content.ContentValues;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.GridLayout;
import android.widget.LinearLayout;
import android.widget.NumberPicker;
import android.widget.TextView;
import android.content.Intent;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Calendar;

import static com.example.calenderapp.MainActivity.v;

public class yearView extends AppCompatActivity {
    public String monthName;
    public int monthValue;
    public static Button buttonJanuary;


    //method to incorporate button functionality 
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.year_view);
        Calendar c = Calendar.getInstance();

        int year = c.get(Calendar.YEAR);
        final TextView yearText = (TextView)findViewById(R.id.yearText);
        yearText.setText(Integer.toString(year));                       

     //each month has a corresponding button with values from 0-11 assigned to the respective activity
     //and sets monthSelected to true   
        buttonJanuary = (Button) findViewById(R.id.buttonJanuary);
        buttonJanuary.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity.monthSelected = true;
                MainActivity.month = 0;
                changeView();
            }
        });

        buttonJanuary = (Button) findViewById(R.id.buttonFebruary);
        buttonJanuary.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity.monthSelected = true;
                MainActivity.month = 1;
                changeView();
            }
        });

        buttonJanuary = (Button) findViewById(R.id.buttonMarch);
        buttonJanuary.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity.monthSelected = true;
                MainActivity.month = 2;
                changeView();
            }
        });
        buttonJanuary = (Button) findViewById(R.id.buttonApril);
        buttonJanuary.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity.monthSelected = true;
                MainActivity.month = 3;
                changeView();
            }
        });
        buttonJanuary = (Button) findViewById(R.id.buttonMay);
        buttonJanuary.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity.monthSelected = true;
                MainActivity.month = 4;
                changeView();
            }
        });
        buttonJanuary = (Button) findViewById(R.id.buttonJune);
        buttonJanuary.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity.monthSelected = true;
                MainActivity.month = 5;
                changeView();
            }
        });
        buttonJanuary = (Button) findViewById(R.id.buttonJuly);
        buttonJanuary.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity.monthSelected = true;
                MainActivity.month = 6;
                changeView();
            }
        });
        buttonJanuary = (Button) findViewById(R.id.buttonAugust);
        buttonJanuary.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity.monthSelected = true;
                MainActivity.month = 7;
                changeView();
            }
        });
        buttonJanuary = (Button) findViewById(R.id.buttonSeptember);
        buttonJanuary.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity.monthSelected = true;
                MainActivity.month = 8;
                changeView();
            }
        });
        buttonJanuary = (Button) findViewById(R.id.buttonOctober);
        buttonJanuary.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity.monthSelected = true;
                MainActivity.month = 9;
                changeView();
            }
        });
        buttonJanuary = (Button) findViewById(R.id.buttonNovember);
        buttonJanuary.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity.monthSelected = true;
                MainActivity.month = 10;
                changeView();
            }
        });
        buttonJanuary = (Button) findViewById(R.id.buttonDecember);
        buttonJanuary.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity.monthSelected = true;
                MainActivity.month = 11;
                changeView();
            }
        });





    }
    //intent that transfers from yearView to the mainActivity aka month view
    private void changeView(){
        Intent intent = new Intent(yearView.this, MainActivity.class);
        startActivity(intent);
    }
}
