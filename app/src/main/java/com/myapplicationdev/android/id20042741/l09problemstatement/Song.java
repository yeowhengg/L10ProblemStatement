package com.myapplicationdev.android.id20042741.l09problemstatement;

public class Song {
    private int _id;
    private String title;
    private String singer;
    private int year;
    private int stars;

    public Song (String title, String singer, int year, int stars){
        this.title = title;
        this.singer = singer;
        this.year = year;
        this.stars = stars;
    }

    public int get_id(){
        return this._id;
    }

    public String getTitle(){
        return this.title;
    }

    public String getSinger(){
        return this.singer;
    }

    public int getYear(){
        return this.year;
    }

    public int getStars(){
        return this.stars;
    }
}
