package com.example.whalemusic.view;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.whalemusic.R;
import com.example.whalemusic.model.Album;

import java.util.ArrayList;
import java.util.List;

public class AdapterAlbum extends RecyclerView.Adapter<AdapterAlbum.ViewHolderAlbumes>{

private List<Album> albumList;
private ListenerDelAdapterAlbum listenerDelAdapterAlbum;



public AdapterAlbum(List<Album> albumList) {
        this.albumList = albumList;
        }


public AdapterAlbum(ListenerDelAdapterAlbum listenerDelAdapterAlbum) {
        albumList = new ArrayList<>();
        this.listenerDelAdapterAlbum = listenerDelAdapterAlbum;
        }

@NonNull
@Override
public ViewHolderAlbumes onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View vistaDeLaCelda =layoutInflater.inflate(R.layout.celda_album,parent,false);
        return new ViewHolderAlbumes(vistaDeLaCelda);
        }

@Override
public void onBindViewHolder(@NonNull ViewHolderAlbumes holder, int position) {
        Album albumMostrado = albumList.get(position);
        holder.cargarAlbum(albumMostrado);

        }

@Override
public int getItemCount() {
        return albumList.size();
        }

public void setAlbumList(List<Album> albumList){
        this.albumList = albumList;
        notifyDataSetChanged();
        }


public class ViewHolderAlbumes extends RecyclerView.ViewHolder{
    private TextView textViewTitle;
    private TextView textViewArtista;
    private ImageView imageViewFoto;


    public ViewHolderAlbumes(@NonNull View itemView) {
        super(itemView);
        textViewTitle = itemView.findViewById(R.id.celdaAlbum_textView_titulo);
        textViewArtista = itemView.findViewById(R.id.celdaAlbum_textView_artista);
        imageViewFoto = itemView.findViewById(R.id.celdaAlbum_imageView_cover);
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Album albumSeleccionado = albumList.get(getAdapterPosition());
                listenerDelAdapterAlbum.informarAlbumSeleccionado(albumSeleccionado);
            }
        });
    }

    public void cargarAlbum(Album album){
        textViewTitle.setText(album.getTitle());
        textViewArtista.setText(album.getArtist().getName());
        Glide.with(itemView)
                .load(album.getCover_medium())
                .into(imageViewFoto);
    }
}

public interface ListenerDelAdapterAlbum{
    void informarAlbumSeleccionado(Album album);
}
}


