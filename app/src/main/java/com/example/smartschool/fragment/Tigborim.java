package com.example.smartschool.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.smartschool.R;

public class Tigborim extends Fragment {
    Button math, lashon, english, history, tanach, ezrachut;
    TextView tigbur_math, tigbur_lashon, tigbur_english, tigbur_history, tigbur_tanach, tigbur_ezrachut;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        init();
        close();
        return inflater.inflate(R.layout.fragment_reinforcements, container, false);

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
        math.findViewById(R.id.btn_math);
        lashon.findViewById(R.id.btn_lashon);
        english.findViewById(R.id.btn_english);
        history.findViewById(R.id.btn_history);
        tanach.findViewById(R.id.btn_tanach);
        ezrachut.findViewById(R.id.btn_ezrachut);

        tigbur_math.findViewById(R.id.tigbur_math);
        tigbur_lashon.findViewById(R.id.tigbur_lashon);
        tigbur_english.findViewById(R.id.tigbur_english);
        tigbur_history.findViewById(R.id.tigbur_history);
        tigbur_tanach.findViewById(R.id.tigbur_tanach);
        tigbur_ezrachut.findViewById(R.id.tigbur_ezrachut);

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



    }
}