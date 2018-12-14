package com.example.snehal.cards;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    RecyclerView recyclerView;
productadapter adapter;
List<product> productList;
   
//GridLayout maingrid;

    Button next;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        next=(Button)findViewById(R.id.next);


        BottomNavigationView bottomNavigationView=(BottomNavigationView)findViewById(R.id.bottom_nevigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()){
                    case R.id.action_add:
                        //Toast.makeText(ScrollingActivity.this,"Added!! Yeppie",Toast.LENGTH_SHORT).show();
                        Intent intent=new Intent(MainActivity.this,gridview.class);
                        finish();
                        startActivity(intent);
                        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                        break;
                    case R.id.action_chart:
                        //Toast.makeText(ScrollingActivity.this,"Showing all Details here!!",Toast.LENGTH_SHORT).show();
                        Intent intent1=new Intent(MainActivity.this,MainActivity.class);
                        finish();
                        startActivity(intent1);
                        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                        break;
                    case R.id.action_profile:
                        //Toast.makeText(ScrollingActivity.this,"Your Profile",Toast.LENGTH_SHORT).show();
                        Intent intent2=new Intent(MainActivity.this,MainActivity.class);
                        finish();
                        startActivity(intent2);
                        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                        break;
                  /*  case R.id.action_help:
                        Toast.makeText(ScrollingActivity.this,"Google it ",Toast.LENGTH_SHORT).show();
                        break;*/

                }
                return true;
            }
        });





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


       next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //maingrid=(GridLayout)findViewById(R.id.maingrid);
                //setsingleEvent(maingrid);

                Toast.makeText(MainActivity.this,"Going to next page",Toast.LENGTH_SHORT).show();

                Intent intent=new Intent(MainActivity.this,gridview.class);
                //finish();
                startActivity(intent);
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);

            }
        });



    }

    /*private void setsingleEvent(GridLayout maingrid) {

        for(int i=0;i<maingrid.getChildCount();i++){
            CardView cardView=(CardView)maingrid.getChildAt(i);
            final int a=i;
            cardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(MainActivity.this,"you just added ::"+a,Toast.LENGTH_SHORT).show();
                }
            });

        }
    }*/
}
