package com.example.snehal.cards;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.graphics.Color;
import android.media.RingtoneManager;
import android.net.Uri;
import android.support.v4.app.NotificationCompat;

//public class NotificationUtil extends ContextWrapper  {

public class NotificationUtil extends ContextWrapper {


    private NotificationManager mManager;
    public static final String ANDROID_CHANNEL_ID = "com.example.snehal.cards.ANDROID";
    public static final String IOS_CHANNEL_ID = "com.example.snehal.cards.IOS";
    public static final String ANDROID_CHANNEL_NAME = "ANDROID CHANNEL";
    public static final String IOS_CHANNEL_NAME = "IOS CHANNEL";

    public NotificationUtil(Context base) {
        super(base);
        createChannels();
    }



    public void createChannels() {

        // create android channel
        NotificationChannel androidChannel = new NotificationChannel(ANDROID_CHANNEL_ID,
                ANDROID_CHANNEL_NAME, NotificationManager.IMPORTANCE_DEFAULT);
        // Sets whether notifications posted to this channel should display notification lights
        androidChannel.enableLights(true);
        // Sets whether notification posted to this channel should vibrate.
        androidChannel.enableVibration(true);
        // Sets the notification light color for notifications posted to this channel
        androidChannel.setLightColor(Color.GREEN);
        // Sets whether notifications posted to this channel appear on the lockscreen or not
        androidChannel.setLockscreenVisibility(Notification.VISIBILITY_PRIVATE);

        getManager().createNotificationChannel(androidChannel);

        // create ios channel

    }

    public NotificationManager getManager() {
        if (mManager == null) {
            mManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        }
        return mManager;
    }



    public void onReceive(Context context, Intent intent) {
        long when=System.currentTimeMillis();

        NotificationManager notificationManager=(NotificationManager)context.getSystemService(Context.NOTIFICATION_SERVICE);


        Intent repeating_intent= new Intent(context,viewall.class);
        repeating_intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

        PendingIntent pendingIntent=PendingIntent.getActivity(context,0,repeating_intent,PendingIntent.FLAG_UPDATE_CURRENT);

        Uri alarmSound = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);


        NotificationCompat.Builder builder= new NotificationCompat.Builder(context)
                .setContentIntent(pendingIntent)
                .setSmallIcon(android.R.drawable.stat_notify_more)
                .setContentTitle("Hii farmer!!")
                .setContentText("Time for next pesticide cycle!!!").setSound(alarmSound)
                .setAutoCancel(true).setWhen(when)
                .setVibrate(new long[]{1000,1000,1000,1000,1000});

        notificationManager.notify(0,builder.build());


    }

   public Notification.Builder getAndroidChannelNotification() {
       return new Notification.Builder(getApplicationContext(), ANDROID_CHANNEL_ID)
               .setContentTitle("hey farmer")
               .setContentText("time for crops nourishment")
               .setSmallIcon(android.R.drawable.stat_notify_more)
               .setAutoCancel(true);
   }







  /*  public Notification.Builder getAndroidChannelNotification(String title, String body) {
        return new Notification.Builder(getApplicationContext(), ANDROID_CHANNEL_ID)
                .setContentTitle(title)
                .setContentText(body)
                .setSmallIcon(android.R.drawable.stat_notify_more)
                .setAutoCancel(true);
    }*/




//    public void onReceive(Context context, Intent intent) {
//        NotificationManager notificationManager=(NotificationManager)context.getSystemService(Context.NOTIFICATION_SERVICE);
//
//        Intent repeating_intent= new Intent(context,viewall.class);
//        repeating_intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//
//        PendingIntent pendingIntent=PendingIntent.getActivity(context,100,repeating_intent,PendingIntent.FLAG_UPDATE_CURRENT);
//
//
//        NotificationCompat.Builder builder= new NotificationCompat.Builder(context)
//                .setContentIntent(pendingIntent)
//                .setSmallIcon(android.R.drawable.stat_notify_more)
//                .setContentTitle("Hii farmer!!")
//                .setContentText("Time for next pesticide cycle!!!")
//                .setAutoCancel(true);
//
//        notificationManager.notify(100,builder.build());
//
//
//    }


}






