/**
 * This is the Database helper class. This class uses SQLlite
 * This class is used to manipulate the events
 * @Author: Akshar Patel
 * @Documentor: Ramin Nourbakhsh
 */
package com.example.calenderapp;

import android.app.TimePickerDialog;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.room.util.TableInfo;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class myDBAdapter extends AppCompatActivity {
    myDbHelper myhelper;
    int uid; //integer id given to the event
    List<Integer> ids = new ArrayList<>();//ArrayList to hold the event ids

    public myDBAdapter(Context context) {
        myhelper = new myDbHelper(context);
    }

    /**
     * this method inserts data into the database
     * @return returns the id of the new event
     */
    public long insertData() {
        SQLiteDatabase db = myhelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(myDbHelper.DATE, MainActivity.getSelectedDate());
        contentValues.put(myDbHelper.EVENT, MainActivity.event);
        contentValues.put(myDbHelper.TIME, TimePickerFragment.getTime());
        contentValues.put(myDbHelper.AMPM, TimePickerFragment.getAmPm());

        long id = db.insert(myDbHelper.TABLE_NAME, null, contentValues);
        return id;
    }

    /**
     * this method checks if two events are schedule for the same time on the same day
     * @param t time as a string
     * @param a am or pm
     * @return true if two events are scheduled for the same time else return false
     */
    public boolean checkTimeOverlap(String t, String a){
        SQLiteDatabase db = myhelper.getWritableDatabase();
        String[] columns = {myDbHelper.UID, myDbHelper.TIME, myDbHelper.AMPM, myDbHelper.DATE, myDbHelper.EVENT};
        Cursor cursor = db.query(myDbHelper.TABLE_NAME, columns, null, null, null, null, null);

        while (cursor.moveToNext()) {
            String tie = cursor.getString(cursor.getColumnIndex(myDbHelper.TIME));
            String ap = cursor.getString(cursor.getColumnIndex(myDbHelper.AMPM));
            String tm = t+a;
            String time = tie + ap;
            if(time.equals(tm)) {
                return true;
            }
        }
        return false;
    }

    /**
     *Reads the data in the database and returns it as a list of strings
     * @param date date of the event in a string format
     * @return the list of strings
     */
    public List<String> getData(String date) {
        SQLiteDatabase db = myhelper.getWritableDatabase();
        String[] columns = {myDbHelper.UID, myDbHelper.TIME, myDbHelper.DATE, myDbHelper.AMPM, myDbHelper.EVENT};
        Cursor cursor = db.query(myDbHelper.TABLE_NAME, columns, null, null, null, null, myDbHelper.AMPM + " ASC, " + myDbHelper.TIME +" ASC");
        List<String> events = new ArrayList();

        while (cursor.moveToNext()) {
            int cid = cursor.getInt(cursor.getColumnIndex(myDbHelper.UID));
            String tie = cursor.getString(cursor.getColumnIndex(myDbHelper.TIME));
            String dat = cursor.getString(cursor.getColumnIndex(myDbHelper.DATE));
            String event = cursor.getString(cursor.getColumnIndex(myDbHelper.EVENT));
            String ap = cursor.getString(cursor.getColumnIndex(myDbHelper.AMPM));

            if(dat.equals(date)) {
                events.add(tie + ap + "     " + event);
                ids.add(cid);
            }
        }
        return events;
    }

    public List<Integer> getIds(){
        return ids;
    }

    /**
     * update an event
     * @param id id given to the event by the database
     * @param e event
     * @param t time
     * @param a am or pm
     */
    public void updateEvent(int id, String e, String t, String a){
        SQLiteDatabase db = myhelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(myDbHelper.EVENT, e);
        contentValues.put(myDbHelper.TIME, t);
        contentValues.put(myDbHelper.AMPM, a);
        String[] whereArgs = {String.valueOf(id)};
        db.update(myDbHelper.TABLE_NAME, contentValues, myDbHelper.UID+" = ?", whereArgs);
        getData(MainActivity.getSelectedDate());
    }

    /**
     * deletes an event based on it's id
     * @param id id given to event by database
     */
    public void delete(int id){
        SQLiteDatabase db = myhelper.getWritableDatabase();
        String[] whereArgs = {String.valueOf(id)};
        db.delete(myDbHelper.TABLE_NAME, myDbHelper.UID+" = ?", whereArgs);
    }


    static class myDbHelper extends SQLiteOpenHelper {
        private static final String DATABASE_NAME = "myDatabase";
        private static final String TABLE_NAME = "myTable";
        private static final int DATABASE_Version = 5;
        private static final String UID = "_id";
        private static final String EVENT = "event";
        private static final String DATE = "date";
        private static final String TIME = "tie";
        private static final String AMPM= "ampm";

        private static final String CREATE_TABLE = "CREATE TABLE " + TABLE_NAME + " (" + UID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + TIME + " VARCHAR(255) ,"+AMPM + " VARCHAR(255)," + DATE + " VARCHAR(255) ," + EVENT + " VARCHAR(255));";

        private static final String DROP_TABLE = "DROP TABLE IF EXISTS " + TABLE_NAME;
        private Context context;

        public myDbHelper(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_Version);
            this.context = context;
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            try {
                db.execSQL(CREATE_TABLE);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            try {
                db.execSQL(DROP_TABLE);
                onCreate(db);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }
}
