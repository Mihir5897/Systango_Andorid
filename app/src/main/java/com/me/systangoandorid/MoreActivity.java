package com.me.systangoandorid;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class MoreActivity extends AppCompatActivity {
    //Initialize variable
    TabLayout tabLayout;
    ViewPager viewPager;


    BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_more);
        bottomNavigationView = findViewById(R.id.bottom_navigation);
        //Set home Selected
        bottomNavigationView.setSelectedItemId(R.id.more);
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
                        startActivity(new Intent(getApplicationContext(),
                                About_Us.class));
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.more:
                        return true;

                }
                return false;
            }
        });
        //view pager code
        tabLayout = findViewById(R.id.tab_layout);
        viewPager = findViewById(R.id.view_pager);

        ArrayList<String> arrayList=new ArrayList<>();
        //add title in array list
        arrayList.add("Tab 1");
        arrayList.add("Tab 2");
        arrayList.add("Tab 3");
        //prepare view page
        prePareViewPage(viewPager,arrayList);
        //Setup with view pager
        tabLayout.setupWithViewPager(viewPager);
        
    }

    private void prePareViewPage(ViewPager viewPager, ArrayList<String> arrayList) {
        //Initialize main adapter
        MainAdapter adapter = new MainAdapter (getSupportFragmentManager());
        //Initialize main fragment
        MainFragment mainFragment = new MainFragment();
        //use for loop
        for (int i=0;i<arrayList.size();i++){
            //Initialize Bundle
            Bundle bundle = new Bundle();
            //put string
            bundle.putString("title",arrayList.get(i));
            //Set Argument
            mainFragment.setArguments(bundle);
            //Add fragment
            adapter.addFragment(mainFragment,arrayList.get(i));
            //Define a new fragment
            mainFragment= new MainFragment();
        }
        //set adapter
        viewPager.setAdapter(adapter);
    }

    private class MainAdapter extends FragmentPagerAdapter {
        //initialize array list
        ArrayList<String> arrayList = new ArrayList<>();
        List<Fragment> fragmentList = new ArrayList<>();
        //create constructor
        public void addFragment( Fragment fragment,String title) {
        //Add title
            arrayList.add(title);
            //add fragment
            fragmentList.add(fragment);

        }


        public MainAdapter(@NonNull FragmentManager fm) {
            super(fm);
        }

        @NonNull
        @Override
        public Fragment getItem(int position) {
            //return fragment position
            return fragmentList.get(position);
        }

        @Override
        public int getCount() {
            //return  fragment list size
            return fragmentList.size();
        }

        @Nullable
        @Override
        public CharSequence getPageTitle(int position) {
            //return array list position
            return arrayList.get(position);
        }
    }
}