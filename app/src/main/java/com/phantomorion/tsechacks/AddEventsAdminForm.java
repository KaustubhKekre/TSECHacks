package com.phantomorion.tsechacks;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;

public class AddEventsAdminForm extends AppCompatActivity {

    AutoCompleteTextView mTitle,mDate,mVenue;
    String title,date,venue;
    Button mSave;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_events_admin_form);

        mTitle = findViewById(R.id.title);
        mDate = findViewById(R.id.date);
        mVenue = findViewById(R.id.venue);
        mSave = findViewById(R.id.save_button);

        mSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveEventDetails();
            }
        });
    }

    public void saveEventDetails(){
        title = mTitle.getText().toString();
        date = mDate.getText().toString();
        venue = mVenue.getText().toString();

        FirebaseFirestore fdb = FirebaseFirestore.getInstance();
        CollectionReference crf = fdb.collection("Events");
        EventDetails ed = new EventDetails(date,title,venue);
        crf.document(title).set(ed)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Toast.makeText(AddEventsAdminForm.this, "Event successfully added", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(AddEventsAdminForm.this,EventsFragmentAdmin.class);
                        startActivity(intent);
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(AddEventsAdminForm.this, "Failed", Toast.LENGTH_SHORT).show();
                    }
                });
    }
}
