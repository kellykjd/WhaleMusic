package com.example.whalemusic.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ContainerArtista {

    private List<Artista> data;

    public List<Artista> getData() {
        return data;
    }

    public void setData(List<Artista> data) {
        this.data = data;
    }
}
