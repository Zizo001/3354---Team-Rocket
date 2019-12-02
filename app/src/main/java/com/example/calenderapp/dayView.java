package com.example.calenderapp;
import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import java.util.List;

//dayView class used to view all the events for a selected Date
public class dayView extends AppCompatActivity implements View.OnClickListener {

    TextView dateBox;                       //view to hold selected date
    myDBAdapter helper;                     //database helper
    public static String selectedDate;      //selected date
    private String date;                    //date
    LinearLayout container;                 //linearLayout to hold all the textviews
    public static boolean exists;           //exists check
    public static int boxid;                //boxid
    SharedPref sharedPref;

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

        setContentView(R.layout.day_view);  //setting up the view

        String textStyle = setting.getTextStyle();  //getting the font style

        container = (LinearLayout)findViewById(R.id.llone);     //creating the LinearLayout

        FloatingActionButton addButton = findViewById(R.id.addButton);      //adding floating button for add
        addButton.setSize(FloatingActionButton.SIZE_AUTO);
        addButton.setOnClickListener(this);

        FloatingActionButton homeButton = findViewById(R.id.homeButton);      //adding floating button for home
        homeButton.setSize(FloatingActionButton.SIZE_AUTO);
        //on click home button go to MainActivity
        homeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(dayView.this, MainActivity.class);
                startActivity(i);
            }
        });

        helper = new myDBAdapter(this);     //new database helper object

        date = MainActivity.getDate();          //get the date
        selectedDate = MainActivity.getSelectedDate();      //get the selected date in corrected format

        dateBox = (TextView) findViewById(R.id.textView);      //create the datebox

        //setting the font sytle
        if(textStyle.equals("Bold")) {
            dateBox.setTypeface(dateBox.getTypeface(),Typeface.BOLD);
        }
        if(textStyle.equals("Regular")) {
            dateBox.setTypeface(dateBox.getTypeface(),Typeface.NORMAL);
        }
        if(textStyle.equals("Italic")) {
            dateBox.setTypeface(dateBox.getTypeface(),Typeface.ITALIC);
        }
        dateBox.setText(date);

        List<String> event = helper.getData(selectedDate);      //getting the events by reading the database
        List<Integer> ids = helper.getIds();                    //getting the ids

        //adding a new line using event and id
        for(String e : event){
            int i = ids.get(event.indexOf(e));
            addLine(e, i);
        }

    }

    //for each event add a textView with the event description and add a done button and set it to the id
    public void addLine(final String e, final int uid){
        String textStyle = setting.getTextStyle();
        LayoutInflater layoutInflater = (LayoutInflater) getBaseContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        final View addView = layoutInflater.inflate(R.layout.row, null);
        final AutoCompleteTextView tv = (AutoCompleteTextView)addView.findViewById(R.id.textout);
        tv.setText(" " + e);
        //setting the font style
        if(textStyle.equals("Bold")) {
            tv.setTypeface(tv.getTypeface(),Typeface.BOLD);
        }
        if(textStyle.equals("Regular")) {
            tv.setTypeface(tv.getTypeface(),Typeface.NORMAL);
        }
        if(textStyle.equals("Italic")) {
            tv.setTypeface(tv.getTypeface(),Typeface.ITALIC);
        }
        tv.setFocusable(false);
        tv.setCursorVisible(false);
        tv.setKeyListener(null);
        tv.setId(uid);
        final Button removeButton = (Button)addView.findViewById(R.id.remove);
        removeButton.setId(uid);
        exists = false;

        //listens for user touch on the eventBox to call eventView for updating an event
        final View.OnClickListener thisListener1 = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setBoxId(tv.getId());
                setExists(true);
                Intent i = new Intent(dayView.this, eventView.class);
                startActivity(i);
            }
        };

        //listens for user touch on the done button to remove the event from database and dayView
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

    //method to set if event exists
    public void setExists(boolean e){
        exists = e;
    }

    //returns event exists
    public static boolean getExists(){
        return exists;
    }

    //set box id
    public void setBoxId(int i){
        boxid = i;
    }

    //getBox id
    public static int getBoxId(){
        return boxid;
    }


    //on click add go to event View
    @Override
    public void onClick(View view) {
        Intent i = new Intent(dayView.this, eventView.class);   //going from dayView to eventView
        startActivity(i);
    }

}
