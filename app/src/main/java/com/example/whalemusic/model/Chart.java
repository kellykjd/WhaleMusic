package com.example.whalemusic.model;

public class Chart {

    ContainerArtista artists;
    ContainerCancion tracks;

    public ContainerArtista getArtists() {
        return artists;
    }

    public void setArtists(ContainerArtista artists) {
        this.artists = artists;
    }

    public ContainerCancion getTracks() {
        return tracks;
    }

    public void setTracks(ContainerCancion tracks) {
        this.tracks = tracks;
    }

}
