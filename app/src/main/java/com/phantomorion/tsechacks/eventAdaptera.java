package com.phantomorion.tsechacks;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.EventLog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.phantomorion.tsechacks.EventDetails;
import com.phantomorion.tsechacks.R;

import java.util.ArrayList;

public class eventAdaptera extends RecyclerView.Adapter<eventAdaptera.ViewHolder> {
    FirebaseFirestore fdb = FirebaseFirestore.getInstance();
    ArrayList<EventDetails> eventDetails;
    private onItemClickedListener mListener;

    public interface onItemClickedListener {

        void onDeleteClick(int position);
    }

    public void setOnItemClickListener(onItemClickedListener listener) {
        mListener = listener;
    }

    public eventAdaptera(ArrayList<EventDetails> eventDetails) {
        this.eventDetails = eventDetails;
    }

    @NonNull
    @Override
    public eventAdaptera.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflate = LayoutInflater.from(viewGroup.getContext());
        View view = inflate.inflate(R.layout.activity_event_layouta, viewGroup, false);
        return new ViewHolder(view, mListener);
    }

    @Override
    public void onBindViewHolder(@NonNull final eventAdaptera.ViewHolder viewHolder, int i) {
        viewHolder.title.setText(eventDetails.get(i).getTitle());
        viewHolder.date.setText(eventDetails.get(i).getDate());
        viewHolder.venue.setText(eventDetails.get(i).getVenue());
        viewHolder.image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fdb.collection("Events").document(eventDetails.get(viewHolder.getAdapterPosition()).getTitle()).delete();
                Intent intent =new Intent(v.getContext(),BottomNavigationMainActivity.class);
                v.getContext().startActivity(intent);
            }
        });


    }

    @Override
    public int getItemCount() {
        return eventDetails.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView title, date, venue;
        ImageView image;

        public ViewHolder(@NonNull final View itemView, final onItemClickedListener listener) {
            super(itemView);
            title = itemView.findViewById(R.id.title);
            date = itemView.findViewById(R.id.date);
            venue = itemView.findViewById(R.id.venue);
            image = itemView.findViewById(R.id.image);

//            image.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//                    if(listener!=null){
//                        int position = getAdapterPosition();
//                        fdb.collection("Events").document(eventDetails.get(position).getTitle()).delete();
//                        Intent intent=new Intent(view.getContext(),BottomNavigationMainActivity.class);
//
//                        view.getContext().startActivity(intent);
//                        if(position!=RecyclerView.NO_POSITION){
//                            listener.onDeleteClick(position);
//                        }
//                    }
//                }
//            });
        }
        }
    }
