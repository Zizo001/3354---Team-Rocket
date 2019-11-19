package com.example.calenderapp;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.RequiresPermission;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class dayView extends AppCompatActivity implements View.OnClickListener {

    TextView dateBox;

    public static String selectedDate;
    private String date;
    public static mySQLiteDBHandler dbHandler;
    public static EditText editText;
    public static SQLiteDatabase sqLiteDatabase;
    Button deleteButton;
    public static int num = 0;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.day_view);

        FloatingActionButton addButton = findViewById(R.id.addButton);
        addButton.setSize(FloatingActionButton.SIZE_AUTO);
        addButton.setOnClickListener(this);

       FloatingActionButton homeButton = findViewById(R.id.homeButton);
        homeButton.setSize(FloatingActionButton.SIZE_AUTO);
        homeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(dayView.this, MainActivity.class);
                startActivity(i);
            }
        });


        date = MainActivity.getDate();
        selectedDate = MainActivity.getSelectedDate();

        dateBox = (TextView) findViewById(R.id.textView);
        dateBox.setText(date);

        editText = (EditText) findViewById(R.id.editText1);

        editText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(dayView.this, eventView.class);
                startActivity(i);
            }
        });

        try{
            dbHandler = new mySQLiteDBHandler(this, "CalendarDatabase", null, 1);
            sqLiteDatabase = dbHandler.getWritableDatabase();
            sqLiteDatabase.execSQL("CREATE TABLE EventCalendar(Date TEXT,Event TEXT)");
        }
        catch (Exception e){
            e.printStackTrace();
        }

        ReadDatabase();
    }

    public static void InsertDatabase(View view){
            ContentValues contentValues = new ContentValues();
            contentValues.put("Date", selectedDate);
            contentValues.put("Event", "-" + MainActivity.event);
            sqLiteDatabase.insert("EventCalendar", null, contentValues);

            dayView dv = new dayView();
            dv.ReadDatabase();
    }

    public static void updateDatabase(){
        ContentValues contentValues = new ContentValues();
        contentValues.put("Date", selectedDate);
        contentValues.put("Event","-" + MainActivity.event);
        sqLiteDatabase.update("EventCalendar", contentValues, "Date =" + selectedDate, null);
    }

    public void DeleteDatabase(View view){
        sqLiteDatabase.delete("EventCalendar", "Date =" + selectedDate ,null);
        ReadDatabase();
    }

    public static String textEvent;

    public void ReadDatabase(){
        String query = "Select Event from EventCalendar where Date =" + selectedDate;
        try{
            Cursor cursor = sqLiteDatabase.rawQuery(query, null);
            cursor.moveToFirst();
            editText.setText(cursor.getString(0));
        }
        catch (Exception e){
            e.printStackTrace();
            editText.setText("");
        }
    }

    public void addLine(){
        LinearLayout ll = (LinearLayout) findViewById(R.id.llOne);
        EditText et = new EditText(this);
        if (textEvent.equals("")) {
            ll.removeView(et);
        }else {
            et.setText(textEvent);
            ll.addView(et);
        }

    }


    @Override
    public void onClick(View view) {
        Intent i = new Intent(dayView.this, eventView.class);
        startActivity(i);
    }

}

