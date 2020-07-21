package com.example.whalemusic.model;

import androidx.room.TypeConverter;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.Collections;
import java.util.List;

public class Converter {

    @TypeConverter
    public static Artista stringToArtista(String string){
        if(string.isEmpty()){
            return null;
        }
        return new Gson().fromJson(string, Artista.class);

    }

    @TypeConverter
    public static String artistaToString(Artista artista) {
        return new Gson().toJson(artista);
    }


    @TypeConverter
    public static Cancion stringToCancion(String string){
        if(string.isEmpty()){
            return null;
        }
        return new Gson().fromJson(string, Cancion.class);

    }

    @TypeConverter
    public static String artistaToString(Cancion cancion) {
        return new Gson().toJson(cancion);
    }

    @TypeConverter
    public static Album stringToAlbum(String string){
        if(string.isEmpty()){
            return null;
        }
        return new Gson().fromJson(string, Album.class);

    }

    @TypeConverter
    public static String albumToString(Album album) {
        return new Gson().toJson(album);
    }

}
