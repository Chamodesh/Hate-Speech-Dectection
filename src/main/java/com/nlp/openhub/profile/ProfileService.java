package com.nlp.openhub.profile;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;
import com.google.firebase.cloud.FirestoreClient;
import com.nlp.openhub.classes.Vlog;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

@Service
public class ProfileService {
    private static final String COLLECTION_NAME = "vlogs";

    public List<Vlog> getAllVlogs(@RequestParam String username) throws ExecutionException, InterruptedException {
        Firestore dbFirestore = FirestoreClient.getFirestore();

        List<Vlog> vlogList = new ArrayList<>();
        Vlog vlog = null;

        Query query = dbFirestore.collection(COLLECTION_NAME).whereEqualTo("username", username);
        ApiFuture<QuerySnapshot> future = query.get();

        List<QueryDocumentSnapshot> documents = future.get().getDocuments();
        for (QueryDocumentSnapshot document : documents) {
            vlog = document.toObject(Vlog.class);
            vlogList.add(vlog);
        }
        return vlogList;
    }

    //service to delete an ad object in the database
    public boolean deleteVlog(@RequestParam String topic) throws ExecutionException, InterruptedException {
        Firestore dbFirestore = FirestoreClient.getFirestore();
        try
        {
            ApiFuture<WriteResult> collectionApiFuture = dbFirestore.collection(COLLECTION_NAME).document(topic).delete();
            return true;
        } catch (Exception e) {
            return false;
        }


    }
}
