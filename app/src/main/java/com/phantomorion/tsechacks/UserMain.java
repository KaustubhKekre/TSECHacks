package com.phantomorion.tsechacks;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class UserMain extends AppCompatActivity implements profile.OnFragmentInteractionListener,events.OnFragmentInteractionListener , money.OnFragmentInteractionListener,items.OnFragmentInteractionListener {
    private DrawerLayout main_nav_drawer;
    NavigationView nav_View;
    TextView name;
    public FirebaseAuth mAuth;
    public FirebaseUser mUser;
    ImageView img;
    Uri mURI;
    SharedPreferences.Editor edit;
    SharedPreferences spref;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_main);
        android.support.v7.widget.Toolbar toolbar = findViewById(R.id.toolBar);
        setSupportActionBar(toolbar);
        spref=getSharedPreferences("userName",MODE_PRIVATE);

//        mAuth=FirebaseAuth.getInstance();
//        mUser=signInOptions.mAuth.getCurrentUser();


        main_nav_drawer = findViewById(R.id.main_nav_drawer);
        nav_View = findViewById(R.id.nav_view);
        View headerView = nav_View.getHeaderView(0);
        name = headerView.findViewById(R.id.name);
        img=headerView.findViewById(R.id.img);
        name.setText(spref.getString("userName",null));
        String s1="";
        s1=getIntent().getStringExtra("URL");


            Glide.with(UserMain.this)
                   .load(s1)
                .into(img);

        final ViewPager viewPager = findViewById(R.id.ViewPager);
        final pagerAdapter adapter = new pagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(adapter);

        android.support.v7.app.ActionBarDrawerToggle toggle = new android.support.v7.app.ActionBarDrawerToggle(this, main_nav_drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        main_nav_drawer.addDrawerListener(toggle);
        toggle.syncState();

        nav_View.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId())
                {
                    case R.id.nav_events:
                        viewPager.setCurrentItem(0);
                        main_nav_drawer.closeDrawer(GravityCompat.START);
                        break;
                    case R.id.nav_money:
                        viewPager.setCurrentItem(1);
                        main_nav_drawer.closeDrawer(GravityCompat.START);
                        break;
                    case R.id.nav_items:
                        viewPager.setCurrentItem(2);
                        main_nav_drawer.closeDrawer(GravityCompat.START);
                        break;
                    case R.id.nav_profile:
                        viewPager.setCurrentItem(3);
                        main_nav_drawer.closeDrawer(GravityCompat.START);
                        break;

                    case R.id.nav_logOut:
                        AlertDialog.Builder builder = new AlertDialog.Builder(UserMain.this);
                        DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                switch (which){
                                    case DialogInterface.BUTTON_POSITIVE:

                                        FirebaseAuth.getInstance().signOut();
                                        edit=spref.edit();
                                        edit.clear();
                                        edit.commit();
                                        Intent intent1 =new Intent(UserMain.this,signInOptions.class);
                                        startActivity(intent1);
                                        if(mUser!=null)
                                        {
                                            mUser=null;
                                        }
                                        finish();

                                        break;

                                    case DialogInterface.BUTTON_NEGATIVE:
                                        break;
                                }
                            }
                        };

                        builder.setMessage("Are you sure you want to log out?").setPositiveButton("Yes", dialogClickListener)
                                .setNegativeButton("Back", dialogClickListener).show();

                        break;
                    default:break;
                }

                return true;
            }
        });
    }

    @Override
    public void onBackPressed() {
        if (main_nav_drawer.isDrawerOpen(GravityCompat.START)) {
            main_nav_drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
