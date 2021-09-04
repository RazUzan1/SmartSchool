package com.example.smartschool;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    Button LoginButton;
    Button HarshmaButton;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        LoginButton = findViewById(R.id.LoginButton);
        HarshmaButton = findViewById(R.id.HarshmaButton);


        LoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Activity Login();

            }
        });

        public void Login(){
            Intent intent = new Intent( this,Login.class);
            startActivity(intent);



        }
    }
}