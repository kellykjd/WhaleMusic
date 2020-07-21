package com.example.whalemusic.dao.retrofit;

import androidx.annotation.NonNull;

import com.example.whalemusic.model.Cancion;
import com.example.whalemusic.model.ContainerCancion;
import com.example.whalemusic.utils.ResultListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.List;

public class FirestoreDao {
    public static final String CANCIONES_FAVORITAS = "cancionesFavoritas";
    private FirebaseFirestore firestore;
    private FirebaseUser currentUser;
    private ContainerCancion containerCancion;


    public FirestoreDao(){
        firestore = FirebaseFirestore.getInstance();
        currentUser = FirebaseAuth.getInstance().getCurrentUser();

        traerCancionesFavoritas();
    }
    public void agregarCancionAFavoritos(Cancion cancion){
        if (containerCancion == null) {
            containerCancion = new ContainerCancion();
        }
        if (containerCancion.contieneLaCancion(cancion)){
            containerCancion.removerCancion(cancion);
        }
        else {
            containerCancion.agregarCancion(cancion);
        }
        firestore.collection(CANCIONES_FAVORITAS)
                .document(currentUser.getUid())
               .set(containerCancion);
}

    private void traerCancionesFavoritas() {
        firestore.collection(CANCIONES_FAVORITAS).
                document(currentUser.getUid()).
                get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            //en el listener del on succes intento tranfomar el documento a un container
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                containerCancion = documentSnapshot.toObject(ContainerCancion.class);
                if(containerCancion == null){
                    containerCancion = new ContainerCancion();
                }
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            //en el on failure del listener inicializo un container vacio

            public void onFailure(@NonNull Exception e) {
                containerCancion = new ContainerCancion();
            }
        });
    }
    public void traerCancionesFavoritas(final ResultListener<List<Cancion>> listenerDelController){
        firestore.collection(CANCIONES_FAVORITAS)
                .document(currentUser.getUid())
                .get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            //en el listener del on succes intento tranfomar el documento a un container
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                containerCancion = documentSnapshot.toObject(ContainerCancion.class);
                if (containerCancion == null) {
                    containerCancion = new ContainerCancion();
                }
                listenerDelController.finish(containerCancion.getData());
            }
        })
            .addOnFailureListener(new OnFailureListener() {
            @Override
            //en el on failure del listener inicializo un container vacio
            public void onFailure(@NonNull Exception e) {
                containerCancion = new ContainerCancion();
                listenerDelController.finish(containerCancion.getData());
            }
        });
    }
}