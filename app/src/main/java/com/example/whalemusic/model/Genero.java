
package com.example.whalemusic.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
@Entity(tableName = "genero")
public class Genero implements Serializable {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name="id")
    private Integer id;
    @ColumnInfo
    private String name;
    @ColumnInfo
    private String picture_small;
    @ColumnInfo
    private String picture_medium;

    @Ignore
    public Genero() {

    }

    public Genero(Integer id, String name, String picture_small, String picture_medium) {
        this.id = id;
        this.name = name;
        this.picture_small = picture_small;
        this.picture_medium=picture_medium;
    }

    public String getPicture_medium() {
        return picture_medium;
    }

    public void setPicture_medium(String picture_medium) {
        this.picture_medium = picture_medium;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPicture_small() {
        return picture_small;
    }

    public void setPicture_small(String picture_small) {
        this.picture_small = picture_small;
    }
}
