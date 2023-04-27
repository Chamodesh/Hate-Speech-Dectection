package com.nlp.openhub.categories;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.Query;
import com.google.cloud.firestore.QueryDocumentSnapshot;
import com.google.cloud.firestore.QuerySnapshot;
import com.google.firebase.cloud.FirestoreClient;
import com.nlp.openhub.classes.Vlog;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

@Service
public class CategoriesService {
    private static final String COLLECTION_NAME = "vlogs";

    public List<Vlog> getAllVlogs(@RequestParam String category) throws ExecutionException, InterruptedException {
        Firestore dbFirestore = FirestoreClient.getFirestore();

        List<Vlog> vlogList = new ArrayList<>();
        Vlog vlog = null;

        Query query = dbFirestore.collection(COLLECTION_NAME).whereEqualTo("category", category);
        ApiFuture<QuerySnapshot> future = query.get();

        List<QueryDocumentSnapshot> documents = future.get().getDocuments();
        for (QueryDocumentSnapshot document : documents) {
            vlog = document.toObject(Vlog.class);
            vlogList.add(vlog);
        }
        return vlogList;
    }
}
