package com.example.snehal.cards;

import android.app.AlarmManager;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Calendar;

public class CropEntry extends AppCompatActivity {

    private NotificationUtil mNotificationUtils;
    DatabaseHelper myDb;
    EditText sugarcaneSowingDate,sugarcaneSowingArea;
    Button sugarcaneEnter,btnviewAll;

    private static final int NOTIFICATION_ID = 0;
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
                        sugarcaneSowingArea.setText("");
                        sugarcaneSowingDate.setText("");



                        if(isInserted ==true)
                        {
                            Toast.makeText(CropEntry.this, "Data Inserted", Toast.LENGTH_LONG).show();


                            mNotificationManager = (NotificationManager)
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
                                      createNotificationChannel();


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









