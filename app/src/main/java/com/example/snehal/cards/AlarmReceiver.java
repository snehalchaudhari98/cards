/*
 * Copyright (C) 2018 Google Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.snehal.cards;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;

import java.util.Random;

/**
 * Broadcast receiver for the alarm, which delivers the notification.
 */
public class AlarmReceiver extends BroadcastReceiver {

    private NotificationManager mNotificationManager;
    // Notification ID.
    private static final int NOTIFICATION_ID = 0;
    // Notification channel ID.
    private static final String PRIMARY_CHANNEL_ID =
            "primary_notification_channel";



    @Override
    public void onReceive(Context context, Intent intent) {
        mNotificationManager = (NotificationManager)
                context.getSystemService(Context.NOTIFICATION_SERVICE);

        Bundle bundle= intent.getExtras();
        String msg=bundle.getString("crop","something");
        // Deliver the notification.
        deliverNotification(context,msg);
    }



    private void deliverNotification(Context context ,String aa) {
        // Create the content intent for the notification, which launches
        // this activity
        Intent contentIntent = new Intent(context, MainActivity.class);
        PendingIntent contentPendingIntent = PendingIntent.getActivity
                (context, NOTIFICATION_ID, contentIntent, PendingIntent
                        .FLAG_UPDATE_CURRENT);

        //Intent broadcastIntent = new Intent(context, ActionNotification.class);
      //  broadcastIntent.putExtra("toastmsg","Thanks for Checking notification");
//        PendingIntent broadcastPendingIntent = PendingIntent.getActivity
//                (context, NOTIFICATION_ID, broadcastIntent, PendingIntent
//                        .FLAG_UPDATE_CURRENT);


        //SHOW BUTTON
        Intent showintent=new Intent(context,viewall.class);
        showintent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_CLEAR_TASK);
        PendingIntent showPendingIntent=PendingIntent.getActivity(context,NOTIFICATION_ID,showintent,PendingIntent.FLAG_ONE_SHOT);

        //DONE BUTTON
        Intent doneintent=new Intent(context,CancelAlarm.class);
        doneintent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_CLEAR_TASK);
        PendingIntent donePendingIntent=PendingIntent.getActivity(context,NOTIFICATION_ID,showintent,PendingIntent.FLAG_ONE_SHOT);


        // Build the notification

        String[] quotes={"Time for pesticide cycle","Get up and go to field"};

        String msg= quotes[new Random().nextInt(quotes.length)];

        NotificationCompat.Builder builder = new NotificationCompat.Builder
                (context, PRIMARY_CHANNEL_ID)
                .setSmallIcon(R.drawable.ic_launcher_background)
                .setContentTitle("Heyy Buddy !!!")
                .setContentText(aa)
                .setContentIntent(contentPendingIntent)
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .addAction(R.drawable.ic_action_name,"Done",donePendingIntent)
                .addAction(R.drawable.ic_action_name,"Show",showPendingIntent)
                .setAutoCancel(true)
                .setDefaults(NotificationCompat.DEFAULT_ALL);

        // Deliver the notification
        mNotificationManager.notify(NOTIFICATION_ID, builder.build());
    }
}
