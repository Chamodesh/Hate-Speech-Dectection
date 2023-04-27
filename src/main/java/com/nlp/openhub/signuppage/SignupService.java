package com.nlp.openhub.signuppage;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.WriteResult;
import com.google.firebase.cloud.FirestoreClient;
import com.nlp.openhub.classes.Account;
import org.springframework.stereotype.Service;

import java.util.concurrent.ExecutionException;

@Service
public class SignupService {
    private static final String COLLECTION_NAME = "userAccounts";

    public String saveAccountDetails(Account account) throws ExecutionException, InterruptedException {
        Firestore dbFirestore = FirestoreClient.getFirestore();
        ApiFuture<WriteResult> collectionApiFuture = dbFirestore.collection(COLLECTION_NAME).document(account.getUsername()).set(account);
        return collectionApiFuture.get().getUpdateTime().toString();
    }

    public boolean hasAccount(String username) throws ExecutionException, InterruptedException {
        Firestore dbFirestore = FirestoreClient.getFirestore();
        DocumentReference documentReference = dbFirestore.collection(COLLECTION_NAME).document(username);

        ApiFuture<DocumentSnapshot> future = documentReference.get();
        DocumentSnapshot document = future.get();

        if(document.exists())
        {
            return true;
        }
        else
        {
            return false;
        }

    }
}
