package com.example.whalemusic.model;

import java.io.Serializable;
import java.util.List;

public class Playlist implements Serializable {
    private String id;
    private String title;
    private List<Cancion> tracks;
    private String picture_small;
    private String picture_medium;
    private String picture_big;

    public Playlist(String id, String title, List<Cancion> tracks, String picture_small, String picture_medium, String picture_big) {
        this.id = id;
        this.title = title;
        this.tracks = tracks;
        this.picture_small = picture_small;
        this.picture_medium = picture_medium;
        this.picture_big = picture_big;
    }

    public Playlist() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<Cancion> getTracks() {
        return tracks;
    }

    public void setTracks(List<Cancion> tracks) {
        this.tracks = tracks;
    }

    public String getPicture_small() {
        return picture_small;
    }

    public void setPicture_small(String picture_small) {
        this.picture_small = picture_small;
    }

    public String getPicture_medium() {
        return picture_medium;
    }

    public void setPicture_medium(String picture_medium) {
        this.picture_medium = picture_medium;
    }

    public String getPicture_big() {
        return picture_big;
    }

    public void setPicture_big(String picture_big) {
        this.picture_big = picture_big;
    }
}
