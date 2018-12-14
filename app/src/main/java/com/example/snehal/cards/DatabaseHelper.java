package com.example.snehal.cards;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "Cropsinfo.db";
    public static final String TABLE_NAME = "Suagrcane_table";
    public static final String COL_1 = "SugarcaneDate";
    public static final String COL_2 = "SugarcaneArea";


    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + TABLE_NAME +" (SugarcaneDate DATE PRIMARY KEY ,SugarcaneArea INTEGER)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
        onCreate(db);
    }

    public boolean insertData(String Sugarcanesowingdate, String sugarcanesowingarea) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_1,Sugarcanesowingdate);
        contentValues.put(COL_2,sugarcanesowingarea);

        long result = db.insert(TABLE_NAME,null ,contentValues);
        if(result == -1)
            return false;
        else
            return true;
    }


    public List<DataModel> getdata() {
        // DataModel dataModel = new DataModel();
        List<DataModel> data = new ArrayList<>();
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from " + TABLE_NAME + " ;", null);
        StringBuffer stringBuffer = new StringBuffer();
        DataModel dataModel = null;
        while (cursor.moveToNext()) {
            dataModel = new DataModel();
            String SDate = cursor.getString(cursor.getColumnIndexOrThrow("SugarcaneDate"));
            String SArea = cursor.getString(cursor.getColumnIndexOrThrow("SugarcaneArea"));
            dataModel.setSuagarArea(SArea);
            dataModel.setSuargardate(SDate);
            stringBuffer.append(dataModel);
            // stringBuffer.append(dataModel);
            data.add(dataModel);
        }

        for (DataModel mo : data) {

            Log.i("Hellomo", "" + mo.getSuargardate());
        }

        //

        return data;
    }

    public Cursor getAllData() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from " + TABLE_NAME, null);
        return res;
    }
}

