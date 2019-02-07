package com.phantomorion.tsechacks;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.Spinner;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

public class InformationForm extends AppCompatActivity {
    Spinner mSpin;
    Button mSubmitButton;
    AutoCompleteTextView mName,mEmail,mAge;
    String gender;
    String smName,smEmail,smAge;
     SharedPreferences sharedPreferences;
    SharedPreferences.Editor edit;
    String [] genderlist = {"Male","Female","Other"};;
    private FirebaseFirestore fdb=FirebaseFirestore.getInstance();
    private CollectionReference cref=fdb.collection("Users");
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        sharedPreferences=getSharedPreferences("userName",MODE_PRIVATE);
        setContentView(R.layout.activity_information_form);

        mSpin = findViewById(R.id.gender_spinner);
        mSubmitButton = findViewById(R.id.register_button);
        mName = findViewById(R.id.register_username);
        mEmail = findViewById(R.id.register_email);
        mAge = findViewById(R.id.register_age);

        mSpin.setAdapter(new ArrayAdapter<>(this,android.R.layout.simple_spinner_dropdown_item,genderlist));

        mSubmitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gender = genderlist[(int) mSpin.getSelectedItemId()];
                edit=sharedPreferences.edit();
                smName=mName.getText().toString();
                smAge=mName.getText().toString();
                smEmail=mEmail.getText().toString();
                edit.putString("userName",smName);
                edit.commit();
                UserDetails userDetails =new UserDetails(smName,smEmail,smAge,"USER",gender,0,0);
                cref.document(smName).set(userDetails).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Toast.makeText(InformationForm.this,"Welcome to the family!!",Toast.LENGTH_LONG).show();
                        startActivity(new Intent(InformationForm.this,UserMain.class));
                        finish();
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(InformationForm.this,"Something Went Wrong",Toast.LENGTH_LONG).show();
                    }
                });

            }
        });
    }
}