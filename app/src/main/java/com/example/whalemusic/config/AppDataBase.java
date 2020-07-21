
package com.example.whalemusic.config;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import com.example.whalemusic.dao.room.RoomDao;
import com.example.whalemusic.model.Album;
import com.example.whalemusic.model.Artista;
import com.example.whalemusic.model.Cancion;
import com.example.whalemusic.model.Converter;
import com.example.whalemusic.model.Genero;

@Database(version = 2, entities = {Cancion.class, Genero.class, Artista.class}, exportSchema = false)
@TypeConverters({Converter.class})
public abstract class AppDataBase extends RoomDatabase {

    public abstract RoomDao roomDao();

    public static AppDataBase INSTANCE = null;

    public static AppDataBase getInstance(Context context) {

/*Primero pregunto si sigue siendo null, para generar la Database.
        Una vez generada ya no va a volver a entrar en este método porque ya no vale null.
        Esa es la magia del SINGLETON (Patrón de diseño).*/

        if (INSTANCE == null) {
            INSTANCE = Room
                    .databaseBuilder(context,
                            AppDataBase.class,
                            "db-listas")
                    .fallbackToDestructiveMigration()
                    .allowMainThreadQueries()
                    .build();
        }

        return INSTANCE;
    }
}

