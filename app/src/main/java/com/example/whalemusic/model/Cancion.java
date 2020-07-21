package com.example.whalemusic.model;

import androidx.room.ColumnInfo;
import androidx.room.Embedded;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverter;
import androidx.room.TypeConverters;

import java.io.Serializable;
import java.util.Objects;

@Entity(tableName = "cancion")
public class Cancion implements Serializable {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name="id")
    private Integer id;
    @ColumnInfo
    private String title;
    @ColumnInfo
    private String title_short;
    @ColumnInfo
    private String link;
    @ColumnInfo
    private Integer duration;
    @ColumnInfo
    @TypeConverters(Converter.class)
    private Artista artist;
    @ColumnInfo
    @TypeConverters(Converter.class)
    private Album album;
    @ColumnInfo
    private Integer position;
    @ColumnInfo
    private String preview;

    @Ignore
    public Cancion() {
    }

    public Cancion(Integer id, String title, String title_short, Integer duration, Artista artist, Album album, Integer position, String preview, String link) {
        this.id = id;
        this.title = title;
        this.title_short = title_short;
        this.duration = duration;
        this.artist = artist;
        this.album = album;
        this.position = position;
        this.preview = preview;
        this.link = link;
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

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    public Artista getArtist() {
        return artist;
    }

    public void setArtist(Artista artist) {
        this.artist = artist;
    }

    public Integer getPosition() {
        return position;
    }

    public void setPosition(Integer position) {
        this.position = position;
    }

    public String getTitle_short() {
        return title_short;
    }

    public void setTitle_short(String title_short) {
        this.title_short = title_short;
    }

    public Album getAlbum() {
        return album;
    }

    public void setAlbum(Album album) {
        this.album = album;
    }

    public String getPreview() {
        return preview;
    }

    public void setPreview(String preview) {
        this.preview = preview;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }


    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cancion cancion = (Cancion) o;
        return Objects.equals(id, cancion.id);
    }

    public int hashCode() {
        return Objects.hash(id);
    }
}