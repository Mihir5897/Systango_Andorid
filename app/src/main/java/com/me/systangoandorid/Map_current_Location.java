package com.me.systangoandorid;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.view.View;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;

public class Map_current_Location extends AppCompatActivity {
    //Initialize variable
    DrawerLayout drawerLayout;
    SupportMapFragment supportMapFragment;
    FusedLocationProviderClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map_current_location);
        //Assign variable
        drawerLayout = findViewById(R.id.drawer_layout);
        supportMapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.google_map);
        ///Initialize fused Location
        client = LocationServices.getFusedLocationProviderClient(this);
        //check Permission
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED &&
                ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            //when permission is grated
            //call method
            getCurrentLocation();
        } else {
            ActivityCompat.requestPermissions(Map_current_Location.this,
                    new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 44);
        }
    }

    public void getCurrentLocation() {
        //Initialize task location
        @SuppressLint("MissingPermission")
        Task<Location> task = client.getLastLocation();
        task.addOnSuccessListener(new OnSuccessListener<Location>() {
            @Override
            public void onSuccess(Location location) {
                //when success
                if (location != null){
                    //Sync map
                    supportMapFragment.getMapAsync(new OnMapReadyCallback() {
                        @Override
                        public void onMapReady(@NonNull GoogleMap googleMap) {
                            LatLng GWOC = new LatLng(location.getLatitude(),location.getLongitude());
                            //Create marker option
                            MarkerOptions options = new MarkerOptions().position(GWOC).title("My Location")
                                    .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE));
                            //zoom map
                            googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(GWOC,10));
                            //add marker on map
                            googleMap.addMarker(options);
                        }
                    });
                }
            }
        });
            }
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (requestCode == 44) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                //when permission is grant
                getCurrentLocation();

            }
        }
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
        //Recreate Activity
        recreate();

    }
    public void ClickAboutUS(View view){
        //Redirect activity to About_Us
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