package com.example.gharakaama;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.fragment.AboutUs;
import com.example.fragment.BookingHistory;
import com.example.fragment.CustomerFAQ;
import com.example.fragment.Homepage;
import com.example.fragment.Queries;
import com.example.fragment.SettingPage;
import com.example.fragment.Support;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    public static BottomNavigationView bottomNavigation;
    Homepage test;
    private Boolean exit = false;
    public static FragmentManager fragmentManager;
    ImageView image_Logo,image_back;
    TextView welcome;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bottomNavigation = findViewById(R.id.bottomNavigation);
        welcome = findViewById(R.id.welcome);
        image_Logo = findViewById(R.id.image_Logo);
        image_back = findViewById(R.id.image_back);



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

                        selectedFragment = new CustomerFAQ();

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
    @Override
    public void onBackPressed() {
        //super.onBackPressed();

        test = (Homepage) getSupportFragmentManager().findFragmentByTag("HomeFragment");

        if (test != null && test.isVisible()) {

            if (exit) {

                this.finish();
                // finish activity
            } else {
                Toast.makeText(this, "Press Back again to Exit.",
                        Toast.LENGTH_SHORT).show();
                exit = true;
                new Handler().postDelayed(new Runnable() {

                    @Override
                    public void run() {
                        // TODO Auto-generated method stub
                        Intent a = new Intent(Intent.ACTION_MAIN);
                        a.addCategory(Intent.CATEGORY_HOME);
                        a.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(a);
                    }
                }, 4 * 1000);
            }
        }
        else {

            getSupportFragmentManager().beginTransaction().replace(R.id.framLayout,new Homepage(),"HomeFragment").commit();

        }
    }
}