package com.example.calenderapp;

import org.junit.Test;


import static org.junit.Assert.assertTrue;

public class TimePickerFragmentTest1 {
    @Test
    public void isTimeSelected() {
        TimePickerFragment.selectedTime = true;
        assertEquals(TimePickerFragment.selectedTime, TimePickerFragment.isTimeSelected());

    }
    @Test
    public void isTimeSelected1(){
        TimePickerFragment.selectedTime = false;
        assertEquals(TimePickerFragment.selectedTime, TimePickerFragment.isTimeSelected());
    }
}
