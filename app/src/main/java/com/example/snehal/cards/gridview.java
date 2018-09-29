package com.example.snehal.cards;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.GridLayout;

public class gridview extends AppCompatActivity {

    GridLayout maingrid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gridview);




        maingrid=(GridLayout)findViewById(R.id.maingrid);

        setsingleEvent(maingrid);



        /*BottomNavigationView bottomNavigationView=(BottomNavigationView)findViewById(R.id.bottom_nevigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()){
                    case R.id.action_add:
                        //Toast.makeText(ScrollingActivity.this,"Added!! Yeppie",Toast.LENGTH_SHORT).show();
                        Intent intent=new Intent(gridview.this,gridview.class);
                        finish();
                        startActivity(intent);
                        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                        break;
                    case R.id.action_chart:
                        //Toast.makeText(ScrollingActivity.this,"Showing all Details here!!",Toast.LENGTH_SHORT).show();
                        Intent intent1=new Intent(gridview.this,MainActivity.class);
                        finish();
                        startActivity(intent1);
                        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                        break;
                    case R.id.action_profile:
                        //Toast.makeText(ScrollingActivity.this,"Your Profile",Toast.LENGTH_SHORT).show();
                        Intent intent2=new Intent(gridview.this,MainActivity.class);
                        finish();
                        startActivity(intent2);
                        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                        break;
                  /*  case R.id.action_help:
                        Toast.makeText(ScrollingActivity.this,"Google it ",Toast.LENGTH_SHORT).show();
                        break;*/

               /* }
                return true;
            }
        });*/

    }


    private void setsingleEvent(GridLayout maingrid) {

        for(int i=0;i<maingrid.getChildCount();i++){
            CardView cardView=(CardView)maingrid.getChildAt(i);
            final int a=i;
            cardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                 //   Toast.makeText(gridview.this,"you just added ::"+a,Toast.LENGTH_SHORT).show();
                    Intent intent=new Intent(gridview.this,CropEntry.class);
                    finish();
                    startActivity(intent);

                }
            });

        }
    }

    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
    }
}
