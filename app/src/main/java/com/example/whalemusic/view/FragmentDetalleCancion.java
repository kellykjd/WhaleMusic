package com.example.whalemusic.view;


import android.annotation.SuppressLint;
import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatSeekBar;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.fragment.app.Fragment;

import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.whalemusic.R;
import com.example.whalemusic.controller.FirestoreController;
import com.example.whalemusic.model.Cancion;
import com.example.whalemusic.utils.MediaPlayerSingleton;
import com.example.whalemusic.utils.ResultListener;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentDetalleCancion extends Fragment{
    public static final String CLAVE_CANCION = "claveCancion";
    private TextView textViewTitle;
    private TextView textViewArtista;
    private TextView textViewAlbum;
    private TextView textViewPosicionActual;
    private TextView textViewDuracionTotal;
    private ImageView imageViewFoto;
    private Button botonBackward;
    private Button botonForward;
    private Button botonPlay;
    private Button botonCompartir;
    private Button botonFavorito;
    private Cancion cancionSeleccionada;
    private int seekForwardTime = 5000; // 5000 milliseconds
    private int seekBackwardTime = 5000; // 5000 milliseconds
    private AppCompatSeekBar seekBar;
    private MediaPlayer mediaPlayer;
    private Runnable runnable;
    private FirestoreController firestoreController;
    private Boolean esFavorita=true;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_detalle_cancion, container, false);
        encontrarVistas(view);

        Bundle bundle = getArguments();
        cancionSeleccionada = (Cancion) bundle.getSerializable(CLAVE_CANCION);
        textViewTitle.setText(cancionSeleccionada.getTitle());
        textViewArtista.setText(cancionSeleccionada.getArtist().getName());
        textViewAlbum.setText("Album: " + cancionSeleccionada.getAlbum().getTitle());
            Glide.with(getContext())
                    .load(cancionSeleccionada.getAlbum().getCover_medium())
                    .into(imageViewFoto);

        if (mediaPlayer != null) {
            mediaPlayer.stop();
        }

        mediaPlayer = MediaPlayerSingleton.getSingletonMediaPlayer();

        initMusicPlayer(0);


        botonPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                play();
            }

        });

        botonForward.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // get current song position
                int currentPosition = mediaPlayer.getCurrentPosition();
                // check if seekForward time is lesser than song duration
                if (currentPosition + seekForwardTime <= mediaPlayer.getDuration()) {
                    // forward song
                    mediaPlayer.seekTo(currentPosition + seekForwardTime);
                } else {
                    // forward to end position
                    mediaPlayer.seekTo(mediaPlayer.getDuration());
                }
            }
        });

        botonBackward.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int currentPosition = mediaPlayer.getCurrentPosition();
                // check if seekBackward time is greater than 0 sec
                if (currentPosition - seekBackwardTime >= 0) {
                    // forward song
                    mediaPlayer.seekTo(currentPosition - seekBackwardTime);
                } else {
                    // backward to starting position
                    mediaPlayer.seekTo(0);
                }
            }
        });

        botonCompartir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_SEND);
                intent.putExtra(Intent.EXTRA_TEXT, "Escuchá esta canción a través de la app Whale Music: " + cancionSeleccionada.getTitle_short() + ", " + cancionSeleccionada.getArtist().getName()+" " + cancionSeleccionada.getPreview());
                intent.setType("text/plain");
                Intent chooser = Intent.createChooser(intent, "¡Estoy compartiendo!");
                startActivity(chooser);
            }
        });


        botonFavorito.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View view) {

                if (!usuarioLogueado()) {
                    Toast.makeText(getActivity(), "Iniciá sesión para continuar", Toast.LENGTH_LONG).show();
                } else {
                    firestoreController.agregarCancionAFavoritos(cancionSeleccionada);
                    esFavorita = !esFavorita;
                    actualizarFav();
                }
            }
        });

        if (usuarioLogueado()) {
            firestoreController = new FirestoreController();
            firestoreController.traerListaDeFavorito(new ResultListener<List<Cancion>>() {
                @Override
                public void finish(List<Cancion> result) {
                    esFavorita = result.contains(cancionSeleccionada);
                    actualizarFav();

                }
            });
        }
        return view;
    }

    private void encontrarVistas(View view) {
        textViewTitle = view.findViewById(R.id.fragmentDetalleCancion_TextView_nombre);
        textViewArtista = view.findViewById(R.id.fragmentDetalleCancion_TextView_artista);
        textViewAlbum = view.findViewById(R.id.fragmentDetalleCancion_TextView_album);
        imageViewFoto = view.findViewById(R.id.fragmentDetalleCancion_ImageView_foto);
        seekBar = view.findViewById(R.id.fragmentDetalleCancion_SeekBar);
        textViewPosicionActual = view.findViewById(R.id.fragmentDetalleCancion_TextView_posicionActual);
        textViewDuracionTotal = view.findViewById(R.id.fragmentDetalleCancion_TextView_duracionTotal);
        botonBackward = view.findViewById(R.id.fragmentDetalleCancion_Button_backward);
        botonForward = view.findViewById(R.id.fragmentDetalleCancion_Button_forward);
        botonPlay = view.findViewById(R.id.fragmentDetalleCancion_Button_play);
        botonCompartir = view.findViewById(R.id.fragmentDetalleCancion_Button_compartir);
        botonFavorito = view.findViewById(R.id.fragmentDetalleCancion_Button_favorito);
    }


    private Boolean usuarioLogueado() {
        Boolean usuario = false;
        if (FirebaseAuth.getInstance().getCurrentUser() != null) {
            usuario = true;
        }
        return usuario;
    }

    private void actualizarFav() {
        if (esFavorita) {
            botonFavorito.setBackgroundResource(R.drawable.ic_favorite_clicked);
        } else {
            botonFavorito.setBackgroundResource(R.drawable.ic_favorite_empty);
        }
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    private void initMusicPlayer(int position) {
        if (mediaPlayer != null && mediaPlayer.isPlaying()) {
            mediaPlayer.reset();
        }

        mediaPlayer = MediaPlayer.create(getContext(), Uri.parse(cancionSeleccionada.getPreview()));

        mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mediaPlayer) {

                seekBar.setMax(mediaPlayer.getDuration());

                String totalTime = createTimerLabel(mediaPlayer.getDuration());
                textViewDuracionTotal.setText(totalTime);
                mediaPlayer.start();
                botonPlay.setBackgroundResource(R.drawable.ic_pause);

            }
        });

        mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mediaPlayer) {
                botonPlay.setBackgroundResource(R.drawable.ic_play);
            }
        });

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if (fromUser) {
                    mediaPlayer.seekTo(progress);
                    seekBar.setProgress(progress);
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    if (mediaPlayer==null){

                    }

                while (mediaPlayer != null) {

                        if (mediaPlayer.isPlaying()) {
                            Message message = new Message();
                            message.what = mediaPlayer.getCurrentPosition();
                            handler.sendMessage(message);
                            try {
                                Thread.sleep(1000);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                        }
                    } catch (Exception e) {
                    e.printStackTrace();
                    }


            }
        }).start();

    }

    @SuppressLint("HandlerLeak")
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(@NonNull Message msg) {
            textViewPosicionActual.setText(createTimerLabel(msg.what));
            seekBar.setProgress(msg.what);
        }
    };

    private void play() {
        if (mediaPlayer != null && mediaPlayer.isPlaying()) {
            mediaPlayer.pause();
            botonPlay.setBackgroundResource(R.drawable.ic_play);
        } else {
            mediaPlayer.start();
            botonPlay.setBackgroundResource(R.drawable.ic_pause);
        }
    }

    public String createTimerLabel(int duration) {
        String timeLabel = "";
        int min = duration / 1000 / 60;
        int sec = duration / 1000 % 60;
        timeLabel += min + ":";

        if (sec < 10) timeLabel += "0";

        timeLabel += sec;

        return timeLabel;
    }


    @Override
    public void onResume() {
        super.onResume();
        mediaPlayer.start();
    }

    @Override
    public void onPause() {
        if (mediaPlayer != null) {
            mediaPlayer.pause();
            mediaPlayer.stop();
        }
        super.onPause();
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        mediaPlayer.release();
        handler.removeCallbacks(runnable);
    }
}
