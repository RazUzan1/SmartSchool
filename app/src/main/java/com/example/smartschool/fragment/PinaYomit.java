package com.example.smartschool.fragment;

import android.graphics.drawable.Drawable;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.smartschool.R;

import java.util.Calendar;

public class PinaYomit extends Fragment {
    ImageView one, two, three;

 
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        imageview();
        return inflater.inflate(R.layout.fragment_hayadata, container, false);
    }

    private void imageview() {
        one = requireView().findViewById(R.id.one);
        two = requireView().findViewById(R.id.two);
        three = requireView().findViewById(R.id.three);
        Calendar calendar = Calendar.getInstance();
        if (calendar.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY){
            two.setVisibility(View.GONE);
            three.setVisibility(View.GONE);
        }


    }
}