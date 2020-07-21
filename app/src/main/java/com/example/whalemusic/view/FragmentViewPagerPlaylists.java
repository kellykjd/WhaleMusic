package com.example.whalemusic.view;
import android.content.Intent;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import com.example.whalemusic.R;
import com.example.whalemusic.controller.FirestoreController;
import com.example.whalemusic.model.Cancion;
import com.example.whalemusic.dao.retrofit.FirestoreDao;
import com.example.whalemusic.utils.ResultListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;
import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentViewPagerPlaylists extends Fragment implements AdapterCancion.ListenerDelAdapterCancion {
    private FirebaseAuth mAuth;
    private RecyclerView recyclerViewCancionFavoritos;
    private FirebaseFirestore firestore;
    private FirestoreDao firestoreDao;
    private LinearLayoutManager linearLayoutManager;
    private AdapterCancion adapterCancion;
    private ArrayList<Cancion> listaDeCancionesFavoritos = new ArrayList<>();
    private FirebaseUser currentUser;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View vistaDelFragment = inflater.inflate(R.layout.fragment_view_pager_playlists, container, false);
        recyclerViewCancionFavoritos = vistaDelFragment.findViewById(R.id.viewpagerPlaylistRecycler);
        linearLayoutManager = new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false);
        recyclerViewCancionFavoritos.setLayoutManager(linearLayoutManager);
        adapterCancion = new AdapterCancion(this);
        recyclerViewCancionFavoritos.setAdapter(adapterCancion);
        firestore = FirebaseFirestore.getInstance();
        mAuth = FirebaseAuth.getInstance();
        currentUser = mAuth.getCurrentUser();
        if (currentUser != null) {
        } else {
            Intent intent = new Intent(getContext(), LoginActivity.class);
            startActivity(intent);
            Toast.makeText(getContext(), "No está logueado", Toast.LENGTH_SHORT).show();
        }

        return vistaDelFragment;
}
    @Override
    public void onStart() {
        super.onStart();
        if(currentUser == null){
            Intent intent = new Intent(getContext(), LoginActivity.class);
            startActivity(intent);
            Toast.makeText(getContext(), "Debes loguearte para acceder a favoritos", Toast.LENGTH_SHORT).show();
        } else {
            final FirestoreController firestoreController = new FirestoreController();
            firestoreController.traerListaDeFavorito(new ResultListener<List<Cancion>>() {
                @Override
                public void finish(List<Cancion> results) {
                    if (results == null) {
                        Toast.makeText(getContext(), "Aun no tiene favoritos", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(getContext(), "Favoritos", Toast.LENGTH_SHORT).show();
                        firestoreController.traerListaDeFavorito(new ResultListener<List<Cancion>>() {
                            @Override
                            public void finish(List<Cancion> results) {
                                adapterCancion.setCancionList(results);
                            }
                        });
                        recyclerViewCancionFavoritos.setAdapter(adapterCancion);
                        recyclerViewCancionFavoritos.setLayoutManager(linearLayoutManager);
                    }
                }
            });
        }
    }

    @Override
    public void informarCancionSeleccionada(Cancion cancion) {
    }
}