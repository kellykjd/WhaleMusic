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
import com.example.whalemusic.model.Artista;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class AdapterArtista extends RecyclerView.Adapter<AdapterArtista.ViewHolderArtistas>{

    private List<Artista> artistaList;
    private ListenerDelAdapterArtista listenerDelAdapterArtista;



        public AdapterArtista(ListenerDelAdapterArtista listenerDelAdapterArtista) {
        artistaList = new ArrayList<>();
        this.listenerDelAdapterArtista = listenerDelAdapterArtista;
    }

    @NonNull
    @Override
    public ViewHolderArtistas onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View vistaDeLaCelda =layoutInflater.inflate(R.layout.celda_artista,parent,false);
        return new ViewHolderArtistas(vistaDeLaCelda);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderArtistas holder, int position) {
        Artista artistaMostrado = artistaList.get(position);
        holder.cargarArtista(artistaMostrado);

    }

    @Override
    public int getItemCount() {
        return artistaList.size();
    }

    public void setArtistaList(List<Artista> artistaList){
        this.artistaList = artistaList;
        notifyDataSetChanged();
    }


    public class ViewHolderArtistas extends RecyclerView.ViewHolder{
        private TextView textViewNombre;
        private ImageView imageViewFoto;


        public ViewHolderArtistas(@NonNull View itemView) {
            super(itemView);
            textViewNombre = itemView.findViewById(R.id.celdaArtista_textView_nombre);
            imageViewFoto = itemView.findViewById(R.id.celdaArtista_imageView_foto);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Artista artistaSeleccionado = artistaList.get(getAdapterPosition());
                    listenerDelAdapterArtista.informarArtistaSeleccionado(artistaSeleccionado);
                }
            });
        }

        public void cargarArtista(Artista artista){
            textViewNombre.setText(artista.getName());
            Glide.with(itemView)
                    .load(artista.getPicture_medium())
                    .into(imageViewFoto);
        }
    }

    public interface ListenerDelAdapterArtista{
        public void informarArtistaSeleccionado(Artista artista);
    }
}
