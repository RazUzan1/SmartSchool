package com.example.smartschool.login;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.smartschool.Drawer;
import com.example.smartschool.R;
import com.example.smartschool.Utils;
import com.example.smartschool.objects.UserObject;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import io.grpc.okhttp.internal.Util;

public class SignUp extends AppCompatActivity {
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        init();

    }

    private void init() {
        EditText firstName=findViewById(R.id.first_name);
        EditText lastName=findViewById(R.id.last_name);
        EditText courseName=findViewById(R.id.course_code);
        EditText password=findViewById(R.id.password);
        EditText userName=findViewById(R.id.user_name);
        Button signUp=findViewById(R.id.sign_up_button);

        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signUp.setEnabled(false);

                signUp.setText("טוען מידע אנא המתן..");
                db.collection("users").
                        document(userName.getText().toString()).
                        get().
                        addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                       if( !task.getResult().exists()){
                           db.
                                   collection("courses").
                                   document(courseName.getText().toString()).get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                               @Override
                               public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                                   if(task.getResult().exists()){
                                       UserObject userObject=new UserObject(firstName.getText().toString(),lastName.getText().toString(),password.getText().toString(),courseName.getText().toString(),userName.getText().toString());
                                       db.collection("users").document(userName.getText().toString()).set(userObject).addOnCompleteListener(new OnCompleteListener<Void>() {
                                           @Override
                                           public void onComplete(@NonNull Task<Void> task) {
                                               Intent intent=new Intent(SignUp.this, Drawer.class);
                                               Utils.user=userObject;
                                               Utils.saveData(SignUp.this,Utils.NAME_OF_FILE,Utils.user.getUserName(),"userName");
                                               Utils.saveData(SignUp.this,Utils.NAME_OF_FILE,Utils.user.getPassword(),"password");
                                               startActivity(intent);
                                           }
                                       });
                                   } else{
                                       signUp.setText("לחץ להרשמה");
                                       signUp.setEnabled(true);
                                       courseName.setError("קוד כיתה לא תקין, צור קשר עם המורה שלך.");
                                   }
                               }
                           });
                       }else {
                           signUp.setText("לחץ להרשמה");
                           signUp.setEnabled(true);
                           Toast.makeText(SignUp.this,"שם משתמש כבר קיים, יש לחזור למסך ההתחברות או לחלופין לנסות להירשם עם שם משתמש אחר.",Toast.LENGTH_LONG).show();
                           userName.setError("שם המשתמש תפוס, אנא בחר אחד חדש");
                       }
                    }
                });
            }
        });
    }
}