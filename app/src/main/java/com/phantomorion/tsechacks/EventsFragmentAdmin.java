package com.phantomorion.tsechacks;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;
import com.phantomorion.tsechacks.AdapterFiles.eventAdapter;

import java.util.ArrayList;
import java.util.List;

public class EventsFragmentAdmin extends Fragment  {
    FirebaseFirestore fdb = FirebaseFirestore.getInstance();
    CollectionReference crf = fdb.collection("Events");
    RecyclerView mRecyclerViewEvents;
    Button mAddEventButton;
    eventAdaptera mAdapter;
    ArrayList<EventDetails> ed;
    // EventDetails ed;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_eventsa, container, false);
        mAddEventButton = view.findViewById(R.id.add_event_button);
        mRecyclerViewEvents = view.findViewById(R.id.recycler_view_for_events);
        mRecyclerViewEvents.setHasFixedSize(true);
        mRecyclerViewEvents.setLayoutManager(new LinearLayoutManager(getContext()));
        mRecyclerViewEvents.setNestedScrollingEnabled(true);
        mAdapter = new eventAdaptera(ed);





        crf.get()
                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                        ed = (ArrayList<EventDetails>) queryDocumentSnapshots.toObjects(EventDetails.class);
                        mRecyclerViewEvents.setAdapter(new eventAdaptera(ed));
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {

                    }
                });

        mAddEventButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(),AddEventsAdminForm.class);
                startActivity(intent);

            }
        });
        return  view;
    }


}