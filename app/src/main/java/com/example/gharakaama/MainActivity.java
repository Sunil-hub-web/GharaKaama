package com.example.gharakaama;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import com.example.fragment.AboutUs;
import com.example.fragment.Homepage;
import com.example.fragment.Queries;
import com.example.fragment.SettingPage;
import com.example.fragment.Support;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    BottomNavigationView bottomNavigation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bottomNavigation = findViewById(R.id.bottomNavigation);

        getSupportFragmentManager().beginTransaction().replace(R.id.framLayout,new Homepage(),"HomeFragment").commit();


        bottomNavigation.setSelectedItemId(R.id.home);

        bottomNavigation.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                Fragment selectedFragment = null;

                switch (item.getItemId()){

                    case R.id.about:

                        selectedFragment = new AboutUs();

                        break;

                    case R.id.home:

                        selectedFragment = new Homepage();

                        break;

                    case R.id.support:

                        selectedFragment = new Support();

                        break;

                    case R.id.queries:

                        selectedFragment = new Queries();

                        break;

                    case R.id.setting:

                        selectedFragment = new SettingPage();

                        break;


                }
                getSupportFragmentManager().beginTransaction().replace(R.id.framLayout,selectedFragment).commit();

                return true;
            }
        });
    }

}