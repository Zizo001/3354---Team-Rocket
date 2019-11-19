package com.example.calenderapp;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;

public class eventView extends AppCompatActivity{
    Button addEvent;
    private EditText eventText;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.event_view);

        eventText = (EditText) findViewById(R.id.eventText);

        addEvent = (Button) findViewById(R.id.addBut);
        addEvent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MainActivity.event = eventText.getText().toString();
                dayView.InsertDatabase(view);
                Intent i = new Intent(eventView.this, dayView.class);
                startActivity(i);
            }
        });

    }

}
