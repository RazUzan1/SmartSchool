package com.example.smartschool.fragment;


import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.smartschool.R;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.concurrent.Executor;


public class Notifications extends Fragment {
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    TextView one, two;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.activity_activitynotification, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        fireb();

    }

    private void fireb() {
        one = requireView().findViewById(R.id.Klali);
        two = requireView().findViewById(R.id.Tigborim);
        DocumentReference docref = db.collection("library").document("one");
        docref.addSnapshotListener((Executor) this, (value, error) ->
        {
            if (error!= null){
                return;
            }
            assert value!= null;
            one.setText(value.getString("one"));
            two.setText(value.getString("two"));


        });

    }
}