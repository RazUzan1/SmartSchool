package com.example.smartschool.fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.smartschool.R;

public class Tigborim extends Fragment {
    Button math, lashon, english, history, tanach, ezrachut;
    ConstraintLayout tigbur_math, tigbur_lashon, tigbur_english, tigbur_history, tigbur_tanach, tigbur_ezrachut;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        return inflater.inflate(R.layout.fragment_reinforcements, container, false);

    }
//מתודה שאחראית על היצירה של getview צריך לעשות ככה בכל פרגמנט!
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        init();
        close();
    }

    private void close() {
        tigbur_math.setVisibility(View.GONE);
        tigbur_lashon.setVisibility(View.GONE);
        tigbur_english.setVisibility(View.GONE);
        tigbur_history.setVisibility(View.GONE);
        tigbur_tanach.setVisibility(View.GONE);
        tigbur_ezrachut.setVisibility(View.GONE);
    }

    private void init() {
        math=getView().findViewById(R.id.btn_math);
        lashon=getView().findViewById(R.id.btn_lashon);
        english=getView().findViewById(R.id.btn_english);
        history=getView().findViewById(R.id.btn_history);
        tanach=getView().findViewById(R.id.btn_tanach);
        ezrachut=getView().findViewById(R.id.btn_ezrachut);

        tigbur_math=getView().findViewById(R.id.math);
        tigbur_lashon=getView().findViewById(R.id.lashon);
        tigbur_english=getView().findViewById(R.id.english);
        tigbur_history=getView().findViewById(R.id.history);
        tigbur_tanach=getView().findViewById(R.id.tanach);
        tigbur_ezrachut=getView().findViewById(R.id.ezrachut);

        math.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                close();
                tigbur_math.setVisibility(View.VISIBLE);
            }
        });

        lashon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                close();
                tigbur_lashon.setVisibility(View.VISIBLE);
            }
        });

        english.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                close();
                tigbur_english.setVisibility(View.VISIBLE);
            }
        });

        history.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                close();
                tigbur_history.setVisibility(View.VISIBLE);
            }
        });

        tanach.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                close();
                tigbur_tanach.setVisibility(View.VISIBLE);
            }
        });

        ezrachut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                close();
                tigbur_ezrachut.setVisibility(View.VISIBLE);
            }
        });

    }
}