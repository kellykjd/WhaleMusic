package com.example.whalemusic.model;

import java.io.Serializable;

public class BibliotecaViewPager implements Serializable {

    private String nombreDeLaBiblioteca;

    public BibliotecaViewPager(String nombreDeLaBiblioteca) {
        this.nombreDeLaBiblioteca = nombreDeLaBiblioteca;

    }

    public String getNombreDeLaBiblioteca() {
        return nombreDeLaBiblioteca;
    }
}
