package com.nlp.openhub.loginpage;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.Firestore;
import com.google.firebase.cloud.FirestoreClient;
import com.nlp.openhub.classes.Account;
import org.springframework.stereotype.Service;

import java.util.concurrent.ExecutionException;

@Service
public class LoginService {
    private static final String COLLECTION_NAME = "userAccounts";

    //service to get account details for a specific username
    public Account getAccountDetails(String username) throws ExecutionException, InterruptedException {
        Firestore dbFirestore = FirestoreClient.getFirestore();
        DocumentReference documentReference = dbFirestore.collection(COLLECTION_NAME).document(username);

        ApiFuture<DocumentSnapshot> future = documentReference.get();
        DocumentSnapshot document = future.get();

        Account account = null;

        if(document.exists())
        {
            account = document.toObject(Account.class);
            return account;
        }
        else
        {
            return null;
        }

    }

    //service to search for account in database
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
