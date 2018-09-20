package com.example.snehal.cards;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class CropEntry extends AppCompatActivity {

    DatabaseHelper myDb;
    EditText sugarcaneSowingDate,sugarcaneSowingArea;
    Button sugarcaneEnter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crop_entry);
        myDb = new DatabaseHelper(this);

        sugarcaneSowingDate = (EditText)findViewById(R.id.sugarcanesowingdate);
        sugarcaneSowingArea = (EditText)findViewById(R.id.suagarcanesowingarea);
        sugarcaneEnter = (Button)findViewById(R.id.sugarcanesowingenter);


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
    }


    public  void AddData() {
        sugarcaneEnter.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        boolean isInserted = myDb.insertData(sugarcaneSowingDate.getText().toString(),sugarcaneSowingArea.getText().toString());
                        if(isInserted ==true)
                            Toast.makeText(CropEntry.this,"Data Inserted",Toast.LENGTH_LONG).show();
                        else
                            Toast.makeText(CropEntry.this,"Data not Inserted",Toast.LENGTH_LONG).show();
                    }
                }
        );
    }

}









