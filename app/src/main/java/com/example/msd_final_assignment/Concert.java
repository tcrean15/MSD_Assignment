package com.example.msd_final_assignment;
public class Concert {
    private String title; // Updated field name
    private String date;
    private String imageUrl;

    public Concert(String title, String date, String imageUrl) {
        this.title = title;
        this.date = date;
        this.imageUrl = imageUrl;
    }

    public String getTitle() {
        return title;
    }

    public String getDate() {
        return date;
    }

    public String getImageUrl() {
        return imageUrl;
    }
}
