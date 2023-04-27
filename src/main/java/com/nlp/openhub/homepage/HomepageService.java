package com.nlp.openhub.homepage;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.QueryDocumentSnapshot;
import com.google.cloud.firestore.QuerySnapshot;
import com.google.firebase.cloud.FirestoreClient;
import com.nlp.openhub.classes.Vlog;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

@Service
public class HomepageService {
    private static final String COLLECTION_NAME = "vlogs";

    public List<Vlog> getAllVlogs() throws ExecutionException, InterruptedException {
        Firestore dbFirestore = FirestoreClient.getFirestore();

        List<Vlog> vlogList = new ArrayList<>();
        Vlog vlog = null;

        ApiFuture<QuerySnapshot> future = dbFirestore.collection(COLLECTION_NAME).get();

        List<QueryDocumentSnapshot> documents = future.get().getDocuments();
        for (QueryDocumentSnapshot document : documents) {
            vlog = document.toObject(Vlog.class);
            vlogList.add(vlog);
        }
        return vlogList;
    }
}
