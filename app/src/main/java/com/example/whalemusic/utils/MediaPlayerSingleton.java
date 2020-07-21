package com.example.whalemusic.utils;

import android.media.MediaPlayer;

public class MediaPlayerSingleton {

    private static MediaPlayer mediaPlayer;


    public static MediaPlayer getSingletonMediaPlayer() {
        if (mediaPlayer == null) {
            mediaPlayer = new MediaPlayer();
        }
        return mediaPlayer;
    }

}

