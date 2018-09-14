package com.example.snehal.cards;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
RecyclerView recyclerView;
productadapter adapter;
List<product> productList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        productList=new ArrayList<>();

        recyclerView=(RecyclerView)findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        productList.add(
                new product(10,10, 88,"Wheat",R.drawable.crop1));
        productList.add(
                new product(58,24, 75,"Corn",R.drawable.c6));

        productList.add(
                new product(38,17, 64,"Rice",R.drawable.c5));
        productList.add(
                new product(98,12, 57,"Cotton",R.drawable.c4));

adapter=new productadapter(this,productList);
recyclerView.setAdapter(adapter);

    }
}
