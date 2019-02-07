package com.phantomorion.tsechacks;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.icu.text.IDNA;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

public class MainActivity extends AppCompatActivity {

    public FirebaseAuth mAuth;
    public FirebaseUser mUser;
    public Uri uImg;
    boolean connection;
    public FirebaseFirestore fdb;
    public UserDetails userDetails;
    SharedPreferences spref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       // setContentView(R.layout.activity_main);
        connection = haveNetworkConnection();
        spref=getSharedPreferences("userName",MODE_PRIVATE);
        fdb=FirebaseFirestore.getInstance();

        if (connection == false) {
            Toast.makeText(MainActivity.this, "Please check your internet connection and try again", Toast.LENGTH_LONG).show();
            Intent intent = new Intent(MainActivity.this, NoInternet.class);
            startActivity(intent);
            finish();
        } else {
           // setContentView(R.layout.activity_main);
            mAuth = FirebaseAuth.getInstance();
            mUser = mAuth.getCurrentUser();
            String s1=spref.getString("userName","amn");
            if (mUser == null && !s1.equals("admin")) {
                startActivity(new Intent(MainActivity.this, signInOptions.class));
                finish();
            }
            else
            {
//                fdb.collection("Users").document(spref.getString("userName",null))
//                    .get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
//                                                    @Override
//                                                    public void onSuccess(DocumentSnapshot documentSnapshot) {
//                                                       userDetails=documentSnapshot.toObject(UserDetails.class);
//                                                       if(userDetails.getUserType().equals("USER"))
//                                                       {
//                                                           startActivity(new Intent(MainActivity.this, UserMain.class));
//                                                           finish();
//                                                       }
//                                                       else
//                                                       {
//                                                           startActivity(new Intent(MainActivity.this,BottomNavigationMainActivity.class));
//                                                       }
//                                                    }
//                                                }).addOnFailureListener(new OnFailureListener() {
//                        @Override
//                        public void onFailure(@NonNull Exception e) {
//
//                        }
//                    });
                if(s1.equals("admin"))
                {
                    startActivity(new Intent(MainActivity.this,BottomNavigationMainActivity.class));
                    finish();
                }
                else
                {
                    startActivity(new Intent(MainActivity.this, UserMain.class));
                     finish();
                }
            }
        }
        }



    private boolean haveNetworkConnection() {
        boolean haveConnectedWifi = false;
        boolean haveConnectedMobile = false;

        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo[] netInfo = cm.getAllNetworkInfo();
        for (NetworkInfo ni : netInfo) {
            if (ni.getTypeName().equalsIgnoreCase("WIFI"))
                if (ni.isConnected())
                    haveConnectedWifi = true;
            if (ni.getTypeName().equalsIgnoreCase("MOBILE"))
                if (ni.isConnected())
                    haveConnectedMobile = true;
        }
        return haveConnectedWifi || haveConnectedMobile;
    }
    }

