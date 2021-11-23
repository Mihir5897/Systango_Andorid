package com.me.systangoandorid;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class Dashboard extends AppCompatActivity {
//Initialize variable
    DrawerLayout drawerLayout;
    BottomNavigationView bottomNavigationView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        //Assign variable
        drawerLayout = findViewById(R.id.drawer_layout);
        bottomNavigationView = findViewById(R.id.bottom_navigation);
        //Set home Selected
        bottomNavigationView.setSelectedItemId(R.id.dashboard);
        //perform item SelectListener
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.dashboard:

                        return true;
                    case R.id.home:
                        startActivity(new Intent(getApplicationContext(),
                                MainActivity.class));
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.aboutus:
                        startActivity(new Intent(getApplicationContext(),
                                About_Us.class));
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.more:
                        startActivity(new Intent(getApplicationContext(),
                                MoreActivity.class));
                        overridePendingTransition(0,0);
                        return true;
                }
                return false;
            }
        });

    }
    public  void  ClickMenu(View view){
        //open drawer
        MainActivity.openDrawer(drawerLayout);
    }
    public void  ClickLogo(View view){
        //Close drawer
        MainActivity.closerDrawer(drawerLayout);
    }
    public  void ClickHome(View view){
        //recreate activity
        MainActivity.redirectActivity(this,MainActivity.class);
    }
    public void ClickDashBoard(View view){
        //Recreate Activity
        recreate();
    }
    public void ClickMap( View view){
        //Redirect activity to MAP
        MainActivity.redirectActivity(this,Map_current_Location.class);
    }
    public void ClickAboutUS(View view){
        //redirectActivity to about us
        MainActivity.redirectActivity(this,About_Us.class);
    }
    public void ClickLogout(View view){
        //Close App
        MainActivity.logout(this);
    }
    @Override
    public void onPause() {
        super.onPause();
        //Close Drawer
        MainActivity.closerDrawer(drawerLayout);
    }
}