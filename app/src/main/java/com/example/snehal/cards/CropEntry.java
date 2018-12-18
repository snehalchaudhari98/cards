package com.example.snehal.cards;

import android.app.AlarmManager;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import static android.app.PendingIntent.FLAG_UPDATE_CURRENT;

public class CropEntry extends AppCompatActivity {
    Cursor c=null;
    private NotificationUtil mNotificationUtils;
    DatabaseHelper myDb;
    EditText sugarcaneSowingDate,sugarcaneSowingArea;
    Button sugarcaneEnter,btnviewAll;

    private static int REQUEST=1;

     static final int NOTIFICATION_ID = 0;
    // Notification channel ID.
    private static final String PRIMARY_CHANNEL_ID =
            "primary_notification_channel";
    private NotificationManager mNotificationManager;

    private DatePickerDialog.OnDateSetListener mDateSetListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crop_entry);
        myDb = new DatabaseHelper(this);

        sugarcaneSowingDate = (EditText)findViewById(R.id.sugarcanesowingdate);
        sugarcaneSowingArea = (EditText)findViewById(R.id.suagarcanesowingarea);
        sugarcaneEnter = (Button)findViewById(R.id.sugarcanesowingenter);
        btnviewAll = (Button) findViewById(R.id.button_viewAll);


        /*BottomNavigationView bottomNavigationView=(BottomNavigationView)findViewById(R.id.bottom_nevigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()){
                    case R.id.action_add:
                        //Toast.makeText(ScrollingActivity.this,"Added!! Yeppie",Toast.LENGTH_SHORT).show();
                        Intent intent=new Intent(CropEntry.this,gridview.class);
                        startActivity(intent);
                        break;
                    case R.id.action_chart:
                        //Toast.makeText(ScrollingActivity.this,"Showing all Details here!!",Toast.LENGTH_SHORT).show();
                        Intent intent1=new Intent(CropEntry.this,MainActivity.class);
                        startActivity(intent1);
                        break;
                    case R.id.action_profile:
                        //Toast.makeText(ScrollingActivity.this,"Your Profile",Toast.LENGTH_SHORT).show();
                        Intent intent2=new Intent(CropEntry.this,MainActivity.class);
                        startActivity(intent2);
                        break;
                  /*  case R.id.action_help:
                        Toast.makeText(ScrollingActivity.this,"Google it ",Toast.LENGTH_SHORT).show();
                        break;*/
