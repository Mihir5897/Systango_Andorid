package com.me.systangoandorid;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class About_Us extends AppCompatActivity {
    //Initialize variable
    DrawerLayout drawerLayout;
    BottomNavigationView bottomNavigationView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_us);
        //Assign variable
        drawerLayout = findViewById(R.id.drawer_layout);
        bottomNavigationView = findViewById(R.id.bottom_navigation);
        //Set home Selected
        bottomNavigationView.setSelectedItemId(R.id.aboutus);
        //perform item SelectListener
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.dashboard:
                        startActivity(new Intent(getApplicationContext(),
                                Dashboard.class));
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.home:
                        startActivity(new Intent(getApplicationContext(),
                                MainActivity.class));
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.aboutus:

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
        //redirectActivity to dashboard
        MainActivity.redirectActivity(this,Dashboard.class);

    }
    public void ClickMap( View view){
        //Redirect activity to MAP
        MainActivity.redirectActivity(this,Map_current_Location.class);
    }
    public void ClickAboutUS(View view){
        //Recreate Activity
        recreate();
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