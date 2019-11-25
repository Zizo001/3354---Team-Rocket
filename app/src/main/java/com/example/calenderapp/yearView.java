package com.example.calenderapp;
import android.content.ContentValues;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridLayout;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.content.Intent;

import androidx.appcompat.app.AppCompatActivity;

import static com.example.calenderapp.MainActivity.v;

public class yearView extends AppCompatActivity {
    public String monthName;
    public int monthValue;
    public static Button buttonJanuary;
    //, buttonFebruary, buttonMarch, buttonApril, buttonJune, buttonJuly
    //, buttonAugust, buttonSeptember, buttonOctober, buttonNovember, buttonDecember;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        buttonJanuary = (Button) findViewById(R.id.buttonJanuary);
    buttonJanuary.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(yearView.this, MainActivity.class);
            startActivity(intent);
        }
    });


    }
}
