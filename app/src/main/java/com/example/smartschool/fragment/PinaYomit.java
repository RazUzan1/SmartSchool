package com.example.smartschool.fragment;



import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.smartschool.R;
import com.example.smartschool.Utils;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.Calendar;

public class PinaYomit extends Fragment {
    ImageView one, two, three;
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    private ArrayList<Object>didYouKnow;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_hayadata, container, false);
    }
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        imageview();
        init();
    }
    private void imageview() {
        one = getView().findViewById(R.id.one);
        two = getView().findViewById(R.id.two);
        three = getView().findViewById(R.id.three);
        Calendar calendar = Calendar.getInstance();
        if (calendar.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY){
            two.setVisibility(View.GONE);
            three.setVisibility(View.GONE);
        }
    }
    private void init(){
        db.collection(Utils.user.getClassId()).document("didYouKnow").get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                didYouKnow=new ArrayList<>();


            }
        });
    }

    public ArrayList<Object> getDidYouKnow() {
        return didYouKnow;
    }

    public void setDidYouKnow(ArrayList<Object> didYouKnow) {
        this.didYouKnow = didYouKnow;
    }
}