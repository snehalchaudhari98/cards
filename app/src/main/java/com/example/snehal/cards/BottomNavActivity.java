package com.example.snehal.cards;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.widget.TextView;

public class BottomNavActivity extends AppCompatActivity {

    private TextView mTextMessage;
    private DrawerLayout drawer;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    setTitle("Fragment One");
                    FragmentOne fragment1=new FragmentOne();
                    FragmentTransaction fragmentTransaction1=getSupportFragmentManager().beginTransaction();
                    fragmentTransaction1.replace(R.id.frame,fragment1,"FragmentName");
                    fragmentTransaction1.commit();
                    return true;
                case R.id.navigation_dashboard:
                    setTitle("Fragment Two");
                    FragmentTwo fragment2=new FragmentTwo();
                    FragmentTransaction fragmentTransaction2=getSupportFragmentManager().beginTransaction();
                    fragmentTransaction2.replace(R.id.frame,fragment2,"FragmentName");
                    fragmentTransaction2.commit();
                    return true;
                case R.id.navigation_notifications:
                    setTitle("Fragment Two");
                    FragmentThree fragment3=new FragmentThree();
                    FragmentTransaction fragmentTransaction3=getSupportFragmentManager().beginTransaction();
                    fragmentTransaction3.replace(R.id.frame,fragment3,"FragmentName");
                    fragmentTransaction3.commit();
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bottom_nav);

        android.support.v7.widget.Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        drawer = findViewById(R.id.container);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this,drawer,toolbar,R.string.navigation_drawer_open,R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        mTextMessage = (TextView) findViewById(R.id.message);
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);


        setTitle("Fragment One");
        FragmentOne fragment1 = new FragmentOne();
        FragmentTransaction fragmentTransaction1 = getSupportFragmentManager().beginTransaction();
        fragmentTransaction1.replace(R.id.frame, fragment1, "FragmentName");
        fragmentTransaction1.commit();

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.nav_camera:
                        Log.d("tag", "FIRST ONE");
                        break;
                    case R.id.nav_gallery:
                        Log.d("tag", "SECOND ONE");
                        break;
                    case R.id.nav_slideshow:
                        Log.d("tag", "THIRD ONE");
                        break;
                    case R.id.nav_manage:
                        Log.d("tag", "FOUR ONE");
                        break;
                    case R.id.nav_notify:
                        Log.d("tag", "FIVE ONE");
                        break;
                    case R.id.nav_setting:
                        Log.d("tag", "SIX ONE");
                        break;

                }
                drawer.closeDrawer(GravityCompat.START);
                return false;
            }


        });


    }



    @Override
    public void onBackPressed(){
        if(drawer.isDrawerOpen(GravityCompat.START)){
            drawer.closeDrawer(GravityCompat.START);
        }else{
            super.onBackPressed();
        }
    }




}
