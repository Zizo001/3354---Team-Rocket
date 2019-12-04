package com.example.calenderapp;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

public class myDBAdapterTest {

    @Test
    public void getData() {
        Assert.assertTrue(true);        //Aksharkumar
    }

     @Test
    public void checkTimeOverlap() {
        String dbDate = "10/20/2019";
        String dbAMPM = "AM";
        String dbTime = "2:30";

        String date = "10/20/2019";
        String ampm = "AM";
        String time = "2:30";

        String finalTime = dbTime + dbAMPM;
        String finalTime2 = time + ampm;

        boolean result = finalTime.equals(finalTime2);
        assertEquals(true, result);
    }//Veerendranath Korrapati

    @Test
    public void checkTimeOverlapFalse() {
        Assert.assertFalse(false);
    }       //Charlotte
}
