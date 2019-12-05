package com.example.calenderapp;

import org.junit.Test;

import static org.junit.Assert.*;

public class settingTest {

    @Test
    public void getStyleTest() {
        setting.textStyle = "Italic";
        assertEquals(setting.textStyle, setting.getTextStyle());
    }

}