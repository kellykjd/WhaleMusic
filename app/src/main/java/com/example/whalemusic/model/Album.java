package com.example.whalemusic.model;

import androidx.room.ColumnInfo;
import androidx.room.Embedded;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

import com.google.gson.JsonObject;

import java.io.Serializable;
import java.util.List;

@Entity(tableName = "album")
public class Album implements Serializable {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name="id")
    private Integer id;
    @ColumnInfo
    private String title;
    @ColumnInfo
    private String cover_small;
    @ColumnInfo
    private String cover_medium;
    @ColumnInfo
    private Integer genre_id;
    @ColumnInfo
    private String tracklist;
    @ColumnInfo
    @TypeConverters(Converter.class)
    private Artista artist;

    @Ignore
    public Album() {
    }

    public Album(Integer id, String title, String cover_small, String cover_medium, Integer genre_id, String tracklist, Artista artist) {
        this.id = id;
        this.title = title;
        this.cover_small = cover_small;
        this.cover_medium = cover_medium;
        this.genre_id = genre_id;
        this.tracklist = tracklist;
        this.artist = artist;
    }

   public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCover_small() {
        return cover_small;
    }

    public void setCover_small(String cover_small) {
        this.cover_small = cover_small;
    }

    public String getCover_medium() {
        return cover_medium;
    }

    public void setCover_medium(String cover_medium) {
        this.cover_medium = cover_medium;
    }

    public Integer getGenre_id() {
        return genre_id;
    }

    public void setGenre_id(Integer genre_id) {
        this.genre_id = genre_id;
    }

    public String getTracklist() {
        return tracklist;
    }

    public void setTracklist(String tracklist) {
        this.tracklist = tracklist;
    }

    public Artista getArtist() {
        return artist;
    }

    public void setArtist(Artista artist) {
        this.artist = artist;
    }
}
