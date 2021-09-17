package com.example.smartschool.login;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.smartschool.Drawer;
import com.example.smartschool.R;
import com.example.smartschool.Utils;
import com.example.smartschool.objects.UserObject;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

public class Login extends AppCompatActivity {
    FirebaseFirestore db = FirebaseFirestore.getInstance();

    EditText userName;
    EditText password;
    EditText courseName;
    Button login;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        FirebaseFirestore.setLoggingEnabled(true);
        init();


    }

    private void init() {
        userName=findViewById(R.id.user_name);
        password=findViewById(R.id.password);
        courseName=findViewById(R.id.course_code);
         login=findViewById(R.id.login);
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
                login.setEnabled(false);
                userName.setEnabled(false);
                password.setEnabled(false);
                courseName.setEnabled(false);
                userName.setError(null);
                password.setError(null);
                courseName.setError(null);
                login.setText("מתחבר...");
                onLogin();
                /*
                אחרי שהמשתמש הזין את הנתונים שלו אנחנו מאפסים את המצב של המסך
                משגיאות וכדומה וקוראים למתודה onLogin שבודקת האם המידע תקין
              */
            }
        });

        /*
        * אתחול ראשוני
        * */



    }
    private void navToScreen(Class classType){
        /*
        * מעביר לפעילות הבאה בהתאם לסוג המחלקה ששלחנו
        * */
        Intent intent=new Intent(this,classType);
        startActivity(intent);
    }
    private void onLogin(){
        if(userName.getText().toString().length()>=6&&courseName.getText().toString().length()>4&&password.getText().toString().length()>6){
            //בדיקה בסיסית לראות אם הנתונים תקינים
            db.collection("users").document(userName.getText().toString()).get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                @Override
                public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                   /*
                   משיכת הנתונים מתוך כל המשתמשים כדי לבדוק האם המשתמש קיים או לא
                   כדי שתלמידים לא יוכלו לדרוס מידע קיים

                   */
                    if(task.getResult().exists()&&task.getResult().get("password").equals(password.getText().toString())){
                     /*
                     במידע והשם קיים בודקים האם הסיסמה תואמת במידה וכן בודקים האם קיים קורס
                     כמו שהזין הסטודנט
                     * */
                        db.collection(courseName.getText().toString()).get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                            @Override
                            public void onComplete(@NonNull Task<QuerySnapshot> task2) {
                                if(task2.getResult().isEmpty()){
                                    /*
                                    במידה ולא קיים קורס כזה אז תציג לו שגיאה מסויימת ולא נאפשר לו להתחבר
                                    * */
                                    userName.setEnabled(true);
                                    password.setEnabled(true);
                                    courseName.setEnabled(true);
                                    login.setEnabled(true);
                                    login.setText("לחץ להתחברות");
                                    courseName.setError("יש להזין שם קורס תקין");
                                }else{
                                    /*
                                    * במידה וקיים קורס נאפשר לו להתחבר
                                    * */
                                    Utils.user=task.getResult().toObject(UserObject.class);
                                    Utils.saveData(Login.this,Utils.NAME_OF_FILE,Utils.user.getUserName(),"userName");
                                    Utils.saveData(Login.this,Utils.NAME_OF_FILE,Utils.user.getPassword(),"password");
                                    navToScreen(Drawer.class);
                                }
                            }
                        });



                    }else{
                        /*
                        * לא קיים משתמש כזה או שהסיסמה לא נכונה
                        * */
                        userName.setEnabled(true);
                        password.setEnabled(true);
                        courseName.setEnabled(true);
                        login.setEnabled(true);
                        login.setText("לחץ להתחברות");
                        userName.setError("יתכן ששם המשתמש שהזנת לא תואם למה שקיים במערכת או לא קיים במערכת");
                        password.setError("נא לוודא שהסיסמה שהוכנסה הינה תקינה");
                    }
                }
            });
        }else{
            /*
            * לא הוזנו הפרטים התקינים
            * */
            userName.setEnabled(true);
            password.setEnabled(true);
            courseName.setEnabled(true);
            login.setEnabled(true);
            login.setText("לחץ להתחברות");
        }
        if(userName.getText().toString().length()<6){
            userName.setError("שם משתמש חייב להכיל לפחות שישה תווים");
        }
        if(password.getText().toString().length()<6){
            password.setError("הסיסמה חייבת להכיל לפחות שישה תווים");
        }
        if(courseName.getText().toString().length()<=4){
            courseName.setError("חובה להכניס קוד כיתה, יש ליצור קשר עם המחכנת על מנת לקבל קוד כיתה");
        }
    }
}