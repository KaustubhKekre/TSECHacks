package com.phantomorion.tsechacks.AdapterFiles;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.EventLog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.phantomorion.tsechacks.EventDetails;
import com.phantomorion.tsechacks.R;

import java.util.ArrayList;

public class eventAdapter extends RecyclerView.Adapter<eventAdapter.ViewHolder> {
    ArrayList<EventDetails> eventDetails;

    public eventAdapter(ArrayList<EventDetails> eventDetails){
        this.eventDetails=eventDetails;
    }

    @NonNull
    @Override
    public eventAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflate=LayoutInflater.from(viewGroup.getContext());
        View view = inflate.inflate(R.layout.activity_event_layout,viewGroup,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull eventAdapter.ViewHolder viewHolder, int i) {
        viewHolder.title.setText(eventDetails.get(i).getTitle());
        viewHolder.date.setText(eventDetails.get(i).getDate());
        viewHolder.venue.setText(eventDetails.get(i).getVenue());

    }

    @Override
    public int getItemCount() {
        return eventDetails.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView title,date,venue;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            title=itemView.findViewById(R.id.title);
            date=itemView.findViewById(R.id.date);
            venue=itemView.findViewById(R.id.venue);
        }
    }
}