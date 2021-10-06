package com.example.smartschool.fragment;

import static android.Manifest.permission.CALL_PHONE;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.smartschool.R;


public class ZurKesher extends Fragment {
    EditText fullName,text;
    Button whatsApp,call,mail;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_zur_kesher, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        whatsApp=getView().findViewById(R.id.send_whatsapp);
        call=getView().findViewById(R.id.call);
        mail=getView().findViewById(R.id.send_mail);
        fullName=getView().findViewById(R.id.full_name);
        text=getView().findViewById(R.id.question);

        whatsApp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://api.whatsapp.com/send?phone=" + "+9720532855520" + "&text=" + text.getText().toString())));
            }
        });

        call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Intent.ACTION_CALL);
                i.setData(Uri.parse("tel:0532855520"));
                if (ContextCompat.checkSelfPermission(getContext(), CALL_PHONE) == PackageManager.PERMISSION_GRANTED) {
                    startActivity(i);
                } else {
                    requestPermissions(new String[]{CALL_PHONE}, 1);
                }
            }
        });

        mail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent email = new Intent(Intent.ACTION_SEND);
                email.putExtra(Intent.EXTRA_SUBJECT, fullName.getText().toString());
                email.putExtra(Intent.EXTRA_TEXT, text.getText().toString());

                email.setType("message/rfc822");

                startActivity(Intent.createChooser(email, "Choose an Email client :"));
            }
        });
    }
}