package com.ming.dayouxia.theaterapp.model;


public class Movie {

    private String movieName;
    private String showTime;
    private int icon;

    public int getIcon() {
        return icon;
    }

    public void setIcon(int icon) {
        this.icon = icon;
    }

    public Movie(String name, String time, int icon){
        movieName = name;
        showTime = time;
        this.icon = icon;

    }

    public String getMovieName() {
        return movieName;
    }

    public void setMovieName(String movieName) {
        this.movieName = movieName;
    }

    public String getShowTime() {
        return showTime;
    }

    public void setShowTime(String showTime) {
        this.showTime = showTime;
    }
}
