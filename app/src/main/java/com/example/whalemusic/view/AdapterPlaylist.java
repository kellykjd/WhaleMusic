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
import com.example.whalemusic.model.Playlist;

import java.util.ArrayList;
import java.util.List;

public class AdapterPlaylist extends RecyclerView.Adapter<AdapterPlaylist.ViewHolderPlaylist>{

    private List<Playlist> playlistList;
    private ListenerDelAdapterPlaylist listenerDelAdapterPlaylist;


    public AdapterPlaylist(ListenerDelAdapterPlaylist listenerDelAdapterPlaylist) {
        playlistList = new ArrayList<>();
        this.listenerDelAdapterPlaylist = listenerDelAdapterPlaylist;
    }

    @NonNull
    @Override
    public ViewHolderPlaylist onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View vistaDeLaCelda =layoutInflater.inflate(R.layout.celda_playlist,parent,false);
        return new ViewHolderPlaylist(vistaDeLaCelda);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderPlaylist holder, int position) {
        Playlist playlistMostrado = playlistList.get(position);
        holder.cargarPlaylist(playlistMostrado);

    }

    @Override
    public int getItemCount() {
        return playlistList.size();
    }

    public void setPlaylistList(List<Playlist> playlistList){
        this.playlistList = playlistList;
        notifyDataSetChanged();
    }


    public class ViewHolderPlaylist extends RecyclerView.ViewHolder{
        private TextView textViewNombre;
        private ImageView imageViewFoto;


        public ViewHolderPlaylist(@NonNull View itemView) {
            super(itemView);
            textViewNombre = itemView.findViewById(R.id.celdaPlaylist_textView_titulo);
            imageViewFoto = itemView.findViewById(R.id.celdaPlaylist_imageView_cover);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Playlist playlistSeleccionado = playlistList.get(getAdapterPosition());
                    listenerDelAdapterPlaylist.informarPlaylistSeleccionado(playlistSeleccionado);
                }
            });
        }

        public void cargarPlaylist(Playlist playlist){
            textViewNombre.setText(playlist.getTitle());
            Glide.with(itemView)
                    .load(playlist.getPicture_medium())
                    .into(imageViewFoto);

        }
    }

    public interface ListenerDelAdapterPlaylist{
        public void informarPlaylistSeleccionado(Playlist playlist);
    }
}