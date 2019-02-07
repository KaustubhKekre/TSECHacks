package com.phantomorion.tsechacks;

public class EventDetails {
    String date,title,venue;

    public EventDetails() {

    }

    public EventDetails(String date, String title,String venue) {

        this.date = date;
        this.title = title;
        this.venue=venue;
    }


    public String getVenue() {
        return venue;
    }

    public void setVenue(String venue) {
        this.venue = venue;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;

    }
}