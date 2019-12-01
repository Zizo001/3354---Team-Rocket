package com.example.calenderapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Switch;
import android.widget.Toast;

public class setting extends AppCompatActivity implements View.OnClickListener {

    private Switch myswitch;
    public static boolean switchStat;
    RadioGroup rg;
    RadioButton rb;
    static String textStyle = "Regular";

    SharedPref sharedPref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        sharedPref = new SharedPref(this);
        Button goBack = (Button)findViewById(R.id.goback);

        // Set the theme
        if(sharedPref.loadNightModeState() ==true)
        {
            switchStat = true;
            setTheme(R.style.DarkTheme);
        }
        else setTheme(R.style.AppTheme);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.setting_view);

        // Set the view
        rg = (RadioGroup)findViewById(R.id.rgroup);

        // Activate switch
        myswitch = (Switch)findViewById(R.id.myswitch);

        if(sharedPref.loadNightModeState() == true)
        {
            switchStat = true;
            myswitch.setChecked(true);
        }
        myswitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked)
                {
                    sharedPref.setNightModeState(true);
                    restartApp();
                }
                else {
                    sharedPref.setNightModeState(false);
                    restartApp();
                }
            }
        });

    }

    /* This method restart the app after changing theme */
    public void restartApp(){
        Intent i = new Intent(getApplicationContext(), setting.class);
        startActivity(i);
        finish();
    }

    /* This method activate radio button to choose text style */
    public void rbclick(View v)
    {
        int radiobuttonid = rg.getCheckedRadioButtonId();
        RadioButton rb = (RadioButton)findViewById(radiobuttonid);
        textStyle = rb.getText().toString();
        Toast toast = Toast.makeText(getBaseContext(), rb.getText(), Toast.LENGTH_LONG);
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.show();
    }

    public static String getTextStyle(){
        return textStyle;
    }

    @Override
    public void onClick(View v) {
        Intent i = new Intent(setting.this, MainActivity.class);
        startActivity(i);
    }
}
