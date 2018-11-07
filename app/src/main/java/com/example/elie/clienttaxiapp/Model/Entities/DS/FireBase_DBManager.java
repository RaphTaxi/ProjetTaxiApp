package com.example.elie.clienttaxiapp.Model.Entities.DS;

import android.support.annotation.NonNull;

import com.example.elie.clienttaxiapp.Model.Entities.Entities.ClientRequest;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class FireBase_DBManager

{
    public interface Action<T>
    {
        void OnSuccess(T obj);

        void OnProgress(String status,double percent);

        void OnFailure(Exception exception);
    }

    public interface NotifyDataChange<T>
    {
        void OnDataChanged(T obj);

        void OnFailure(Exception exception);
    }



     static DatabaseReference ClientsRef;
     static
    {
        FirebaseDatabase data=FirebaseDatabase.getInstance();
        ClientsRef= data.getReference("Clients");
    }


    public static void addClientToFireBase(final ClientRequest client,final Action<String> action)
    {
        String key=client.getName();
        ClientsRef.child(key).setValue(client).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                action.OnSuccess(client.getName());
                action.OnProgress("Load Clientrequest data",100);
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                action.OnFailure(e);
                action.OnProgress("Error loading data",100);
            }
        });
    }







}
