package com.example.calenderapp;

import android.provider.CalendarContract;

import androidx.appcompat.app.AppCompatActivity;

import org.junit.Test;

import java.sql.Time;

import static org.junit.Assert.*;

public class myDBAdapterTest extends AppCompatActivity {

    @Test
    public void getIds(){
        myDBAdapter helper = new myDBAdapter(this);
        assertEquals(helper.getIds(), helper.ids);
    }

    @Test
    public void getAlaramIds() {
        myDBAdapter.setAlaramId(15);
        assertEquals(15, myDBAdapter.getAlaramId());
    }


    @Test
    public void setRemoveId() {
        myDBAdapter.setRemoveId(10);
        assertEquals(10, myDBAdapter.getRemoveId());
    }

}
