/**
 * @author: Ramin Nourbakhsh & Akshar Patel
 * this class allows for the notifications of the app to work
 */
package com.example.calenderapp;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build;

import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

public class AlarmReceiver extends BroadcastReceiver {
    //this method will be invoked when the time on an event occurs
    @Override
    public void onReceive(Context context, Intent intent){
        NotificationCompat.Builder builder = new NotificationCompat.Builder(context, "myCalendar")
                .setSmallIcon(R.mipmap.ic_launcher_round)
                .setContentTitle("Calendar App")
                .setContentText("Event Reminder!")
                .setAutoCancel(true)
                .setPriority(NotificationCompat.PRIORITY_DEFAULT);

        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(context);

        notificationManager.notify(154, builder.build());   //building the notification with the info
    }
}
