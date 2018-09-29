package com.example.snehal.cards;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

public class NewInflaterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_inflater);



        BottomNavigationView bottomNavigationView=(BottomNavigationView)findViewById(R.id.bottom_nevigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()){
                    case R.id.action_add:
                        //Toast.makeText(ScrollingActivity.this,"Added!! Yeppie",Toast.LENGTH_SHORT).show();
                       // Intent intent=new Intent(MainActivity.this,gridview.class);
                        //startActivity(intent);
                        FragmentTransaction transaction=getSupportFragmentManager().beginTransaction();
                        transaction.replace(R.id.fragment_container,new FragmentOne());
                        transaction.commit();
                        break;
                    case R.id.action_chart:
                        //Toast.makeText(ScrollingActivity.this,"Showing all Details here!!",Toast.LENGTH_SHORT).show();
                        //Intent intent1=new Intent(MainActivity.this,MainActivity.class);
                        //startActivity(intent1);
                        FragmentTransaction transaction1=getSupportFragmentManager().beginTransaction();
                        transaction1.replace(R.id.fragment_container,new FragmentTwo());
                        transaction1.commit();
                        break;
                    case R.id.action_profile:
                        //Toast.makeText(ScrollingActivity.this,"Your Profile",Toast.LENGTH_SHORT).show();
                        //Intent intent2=new Intent(MainActivity.this,MainActivity.class);
                        //startActivity(intent2);
                        FragmentTransaction transaction2=getSupportFragmentManager().beginTransaction();
                        transaction2.replace(R.id.fragment_container,new FragmentThree());
                        transaction2.commit();

                        break;
                  /*  case R.id.action_help:
                        Toast.makeText(ScrollingActivity.this,"Google it ",Toast.LENGTH_SHORT).show();
                        break;*/

                }
                return true;
            }
        });
    }
}
