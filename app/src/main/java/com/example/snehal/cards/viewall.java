package com.example.snehal.cards;

import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;






public class viewall extends AppCompatActivity {


    Cursor c=null;
    Button show;
    DatabaseHelper database;
    RecyclerView recyclerView;
    RecycleAdapter recycler;
    List<DataModel> requiredData;
    List<DataModel> datamodel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_viewall);

        show = (Button) findViewById(R.id.view);
        datamodel =new ArrayList<DataModel>();
        requiredData =new ArrayList<DataModel>();
        recyclerView = (RecyclerView) findViewById(R.id.recycle);




        show.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                database = new DatabaseHelper(viewall.this);
                datamodel=  database.getdata();
                Log.d("size of fetched data", String.valueOf(datamodel.size()));


              //  Calendar p3 = Calendar.getInstance();
                Calendar p2 = Calendar.getInstance();
                Calendar p1 =Calendar.getInstance();
                SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

                try {
                  p1.setTime(sdf.parse(String.valueOf(Calendar.getInstance().getTime())));
//                    p3.set(Calendar.HOUR_OF_DAY,0);
//                    p3.set(Calendar.MINUTE,0);
//                    p3.set(Calendar.SECOND,0);
//                    p1.setTime(sdf.parse(String.valueOf(p3)));
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                Log.i("today", "" + p1);

                long noOfDays=0;
              //  Log.i("Aray list",datamodel.toString());

                for(DataModel i:datamodel){
                    Log.i("in list 99", "" + i.getSuargardate());

                    try {
                        p2.setTime(sdf.parse(i.getSuargardate()));
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }

                    long m1=p1.getTimeInMillis();
                    long m2=p2.getTimeInMillis();

                    noOfDays=(m1-m2)/(24 * 60 * 60000);
                    Log.d("Date difference", String.valueOf(noOfDays));



                    DataBaseHelper2 myDbHelper = new DataBaseHelper2(viewall.this);
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
                            Log.i("LISTDATA", "_id: " + c.getString(0) + "\n" +
                                    "crop " + c.getString(1) + "\n" );

                            if(noOfDays < c.getInt(0)){
                                requiredData.add(i);
                                break;
                            }


                        } while (c.moveToNext());


                    }
                }





               /* Calendar p = Calendar.getInstance();
                SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

                try {
                    p.setTime(sdf.parse(String.valueOf(Calendar.getInstance().getTime())));
                } catch (ParseException e) {
                    e.printStackTrace();
                }


                long noOfDays=0;
                for(DataModel i:datamodel){
                    Log.i("in list", "" + i.getSuargardate());
                    long m1=p.getTimeInMillis();
                    Calendar m2=Calendar.getInstance();

                    try {
                        m2.setTime(sdf.parse(i.getSuargardate()));
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }

                    long m3=m2.getTimeInMillis();

                    noOfDays=( m1 - m3)/(24 * 60 * 60000);

                    Log.i("date difference", "" +noOfDays);


                }



                DataBaseHelper2 myDbHelper = new DataBaseHelper2(viewall.this);
                try {
                    myDbHelper.createDataBase();
                } catch (IOException ioe) {
                    throw new Error("Unable to create database");
                }
                myDbHelper.openDataBase();
                Toast.makeText(viewall.this, "Successfully Imported", Toast.LENGTH_SHORT).show();
                c = myDbHelper.query("SugarCane", null, null, null, null, null, null);
                if (c.moveToFirst()) {
                    do {
                        Log.i("LISTDATA", "_id: " + c.getString(0) + "\n" +
                                "crop " + c.getString(1) + "\n" );
                    } while (c.moveToNext());
                }
                */



                recycler =new RecycleAdapter(requiredData);


                RecyclerView.LayoutManager reLayoutManager =new LinearLayoutManager(getApplicationContext());
                recyclerView.setLayoutManager(reLayoutManager);
                recyclerView.setItemAnimator(new DefaultItemAnimator());
                recyclerView.setAdapter(recycler);


            }
        });



    }
}




//
//
//public class viewall extends AppCompatActivity {
//
//    Button show;
//    DatabaseHelper database;
//    RecyclerView recyclerView;
//    RecycleAdapter recycler;
//    List<DataModel> datamodel;
//
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_viewall);
//
//        show = (Button) findViewById(R.id.view);
//        datamodel =new ArrayList<DataModel>();
//        recyclerView = (RecyclerView) findViewById(R.id.recycle);
//
//
//
//        show.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                database = new DatabaseHelper(viewall.this);
//                datamodel=  database.getdata();
//                recycler =new RecycleAdapter(datamodel);
//
//
//                Log.i("HIteshdata",""+datamodel);
//                RecyclerView.LayoutManager reLayoutManager =new LinearLayoutManager(getApplicationContext());
//                recyclerView.setLayoutManager(reLayoutManager);
//                recyclerView.setItemAnimator(new DefaultItemAnimator());
//                recyclerView.setAdapter(recycler);
//
//
//            }
//        });
//
//
//
//    }
//}
