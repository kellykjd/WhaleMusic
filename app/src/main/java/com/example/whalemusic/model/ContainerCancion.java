package com.example.whalemusic.model;

import java.util.ArrayList;
import java.util.List;

public class ContainerCancion {

    private List<Cancion> data;

    public List<Cancion> getData() {
        return data;
    }

    public ContainerCancion() {
        data = new ArrayList<>();
    }

    public void setData(List<Cancion> data) {
        this.data = data;
    }


    public void agregarCancion(Cancion cancion){
        data.add(cancion);
    }
    public void removerCancion(Cancion cancion){
        data.remove(cancion);
    }

    public Boolean contieneLaCancion(Cancion cancion){
        return data.contains(cancion);
    }
}