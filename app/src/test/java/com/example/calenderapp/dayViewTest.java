package com.example.calenderapp;

import androidx.appcompat.app.AppCompatActivity;

import org.junit.Test;

import static org.junit.Assert.*;

public class dayViewTest extends AppCompatActivity{

    @Test
    public void isHelperNull() {
        dayView dv = new dayView();
        assertNull(dv.helper);
    }

    @Test
    public void isHelperNotNull(){
        dayView dv = new dayView();
        dv.helper = new myDBAdapter(this);
        assertNotNull(dv.helper);

    }

    @Test
    public void doesEventExist(){
        dayView dv = new dayView();
        dv.setExists(true);
        assertTrue(dayView.getExists());
    }
}