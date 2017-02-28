package com.example.gauravsingh.restdemo;

import java.io.Serializable;

/**
 * Created by Gaurav Singh on 26-02-2017.
 */

public class MovieDTO implements Serializable {
    String title="";
    String date="";
    String voteAverage="";
    String plot="";
    String posterUrl="";
    String id="";

    public MovieDTO(){}

    public MovieDTO(String id,String title, String date, String voteAverage, String plot, String posterUrl) {
        this.title = title;
        this.date = date;
        this.voteAverage = voteAverage;
        this.plot = plot;
        this.posterUrl = posterUrl;
        this.id = id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setPlot(String plot) {
        this.plot = plot;
    }

    public void setPosterUrl(String posterUrl) {
        this.posterUrl = posterUrl;
    }

    public void setVoteAverage(String voteAverage) {
        this.voteAverage = voteAverage;
    }

    public String getDate() {
        return date;
    }

    public String getPlot() {
        return plot;
    }

    public String getPosterUrl() {
        return posterUrl;
    }

    public String getTitle() {
        return title;
    }

    public String getVoteAverage() {
        return voteAverage;
    }

    public String getId() {
        return id;
    }
}
