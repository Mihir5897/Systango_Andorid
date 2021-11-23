package com.me.systangoandorid;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.material.bottomnavigation.BottomNavigationView;


public class MainActivity extends AppCompatActivity {
    //Initialize variable
    DrawerLayout drawerLayout;
    TextView tname,tage;
    BottomNavigationView bottomNavigationView;
    String email,name;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tname = findViewById(R.id.txname);
        tage= findViewById(R.id.txage);
         name = getIntent().getStringExtra("name");
         email = getIntent().getStringExtra("EMAIL");
        tname.setText(name);
        tage.setText(email);
        //assign variable
        drawerLayout = findViewById(R.id.drawer_layout);
        bottomNavigationView = findViewById(R.id.bottom_navigation);
        //Set home Selected
        bottomNavigationView.setSelectedItemId(R.id.home);
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

    public void ClickMenu(View view){
        //Open drawer
        openDrawer(drawerLayout);
    }
    public void  ClickLogo(View view){
        //Redirect activity to User Profile
        //redirectActivity(this,UserProfile.class);
        //Close drawer
        closerDrawer(drawerLayout);
    }
    public static void openDrawer(DrawerLayout drawerLayout) {
        //open Drawer Layout
        drawerLayout.openDrawer(GravityCompat.START);

    }
    public static  void  closerDrawer(DrawerLayout drawerLayout){
        //close drawer layout
        //check condition
        if (drawerLayout.isDrawerOpen(GravityCompat.START)){
            //when drawer is open
            //close the drawer
            drawerLayout.closeDrawer(GravityCompat.START);
        }
    }
    public  void ClickHome(View view){
        //recreate activity
        recreate();
    }
    public void ClickName(View view){
        //Redirect activity to User Profile
        //redirectActivity(this,UserProfile.class);
        Intent intent = new Intent(MainActivity.this,UserProfile.class)
                .putExtra("EMAIL",email);
        startActivity(intent);
    }
    public void ClickDashBoard( View view){
        //Redirect activity to dashboard
        redirectActivity(this,Dashboard.class);
    }
    public void ClickMap( View view){
        //Redirect activity to MAP
        redirectActivity(this,Map_current_Location.class);
    }
    public void ClickAboutUS(View view){
        //redirectActivity to about us
        redirectActivity(this,About_Us.class);
    }
    public void ClickLogout(View view){
        //Close app
        logout(this);
    }

    public static void logout(Activity activity) {
        //Initialize alter dialog
        AlertDialog.Builder builder = new AlertDialog.Builder(activity  );
        //set Title
        builder.setTitle("Logout");
        //setMessage
        builder.setMessage("Are you sure you  want to logout");
        //positive yes button
        builder.setPositiveButton("YES", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //Finish activity
                activity.finishAffinity();
                //RedirectActivity to SignUp
              // redirectActivity(this,SignUp.class);
//                Intent intent = new Intent(MainActivity.this, SignUp.class);
//                startActivity(intent);
                //Exit app
                System.exit(0);

            }
        }).setNegativeButton("NO", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //Dismiss dialog
                dialog.dismiss();
            }
        });
        //Negative No button
        //Show dialog
        builder.show();

    }

    public static void redirectActivity(Activity activity,Class aClass) {
        //Initialize intent
        Intent intent = new Intent(activity,aClass);
        //set flag
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        //start activity
        activity.startActivity(intent);
    }

    @Override
    public void onPause() {
        super.onPause();
        //Close Drawer
        closerDrawer(drawerLayout);
    }
}