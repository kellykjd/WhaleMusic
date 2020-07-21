

package com.example.whalemusic.view;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;


import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.whalemusic.R;
import com.example.whalemusic.model.Album;
import com.example.whalemusic.model.Artista;
import com.example.whalemusic.model.Cancion;
import com.example.whalemusic.model.Genero;
import com.example.whalemusic.model.Playlist;
import com.example.whalemusic.utils.Connectivity;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;


public class MainActivity extends AppCompatActivity implements FragmentListas.ListenerDeFragment, FragmentResultados.ListenerDeFragment, FragmentDetalleGenero.ListenerDeFragment, NavigationView.OnNavigationItemSelectedListener, FragmentDetalleArtista.ListenerDeFragment, FragmentDetalleAlbum.ListenerDeFragment, FragmentDetallePlaylist.ListenerDeFragment {

    private BottomNavigationView buttomNavigationView;
    private Toolbar toolbar;
    private Connectivity connectivity;
    private DrawerLayout drawerLayout;
    private NavigationView navigationView;
    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.AppTheme);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        drawerLayout = findViewById(R.id.mainActivity_drawerLayout);
        navigationView = findViewById(R.id.navigationView);
        toolbar = findViewById(R.id.toolbar);
        buttomNavigationView = findViewById(R.id.bottomNavigationView);
        cargarNavigationView();

        SharedPreferences preferences = getSharedPreferences("preferences", MODE_PRIVATE);
        Boolean primeraYUnicaVez = preferences.getBoolean("primeraYUnicaVez", true);

        if (primeraYUnicaVez) {
            mostrarOnboarding();
        }
        pegarFragment(new FragmentListas(),false);

        buttomNavigationView.setSelectedItemId(R.id.navigationButtom_inicio);
        buttomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                int itemId = menuItem.getItemId();
                switch (itemId){
                    case R.id.navigationButtom_inicio:
                        pegarFragment(new FragmentListas(),false);
                        break;
                    case R.id.navigationButtom_buscar:
                        pegarFragment(new FragmentResultados(),true);
                        break;
                    case R.id.navigationButtom_favoritos:
                        Intent intent  = new Intent(MainActivity.this, ActivityBibliotecasViewPager.class );
                        startActivity(intent);
                        break;
                }
                return true;
            }
        });
    }

    public void mostrarOnboarding(){
        Intent intentOnboarding = new Intent(MainActivity.this, OnBoardingActivity.class);
        startActivity(intentOnboarding);
        SharedPreferences preferences = getSharedPreferences("preferences", MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putBoolean("primeraYUnicaVez", false);
        editor.apply();
    }

    private void cargarNavigationView(){
        ActionBarDrawerToggle toggle =
                new ActionBarDrawerToggle(this,
                        drawerLayout,
                        toolbar,
                        R.string.open_drawer,
                        R.string.close_drawer);

        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);
    }


    private void pegarFragment(Fragment fragment, boolean addToBackStack){
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        if (addToBackStack){
            fragmentTransaction.addToBackStack(null);
        }
        fragmentTransaction.replace(R.id.mainActivity_contenedorFragments,fragment,null)
        .commitAllowingStateLoss();
    }

    private void mostrarDetallesArtista(Artista artista){
        FragmentDetalleArtista fragmentDetalleArtista = new FragmentDetalleArtista();
        Bundle bundle = new Bundle();
        bundle.putSerializable(FragmentDetalleArtista.CLAVE_ARTISTA,artista);
        fragmentDetalleArtista.setArguments(bundle);
        pegarFragment(fragmentDetalleArtista,true);

    }

    private void mostrarDetallesCancion(Cancion cancion){
        FragmentDetalleCancion fragmentDetalleCancion = new FragmentDetalleCancion();
        Bundle bundle = new Bundle();
        bundle.putSerializable(FragmentDetalleCancion.CLAVE_CANCION,cancion);
        fragmentDetalleCancion.setArguments(bundle);
        pegarFragment(fragmentDetalleCancion,true);
    }

    @Override
    public void recibirArtista(Artista artista) {
        mostrarDetallesArtista(artista);

    }

    @Override
    public void recibirCancion(Cancion cancion) {
        mostrarDetallesCancion(cancion);
    }

    @Override
    public void recibirGenero(Genero genero) {
        FragmentDetalleGenero fragmentDetalleGenero = new FragmentDetalleGenero();
        Bundle bundle = new Bundle();
        bundle.putSerializable(FragmentDetalleGenero.CLAVE_GENERO,genero);
        fragmentDetalleGenero.setArguments(bundle);
        pegarFragment(fragmentDetalleGenero,true);
    }

    @Override
    public void recibirBusqueda(String unaPalabra) {
        FragmentResultados fragmentResultados = new FragmentResultados();
        Bundle bundle = new Bundle();
        bundle.putString(FragmentResultados.CLAVE_PALABRA,unaPalabra);
        fragmentResultados.setArguments(bundle);
        pegarFragment(fragmentResultados,true);

    }

    @Override
    public void recibirPlaylist(Playlist playlist) {
        FragmentDetallePlaylist fragmentDetallePlaylist = new FragmentDetallePlaylist();
        Bundle bundle = new Bundle();
        bundle.putSerializable(FragmentDetallePlaylist.CLAVE_PLAYLIST,playlist);
        fragmentDetallePlaylist.setArguments(bundle);
        pegarFragment(fragmentDetallePlaylist,true);
    }

    @Override
    public void mostrarArtistaBuscado(Artista artista) {
        mostrarDetallesArtista(artista);
    }

    @Override
    public void mostrarCancionBuscada(Cancion cancion) {
        mostrarDetallesCancion(cancion);
    }

    @Override
    public void mostrarAlbumBuscado(Album album) {
        FragmentDetalleAlbum fragmentDetalleAlbum = new FragmentDetalleAlbum();
        Bundle bundle = new Bundle();
        bundle.putSerializable(FragmentDetalleAlbum.CLAVE_ALBUM,album);
        fragmentDetalleAlbum.setArguments(bundle);
        pegarFragment(fragmentDetalleAlbum,true);
    }

    @Override
    public void onBackPressed() {
//        getSupportActionBar().show();
        drawerLayout.closeDrawers();
        super.onBackPressed();
    }


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        Integer id = menuItem.getItemId();
        switch (id) {
            case R.id.menu_lateral_iniciarSesion:
                Toast.makeText(this, "Ingresa a tu cuenta!", Toast.LENGTH_SHORT).show();
                Intent intent  = new Intent(this, LoginActivity.class );
                startActivity(intent);
                break;
            case R.id.menu_lateral_cerrarSesion:
                FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
                firebaseAuth.signOut();
                Toast.makeText(this, "Adios!", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(this, MainActivity.class));
                break;
    }
        drawerLayout.closeDrawers();
        return true;
    }

}