/*
                }
                return true;
            }
        });*/

        AddData();
        viewAll();
        sugarcaneSowingDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar c=Calendar.getInstance();
                int month=c.get(Calendar.MONTH);
                int day=c.get(Calendar.DAY_OF_MONTH);
                int year=c.get(Calendar.YEAR);

                DatePickerDialog dialog=new DatePickerDialog(CropEntry.this,android.R.style.Theme_Holo_Light_Dialog_MinWidth,mDateSetListener,year,month,day);
                dialog.getDatePicker().setMaxDate(System.currentTimeMillis()+1000);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();

            }
        });

        mDateSetListener=new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                month=month+1;
                String date=dayOfMonth+"/"+month+"/"+year;
                sugarcaneSowingDate.setText(date);
            }
        };


   }





    public  void AddData() {
        sugarcaneEnter.setOnClickListener(
                new View.OnClickListener() {
                    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
                    @Override
                    public void onClick(View v) {

                        /*Calendar calendar =Calendar.getInstance();
                        calendar.set(Calendar.HOUR_OF_DAY,19);
                        calendar.set(Calendar.MINUTE,24);
                        calendar.set(Calendar.SECOND,30);


                        Intent intent=new Intent(getApplicationContext(),Notification_receiver.class);
                        PendingIntent pendingIntent=PendingIntent.getBroadcast(getApplicationContext(),100,intent,PendingIntent.FLAG_UPDATE_CURRENT);

                        AlarmManager alarmManager=(AlarmManager)getSystemService(ALARM_SERVICE);
                        alarmManager.setRepeating(AlarmManager.RTC_WAKEUP,calendar.getTimeInMillis(),AlarmManager.INTERVAL_DAY,pendingIntent);*/




                         boolean isInserted = myDb.insertData(sugarcaneSowingDate.getText().toString(),sugarcaneSowingArea.getText().toString());
                       String sowDate=sugarcaneSowingDate.getText().toString();
                        sugarcaneSowingArea.setText("");
                        sugarcaneSowingDate.setText("");



                        if(isInserted ==true)
                        {
                            Toast.makeText(CropEntry.this, "Data Inserted", Toast.LENGTH_LONG).show();

                            Calendar p2 = Calendar.getInstance();
                            Calendar p1 =Calendar.getInstance();
                            long noOfDays=0;
                            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

                            try {
                                p1.setTime(sdf.parse(String.valueOf(new Date())));
                            } catch (ParseException e) {
                                e.printStackTrace();
                            }


                            try {
                                p2.setTime(sdf.parse(sowDate));

                            } catch (ParseException e) {
                                e.printStackTrace();
                            }




                            int addDeadline=0;
                            long m1=p1.getTimeInMillis();
                            long m2=p2.getTimeInMillis();
                            Date resultDate1=new Date(m1);
                            Date resultDate2=new Date(m2);


                            Log.d("Today's DATE ::", sdf.format(resultDate1));

                            Log.d("Sowing Date's DATE ::", sdf.format(resultDate2));

                            noOfDays=(m1-m2)/(24 * 60 * 60000);
                            Log.d("Date difference", String.valueOf(noOfDays));



                            DataBaseHelper2 myDbHelper = new DataBaseHelper2(CropEntry.this);
                            try {
                                myDbHelper.createDataBase();
                            } catch (IOException ioe) {
                                throw new Error("Unable to create database");
                            }
                            myDbHelper.openDataBase();
//                    Toast.makeText(viewall.this, "Successfully Imported", Toast.LENGTH_SHORT).show();

                            c = myDbHelper.query("SugarCane", null, null, null, null, null, null);
                            if (c.moveToFirst()) {
                                do {

                                    Log.i("LISTDATA", "Diff" + c.getString(0) + "\n" +
                                            "no_of_days_real " + noOfDays + "\n" );

                                    if(noOfDays > c.getInt(0)) {
                                        continue;

                                    }else{

                                        addDeadline=  c.getInt(0) - (int)(noOfDays-4);

                                        break;
                                    }


                                } while (c.moveToNext());


                            }

                            callAlarm(addDeadline, String.valueOf(sdf.format(resultDate2)));


                           /* mNotificationManager = (NotificationManager)
                                    getSystemService(NOTIFICATION_SERVICE);

                            //ToggleButton alarmToggle = findViewById(R.id.alarmToggle);

                            // Set up the Notification Broadcast Intent.
                            Intent notifyIntent = new Intent(CropEntry.this, AlarmReceiver.class);

                            boolean alarmUp = (PendingIntent.getBroadcast(CropEntry.this, NOTIFICATION_ID,
                                    notifyIntent, PendingIntent.FLAG_NO_CREATE) != null);

                            //alarmToggle.setChecked(alarmUp);

                            final PendingIntent notifyPendingIntent = PendingIntent.getBroadcast
                                    (CropEntry.this, NOTIFICATION_ID, notifyIntent,
                                            PendingIntent.FLAG_UPDATE_CURRENT);

                            final AlarmManager alarmManager = (AlarmManager) getSystemService
                                    (ALARM_SERVICE);






                            // Set the click listener for the toggle button.
                                               // long repeatInterval = AlarmManager.INTERVAL_FIFTEEN_MINUTES;
                                                long repeatInterval = 60*1000*4;

                                                long triggerTime = SystemClock.elapsedRealtime()
                                                        + repeatInterval;

                                                // If the Toggle is turned on, set the repeating alarm with
                                                // a 15 minute interval.
                                                if (alarmManager != null) {
                                                    alarmManager.setInexactRepeating
                                                            (AlarmManager.ELAPSED_REALTIME_WAKEUP,
                                                                    triggerTime, repeatInterval,
                                                                    notifyPendingIntent);
                                                }
                                      createNotificationChannel();*/


                            /*Cursor res = myDb.getAllData();
                            if (res.getCount() == 0) {
                                // show message
                                showMessage("Error", "Nothing found");
                                return;
                            }

                            StringBuffer buffer = new StringBuffer();
                            while (res.moveToNext()) {
                                buffer.append("SugarcaneDate :" + res.getString(0) + "\n");
                                buffer.append("SugarcaneArea :" + res.getString(1) + "\n\n\n");
                            }

                            // Show all data
                            showMessage("Data", buffer.toString());

                            Calendar calendar =Calendar.getInstance();
                            calendar.set(Calendar.HOUR_OF_DAY,1);
                            calendar.set(Calendar.MINUTE,10);
                            calendar.set(Calendar.SECOND,30);


                            Intent intent=new Intent(getApplicationContext(),Notification_receiver.class);
                            PendingIntent pendingIntent=PendingIntent.getBroadcast(getApplicationContext(),100,intent,PendingIntent.FLAG_UPDATE_CURRENT);

                            AlarmManager alarmManager=(AlarmManager)getSystemService(ALARM_SERVICE);
                            alarmManager.setRepeating(AlarmManager.RTC_WAKEUP,calendar.getTimeInMillis(),AlarmManager.INTERVAL_DAY,pendingIntent);*/



//WORKING CODE FOR MOTIFICATION
/*
                           mNotificationUtils = new NotificationUtil(getApplicationContext());

                                        Notification.Builder nb = mNotificationUtils.
                                                getAndroidChannelNotification("HIIII Farmer", "Time for next dose");

                                        mNotificationUtils.getManager().notify(101, nb.build());

                                        */




/*
                            Calendar calendar =Calendar.getInstance();
                            calendar.set(Calendar.HOUR_OF_DAY,11);
                            calendar.set(Calendar.MINUTE,55);
                            calendar.set(Calendar.SECOND,10);


                            Intent intent = new Intent(getApplicationContext(),Notification_receiver.class);
                            PendingIntent pendingIntent=PendingIntent.getBroadcast(getApplicationContext(),0,intent,PendingIntent.FLAG_UPDATE_CURRENT);
                            AlarmManager am=(AlarmManager) getApplicationContext().getSystemService(Context.ALARM_SERVICE);
                            am.setRepeating(AlarmManager.RTC_WAKEUP,calendar.getTimeInMillis(),AlarmManager.INTERVAL_FIFTEEN_MINUTES,pendingIntent);

*/
                        }
                        else {
                            Toast.makeText(CropEntry.this, "Oopps!!Data not Inserted", Toast.LENGTH_LONG).show();
                        }

                    }
                }
        );
    }

    private void callAlarm(int addDeadline, String aa) {
        mNotificationManager = (NotificationManager)
                getSystemService(NOTIFICATION_SERVICE);

        //ToggleButton alarmToggle = findViewById(R.id.alarmToggle);

        // Set up the Notification Broadcast Intent.
        Intent notifyIntent = new Intent(CropEntry.this, AlarmReceiver.class);
        notifyIntent.putExtra("crop","For sugarcane with sowing date : "+aa);


        boolean alarmUp = (PendingIntent.getBroadcast(CropEntry.this, NOTIFICATION_ID,
                notifyIntent, PendingIntent.FLAG_NO_CREATE) != null);

        //alarmToggle.setChecked(alarmUp);

         PendingIntent notifyPendingIntent = PendingIntent.getBroadcast
                (CropEntry.this, NOTIFICATION_ID, notifyIntent,
                        FLAG_UPDATE_CURRENT);


        //for cancelling alarm
      /*  Intent cancellationIntent=new Intent(CropEntry.this,CancelAlarm.class);
        cancellationIntent.putExtra("key",notifyPendingIntent);

        final PendingIntent cancellationPendingIntent = PendingIntent.getBroadcast
                (CropEntry.this, NOTIFICATION_ID, cancellationIntent,
                        FLAG_UPDATE_CURRENT);*/


        final AlarmManager alarmManager = (AlarmManager) getSystemService
                (ALARM_SERVICE);


        // Set the click listener for the toggle button.
        // long repeatInterval = AlarmManager.INTERVAL_FIFTEEN_MINUTES;
        long repeatInterval = 60*1000*2;
       // long repeatInterval = AlarmManager.INTERVAL_DAY;

        long triggerTime = (SystemClock.elapsedRealtime() - (addDeadline*24*60*60*1000))
                + repeatInterval;

       // long cancelTime = (SystemClock.elapsedRealtime() + ((addDeadline+4)*24*60*60*1000));

        // If the Toggle is turned on, set the repeating alarm with
        // a 15 minute interval.
        if (alarmManager != null) {
            alarmManager.setInexactRepeating
                    (AlarmManager.ELAPSED_REALTIME_WAKEUP,
                            triggerTime, repeatInterval,
                            notifyPendingIntent);


            triggerTime = (SystemClock.elapsedRealtime() + ((addDeadline+4)*24*60*60*1000));
            notifyPendingIntent=PendingIntent.getService(CropEntry.this,NOTIFICATION_ID, notifyIntent,
                    0);
            AlarmManager alarmManagerstop=(AlarmManager)getSystemService(ALARM_SERVICE);
            alarmManagerstop.cancel(notifyPendingIntent);
           }

        createNotificationChannel();

    }


    public void createNotificationChannel() {

        // Create a notification manager object.
        mNotificationManager =
                (NotificationManager) getSystemService(NOTIFICATION_SERVICE);

        // Notification channels are only available in OREO and higher.
        // So, add a check on SDK version.
        if (android.os.Build.VERSION.SDK_INT >=
                android.os.Build.VERSION_CODES.O) {

            // Create the NotificationChannel with all the parameters.
            NotificationChannel notificationChannel = new NotificationChannel
                    (PRIMARY_CHANNEL_ID,
                            "Reminder notification",
                            NotificationManager.IMPORTANCE_HIGH);

            notificationChannel.enableLights(true);
            notificationChannel.setLightColor(Color.RED);
            notificationChannel.enableVibration(true);
            notificationChannel.setDescription("Notifies before deadline" +
                    "Time for next pesticide cycle");
            mNotificationManager.createNotificationChannel(notificationChannel);
        }
    }

    public void viewAll() {
        btnviewAll.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        /*Cursor res = myDb.getAllData();
                        if(res.getCount() == 0) {
                            // show message
                            showMessage("Error","Nothing found");
                            return;
                        }

                        StringBuffer buffer = new StringBuffer();
                        while (res.moveToNext()) {
                            buffer.append("SugarcaneDate :"+ res.getString(0)+"\n");
                            buffer.append("SugarcaneArea :"+ res.getString(1)+"\n\n\n");
                             }

                        // Show all data
                        showMessage("Data",buffer.toString());*/
                        Intent intent = new Intent(CropEntry.this,viewall.class);
                        startActivity(intent);
                    }
                }
        );
    }

    public void showMessage(String title,String Message){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(Message);
        builder.show();
    }


    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
    }
}









