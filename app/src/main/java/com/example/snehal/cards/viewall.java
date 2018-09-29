package com.example.snehal.cards;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;
import java.util.List;

public class viewall extends AppCompatActivity {

    Button show;
    DatabaseHelper database;
    RecyclerView recyclerView;
    RecycleAdapter recycler;
    List<DataModel> datamodel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_viewall);

        show = (Button) findViewById(R.id.view);
        datamodel =new ArrayList<DataModel>();
        recyclerView = (RecyclerView) findViewById(R.id.recycle);



        show.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                database = new DatabaseHelper(viewall.this);
                datamodel=  database.getdata();
                recycler =new RecycleAdapter(datamodel);


                Log.i("HIteshdata",""+datamodel);
                RecyclerView.LayoutManager reLayoutManager =new LinearLayoutManager(getApplicationContext());
                recyclerView.setLayoutManager(reLayoutManager);
                recyclerView.setItemAnimator(new DefaultItemAnimator());
                recyclerView.setAdapter(recycler);


            }
        });



    }
}
