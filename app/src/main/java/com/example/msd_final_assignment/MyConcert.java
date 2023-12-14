package com.example.msd_final_assignment;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
@Entity(tableName = "my_concerts")
public class MyConcert extends Concert {

    public MyConcert(String name, String date, String imageUrl) {
        super(name, date, imageUrl);
    }
    @PrimaryKey
    @NonNull
    private String concertId;

    @ColumnInfo(name = "concert_title")
    private String concertTitle;

    @ColumnInfo(name = "concert_date")
    private String concertDate;

    public String getConcertId() {
        return concertId;
    }

    public void setConcertId(String concertId) {
        this.concertId = concertId;
    }

    public String getConcertTitle() {
        return concertTitle;
    }

    public void setConcertTitle(String concertTitle) {
        this.concertTitle = concertTitle;
    }

    public String getConcertDate() {
        return concertDate;
    }

    public void setConcertDate(String concertDate) {
        this.concertDate = concertDate;
    }


}