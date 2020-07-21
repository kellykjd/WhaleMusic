package com.example.whalemusic.view;


import android.content.Context;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.Bundle;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatSeekBar;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;


import com.bumptech.glide.Glide;
import com.example.whalemusic.R;
import com.example.whalemusic.controller.CancionController;
import com.example.whalemusic.model.Artista;
import com.example.whalemusic.model.Cancion;
import com.example.whalemusic.utils.MediaPlayerSingleton;
import com.example.whalemusic.utils.ResultListener;

import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentDetalleArtista extends Fragment implements AdapterCancion.ListenerDelAdapterCancion{

    public static final String CLAVE_ARTISTA="claveArtista";
    private RecyclerView recyclerViewCanciones;
    private TextView textViewNombre;
    private TextView textViewAlbumes;
    private ImageView imageViewFoto;
    private ListenerDeFragment listenerDeFragment;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_detalle_artista, container, false);
        encontrarVistas(view);

        Bundle bundle = getArguments();
        Artista artistaSeleccionado = (Artista)bundle.getSerializable(CLAVE_ARTISTA);
        Glide.with(getContext())
                .load(artistaSeleccionado.getPicture_medium())
                .into(imageViewFoto);
        textViewNombre.setText(artistaSeleccionado.getName());


        final AdapterCancion adapterCancion = new AdapterCancion(this);
        CancionController cancionController = new CancionController(getActivity());
        cancionController.traerCancionesTopArtista(artistaSeleccionado.getId(), new ResultListener<List<Cancion>>() {
            @Override
            public void finish(List<Cancion> results) {
                adapterCancion.setCancionList(results);
            }
        });

        recyclerViewCanciones.setLayoutManager(new LinearLayoutManager(getContext(),RecyclerView.VERTICAL,false));
        recyclerViewCanciones.setAdapter(adapterCancion);

        return view;
    }



    private void encontrarVistas(View view){
        textViewNombre = view.findViewById(R.id.fragmentDetalleArtista_TextView_nombre);
        //textViewAlbumes = view.findViewById(R.id.fragmentDetalleArtista_TextView_albumes);
        imageViewFoto = view.findViewById(R.id.fragmentDetalleArtista_ImageView_foto);
        recyclerViewCanciones = view.findViewById(R.id.fragmentDetalleArtista_RecyclerView);

    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        listenerDeFragment = (ListenerDeFragment) context;
    }

    @Override
    public void informarCancionSeleccionada(Cancion cancion) {
        listenerDeFragment.recibirCancion(cancion);
    }

    public interface ListenerDeFragment {
        void recibirCancion(Cancion cancion);
    }
}