package com.example.whalemusic.view;

import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import com.example.whalemusic.R;
import com.example.whalemusic.model.Cancion;
import com.example.whalemusic.utils.MediaPlayerSingleton;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class AdapterCancion extends RecyclerView.Adapter<AdapterCancion.ViewHolderCancion> {

    private static final int MSG_UPDATE_SEEK_BAR = 1845;
    private Handler uiUpdateHandler;
    private List<Cancion> cancionList;
    private MediaPlayer mediaPlayer;
    private Boolean primeraVez = true;
    private ListenerDelAdapterCancion listenerDelAdapterCancion;

    public AdapterCancion(ListenerDelAdapterCancion listenerDelAdapterCancion) {
        cancionList = new ArrayList<>();
        this.listenerDelAdapterCancion = listenerDelAdapterCancion;
        mediaPlayer = MediaPlayerSingleton.getSingletonMediaPlayer();
    }

    @NonNull
    @Override
    public ViewHolderCancion onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View vistaDeLaCelda = layoutInflater.inflate(R.layout.celda_cancion, parent, false);
        return new ViewHolderCancion(vistaDeLaCelda);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderCancion holder, int position) {
        Cancion cancionMostrada = cancionList.get(position);
         holder.cargarCancion(cancionMostrada);
    }

    @Override
    public int getItemCount() {
        return cancionList.size();
    }

    public void setCancionList(List<Cancion> cancionList) {
        this.cancionList = cancionList;
        notifyDataSetChanged();
    }

    public class ViewHolderCancion extends RecyclerView.ViewHolder {
        private TextView textViewTitulo;
        private TextView textViewArtista;
        private TextView textViewAlbum;
        private Button buttonPlayMusic;

        public ViewHolderCancion(@NonNull final View itemView) {
            super(itemView);
            textViewTitulo = itemView.findViewById(R.id.celdaCancion_textView_titulo);
            textViewArtista = itemView.findViewById(R.id.celdaCancion_textView_artista);
            textViewAlbum = itemView.findViewById(R.id.celdaCancion_textView_album);
            buttonPlayMusic = itemView.findViewById(R.id.celdaCancion_button_playMusic);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Cancion cancionSeleccionada = cancionList.get(getAdapterPosition());
                    listenerDelAdapterCancion.informarCancionSeleccionada(cancionSeleccionada);
                }
            });

            buttonPlayMusic.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {


                    String url;

                    if (mediaPlayer.isPlaying()) {
                        buttonPlayMusic.setBackgroundResource(R.drawable.ic_play_circle);
                        mediaPlayer.stop();
                        mediaPlayer.reset();
                    } else {

                        buttonPlayMusic.setBackgroundResource(R.drawable.ic_pause_circle);
						Cancion cancionSeleccionada = cancionList.get(getAdapterPosition());
                        url = cancionSeleccionada.getPreview();
                        mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
                        try {
                            mediaPlayer.setDataSource(url);
                            mediaPlayer.prepareAsync(); // prepare async to not block main thread

                        } catch (IOException e) {
                            Toast.makeText(itemView.getContext(), "Mp3 not found", Toast.LENGTH_SHORT).show();
                            e.printStackTrace();
                        }
                    }
                    //mp3 will be started after completion of preparing...
                    mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                        @Override
                        public void onPrepared(MediaPlayer player) {
                            if (mediaPlayer!=null) {
                                buttonPlayMusic.setBackgroundResource(R.drawable.ic_pause_circle);
                                player.start();
                            }
                        }
                    });



                    mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                        @Override
                        public void onCompletion(MediaPlayer mediaPlayer) {
                            buttonPlayMusic.setBackgroundResource(R.drawable.ic_play_circle);
                            mediaPlayer.stop();
                            mediaPlayer.reset();
                        }
                    });
                }

            });


        }

        public void cargarCancion(Cancion cancion) {
            textViewTitulo.setText(cancion.getTitle_short());
            textViewArtista.setText(cancion.getArtist().getName());
            if (cancion.getAlbum()!=null) {
                textViewAlbum.setText(cancion.getAlbum().getTitle());
            }else{
                textViewAlbum.setText("");
            }
        }
    }

    public interface ListenerDelAdapterCancion {
        public void informarCancionSeleccionada(Cancion cancion);
    }

}
