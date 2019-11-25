package com.example.setting;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Switch;
import android.widget.Toast;

public class setting extends AppCompatActivity {

    private Switch myswitch;
    RadioGroup rg;
    RadioButton rb;
    SharedPref sharedPref;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        sharedPref = new SharedPref(this);
        // Set the theme
        if(sharedPref.loadNightModeState() == true)
        {
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

    /* This method is use to choose text style */
    public void rbclick(View v)
    {
        int radiobuttonid = rg.getCheckedRadioButtonId();
        RadioButton rb = (RadioButton)findViewById(radiobuttonid);
        Toast.makeText(getBaseContext(), rb.getText(), Toast.LENGTH_LONG).show();
    }

}
