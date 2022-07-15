package com.myapplicationdev.android.id20042741.l09problemstatement;

import java.io.Serializable;

public class Song implements Serializable {
    private int _id;
    private String title;
    private String singer;
    private int year;
    private int stars;

    public Song (int _id, String title, String singer, int year, int stars){
        this._id = _id;
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

    public String toString(){
        String symbol = "";
        for(int i = 0; i < this.stars; i++){
            symbol += "*";
        }
        return this.getTitle() + "\n" + this.singer + " - " + this.year + "\n" + symbol;
    }

}
