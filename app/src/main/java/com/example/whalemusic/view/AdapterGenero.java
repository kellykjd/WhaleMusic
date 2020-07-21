
package com.example.whalemusic.view;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.whalemusic.model.Genero;
import com.example.whalemusic.R;

import java.util.ArrayList;
import java.util.List;

public class AdapterGenero extends RecyclerView.Adapter<AdapterGenero.ViewHolderGenero>{

private List<Genero> generoList;
private ListenerDelAdapterGenero listenerDelAdapterGenero;

public AdapterGenero(List<Genero> generoList) {
        this.generoList = generoList;
        }

public AdapterGenero(ListenerDelAdapterGenero listenerDelAdapterGenero) {
        generoList = new ArrayList<>();
        this.listenerDelAdapterGenero = listenerDelAdapterGenero;
        }

@NonNull
@Override
public ViewHolderGenero onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View vistaDeLaCelda =layoutInflater.inflate(R.layout.celda_genero,parent,false);
        return new ViewHolderGenero(vistaDeLaCelda);
        }

@Override
public void onBindViewHolder(@NonNull ViewHolderGenero holder, int position) {
        Genero generoMostrado = generoList.get(position);
        holder.cargarGenero(generoMostrado);

        }

@Override
public int getItemCount() {
        return generoList.size();
        }

public void setGeneroList(List<Genero> generoList){
        this.generoList = generoList;
        notifyDataSetChanged();
        }


public class ViewHolderGenero extends RecyclerView.ViewHolder{
    private TextView textViewNombre;
    private ImageView imageViewFoto;


    public ViewHolderGenero(@NonNull View itemView) {
        super(itemView);
        textViewNombre = itemView.findViewById(R.id.celdaGenero_textView_nombre);
        imageViewFoto = itemView.findViewById(R.id.celdaGenero_imageView_foto);
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Genero generoSeleccionado = generoList.get(getAdapterPosition());
                listenerDelAdapterGenero.informarGeneroSeleccionado(generoSeleccionado);
            }
        });
    }

    public void cargarGenero(Genero genero){
        textViewNombre.setText(genero.getName());
        Glide.with(itemView)
                .load(genero.getPicture_medium())
                .into(imageViewFoto);

    }
}

public interface ListenerDelAdapterGenero{
    public void informarGeneroSeleccionado(Genero genero);
}
}