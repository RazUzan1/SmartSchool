package com.example.smartschool;



import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;

import com.example.smartschool.login.Login;
import com.example.smartschool.objects.UserObject;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

public class MainActivity extends Activity {
    FirebaseFirestore db = FirebaseFirestore.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        checkIfUserIsLoggedIn();


    }

    private void checkIfUserIsLoggedIn() {
        SharedPreferences sharedPreferences=getSharedPreferences(Utils.NAME_OF_FILE, Context.MODE_PRIVATE);
        String userName=sharedPreferences.getString("userName","");
        String password=sharedPreferences.getString("password","");
        if(userName.length()>0){
            db.collection("users").document(userName).get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                @Override
                public void onComplete(@NonNull Task<DocumentSnapshot> task) {

                    UserObject userObject=task.getResult().toObject(UserObject.class);
                    if(userObject!=null&&userObject.getPassword().equals(password)){
                        Utils.user=userObject;
                        navToActivity(Drawer.class);
                    }else{
                        navToActivity(Login.class);
                    }

                }
            });


        }else{
            navToActivity(Login.class);
        }
    }

    private void navToActivity(Class className){
        Intent intent=new Intent(this,className);
        startActivity(intent);
    }
}