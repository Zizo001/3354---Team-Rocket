/**
 * This class is used to make the dayView
 * dayView shows the user the events in a day
 * @author: Akshar Patel
 * @documentor: Ramin Nourbakhsh
 */
package com.example.calenderapp;
import android.app.ActionBar;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class dayView extends AppCompatActivity implements View.OnClickListener {

    TextView dateBox;
    myDBAdapter helper; //this is a data base helper which allows the user to store events
    public static String selectedDate;
    private String date;
    LinearLayout container;
    public static boolean exists;
    public static int boxid;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.day_view);

        container = (LinearLayout)findViewById(R.id.llone);

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

        helper = new myDBAdapter(this);

        date = MainActivity.getDate();
        selectedDate = MainActivity.getSelectedDate();

        dateBox = (TextView) findViewById(R.id.textView);
        dateBox.setText(date);

        List<String> event = helper.getData(selectedDate);
        List<Integer> ids = helper.getIds();

       for(String e : event){
            int i = ids.get(event.indexOf(e));
            addLine(e, i);
        }

    }

    /**
     * addLine represents events in the day as a text format
     * @param e event description
     * @param uid Id given to event by database
     */
    public void addLine(final String e, final int uid){
        LayoutInflater layoutInflater = (LayoutInflater) getBaseContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        final View addView = layoutInflater.inflate(R.layout.row, null);
        final AutoCompleteTextView tv = (AutoCompleteTextView)addView.findViewById(R.id.textout);
        tv.setText(e);
        tv.setFocusable(false);
        tv.setCursorVisible(false);
        tv.setKeyListener(null);
        tv.setId(uid);
        final Button removeButton = (Button)addView.findViewById(R.id.remove);
        removeButton.setId(uid);
        exists = false;

        final View.OnClickListener thisListener1 = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setBoxId(tv.getId());
                setExists(true);
               Intent i = new Intent(dayView.this, eventView.class);
               startActivity(i);
            }
        };

        final View.OnClickListener thisListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int id = removeButton.getId();
                helper.delete(id);
                ((LinearLayout)addView.getParent()).removeView(addView);
            }
        };

        removeButton.setOnClickListener(thisListener);
        tv.setOnClickListener(thisListener1);
        container.addView(addView);
    }

    //setter for exists
    public void setExists(boolean e){
        exists = e;
    }

    //getter for exists
    public static boolean getExists(){
        return exists;
    }

    //setter for boxId
    public void setBoxId(int i){
        boxid = i;
    }

    //getter for boxId
    public static int getBoxId(){
        return boxid;
    }

    @Override
    public void onClick(View view) {
        Intent i = new Intent(dayView.this, eventView.class);
        startActivity(i);
    }

}

