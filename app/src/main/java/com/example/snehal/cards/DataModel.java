package com.example.snehal.cards;

import android.database.Cursor;
import android.util.Log;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class DataModel {

    public String suagarArea;
    public String suargardate;
    public String deadlinedate;

    public void setDeadlinedate(int addDeadL) throws ParseException {
        Cursor p=null;

        String oldDate = getSuargardate();



        //Specifying date format that matches the given date
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

        Calendar c = Calendar.getInstance();
        //c.setTime(sdf.parse(oldDate));
        try{
            //Setting the date to the given date

            c.setTime(sdf.parse(String.valueOf(Calendar.getInstance())));
            System.out.println(c.getTime());

        }catch(ParseException e){
            e.printStackTrace();
        }

        Log.d("In setdeadline date:", String.valueOf(c));
        Log.d("In setdeadline ADD", String.valueOf(addDeadL));

        //Number of Days to add
        c.add(Calendar.DAY_OF_MONTH, addDeadL);

        //Date after adding the days to the given date
        deadlinedate = sdf.format(c.getTime());
//        return deadlinedate1;
        //Displaying the new Date after addition of Daysreturn deadlinedate;
    }


//    public String getDeadlinedate() throws ParseException {
//        Cursor p=null;
//
//        String oldDate = getSuargardate();
//        System.out.println(oldDate);
//        //Specifying date format that matches the given date
//        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
//
//        Calendar c = Calendar.getInstance();
//        //c.setTime(sdf.parse(oldDate));
//        try{
//            //Setting the date to the given date
//
//            c.setTime(sdf.parse(oldDate));
//            System.out.println(c.getTime());
//
//        }catch(ParseException e){
//            e.printStackTrace();
//        }
//
//
//        //Number of Days to add
//        c.add(Calendar.DAY_OF_MONTH, 30);
//
//        //Date after adding the days to the given date
//        String deadlinedate = sdf.format(c.getTime());
//        return deadlinedate;
//        //Displaying the new Date after addition of Daysreturn deadlinedate;
//    }



    /*public void setDeadlinedate(String deadlinedate) {
        this.deadlinedate = deadlinedate;
    }*/


    public String getDeadlinedate() {
        return deadlinedate ;
    }


    public String getSuagarArea() {
        return suagarArea;
    }

    public void setSuagarArea(String suagarArea) {
        this.suagarArea = suagarArea;
    }

    public String getSuargardate() {
        return suargardate;
    }

    public void setSuargardate(String suargardate) {
        this.suargardate = suargardate;
    }
}
