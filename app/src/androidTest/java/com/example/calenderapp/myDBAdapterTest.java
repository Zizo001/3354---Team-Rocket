package com.example.calenderapp;

import org.junit.Assert;
import org.junit.Test;
import java.util.ArrayList;
import java.util.List;
import static org.junit.Assert.*;

public class myDBAdapterTest {

    @Test
    public void getData() {
        List<String> testList = new ArrayList();
        testList.add("String 1");
        String s = "String 1";
        List<String> list = new ArrayList<>();
        list.add(s);
        assertEquals(list, testList );        //Aksharkumar
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
        String dbDate = "10/20/2019";
        String dbAMPM = "PM";
        String dbTime = "2:30";

        String date = "10/20/2019";
        String ampm = "AM";
        String time = "2:30";
        
        String finalTime = dbTime + dbAMPM;
        String finalTime2 = time + ampm;

        boolean result = finalTime.equals(finalTime2);
        assertEquals(false, result);

    }       //Charlotte
}
