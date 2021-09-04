package com.example.smartschool.login;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.smartschool.Drawer;
import com.example.smartschool.R;
import com.example.smartschool.Utils;
import com.example.smartschool.objects.UserObject;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

public class Login extends AppCompatActivity {
    FirebaseFirestore db = FirebaseFirestore.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        init();


    }

    private void init() {
        EditText userName=findViewById(R.id.user_name);
        EditText password=findViewById(R.id.password);
        Button login=findViewById(R.id.login);
        TextView signUp=findViewById(R.id.sign_up);
        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navToScreen(SignUp.class);
            }
        });

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(userName.getText().toString().length()>=6&&password.getText().toString().length()>6){
                    db.collection("users").document(userName.getText().toString()).get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                            if(task.getResult().exists()&&task.getResult().get("password").equals(password)){
                                Utils.user=task.getResult().toObject(UserObject.class);
                                navToScreen(Drawer.class);
                            }
                        }
                    });
                }else if(userName.getText().toString().length()<6){
                    userName.setError("שם משתמש חייב להכיל לפחות שישה תווים");
                }else if(password.getText().toString().length()<6){
                    password.setError("הסיסמה חייבת להכיל לפחות שישה תווים");
                }
            }
        });



    }
    private void navToScreen(Class classType){
        Intent intent=new Intent(this,classType);
        startActivity(intent);
    }
}