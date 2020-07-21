package com.example.whalemusic.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity(tableName = "artista")
public class Artista implements Serializable {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name="id")
    private Integer id;
    @ColumnInfo
    private String name;
    @ColumnInfo
    private String link;
    @ColumnInfo
    private String share;
    @ColumnInfo
    private String picture_small;
    @ColumnInfo
    private String picture_medium;
    @ColumnInfo
    private String picture_big;
    @ColumnInfo
    private Integer nb_fan;
    @ColumnInfo
    private String tracklist;

    public Artista(Integer id, String name, String link, String share, String picture_small, String picture_medium, String picture_big, Integer nb_fan, String tracklist) {
        this.id = id;
        this.name = name;
        this.link = link;
        this.share = share;
        this.picture_small = picture_small;
        this.picture_medium = picture_medium;
        this.picture_big = picture_big;
        this.nb_fan = nb_fan;
        this.tracklist = tracklist;
    }

    @Ignore
    public Artista() {

    }


    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getLink() {
        return link;
    }

    public String getShare() {
        return share;
    }

    public String getPicture_small() {
        return picture_small;
    }

    public String getPicture_medium() {
        return picture_medium;
    }

    public String getPicture_big() {
        return picture_big;
    }

    public Integer getNb_fan() {
        return nb_fan;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public void setShare(String share) {
        this.share = share;
    }

    public void setPicture_small(String picture_small) {
        this.picture_small = picture_small;
    }

    public void setPicture_medium(String picture_medium) {
        this.picture_medium = picture_medium;
    }

    public void setPicture_big(String picture_big) {
        this.picture_big = picture_big;
    }

    public void setNb_fan(Integer nb_fan) {
        this.nb_fan = nb_fan;
    }

    public String getTracklist() {
        return tracklist;
    }

    public void setTracklist(String tracklist) {
        this.tracklist = tracklist;
    }
}