package com.phantomorion.tsechacks;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

public class BottomNavigationMainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_boottom_navigation_main);

        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new EventsFragmentAdmin()).commit();
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation_view);



        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Fragment tempFragment = null;

                switch(item.getItemId()){
                    case R.id.events:
                        tempFragment = new EventsFragmentAdmin();
                        break;
                    case R.id.donations:
                        tempFragment = new DonationsFragmentAdmin();
                        break;


                }
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, tempFragment).commit();
                return true;
            }
        });
    }
}
