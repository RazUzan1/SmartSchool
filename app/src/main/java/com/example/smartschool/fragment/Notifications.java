package com.example.smartschool.fragment;


import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.smartschool.R;
import com.example.smartschool.Utils;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;


public class Notifications extends Fragment {
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    TextView general, price;
    ArrayList<String> notificationsMessage;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.activity_activitynotification, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        notificationsMessage=new ArrayList<>();
        general();
        price();

    }

    private void general() {
        /*
        * מוצאים את הכפתור של כללי ומוסיפים לו מאזין
        * */
        general = requireView().findViewById(R.id.klali);
        general.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*
                * מושכים את המידע שמקושר אל הקורס שבוא היוזר נמצא באמצעות
                * Utils.user.getClassId()
                * שמכיל בעצם את המזהה של הקורס
                * לאחר מכן ניגשים אל מסמך שקוראים לו general שמכיל את ההודעות הכלליות של הקורס
                * */
                db.collection(Utils.user.getClassId()).document("general").get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                    /*
                    * מוצאים בתבנית העיצוב את הלייאוט שיכיל את הטקסטים
                    * */
                        LinearLayout layout=getView().findViewById(R.id.scroll_view_container);
                        layout.removeAllViews();
                        /*
                        layout.removeAllViews();
                        * מסירים את כל מה שקשור לטקסטים הקודמים
                        * */

                        /*
                         * מאתחלים את המערך במערך חדש כדי לאפס אותו שלא ישמור את הנתונים הקודמים
                        * */
                        notificationsMessage = new ArrayList<>();
                        for (int i = 0; i <task.getResult().getData().size() ; i++) {
                            notificationsMessage.add((String) task.getResult().getData().get(""+i));
                           TextView textView=new TextView(getView().getContext());
                            textView.setText(notificationsMessage.get(i));
                            textView.setTextColor(Color.parseColor("red"));
                            layout.addView(textView);
                            /*
                            * מאתחלים אובייקט של textView חדש ומכניסים לתוכו טקסט וגם מעצבים אותו קצת
                            * ולאחר מכן מכניסים אותו לתוך הלייאוט באמצעות
                            * layout.addView(textView)
                            * */
                        }
                    }
                });

            }
        });



    }

    private void price() {
        /*
         * מוצאים את הכפתור של כללי ומוסיפים לו מאזין
         * */
        price = requireView().findViewById(R.id.limud);
        price.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*

              אחרי שמבצעים התחברות מושכים את כל המידע מהפיירבייס
              לרבות השם של המשתמש והסיסמה שלו וכל יתר הפרטים ובנוסף גם
              את ההודעות האישיות שלו
              ולכן בכל פעם שמשתמש יתחבר הוא ימשוך את המידע שקשור אליו
                 * */

                        LinearLayout layout=getView().findViewById(R.id.scroll_view_container);
                        layout.removeAllViews();
                        /*
                        layout.removeAllViews();
                        * מסירים את כל מה שקשור לטקסטים הקודמים
                        * */

                        /*
                         * מאתחלים את המערך במערך חדש כדי לאפס אותו שלא ישמור את הנתונים הקודמים
                         * */
                        notificationsMessage = Utils.user.getUserPersonalNotifications();
                        for (int i = 0; i <Utils.user.getUserPersonalNotifications().size() ; i++) {

                            TextView textView=new TextView(getView().getContext());
                            textView.setText(notificationsMessage.get(i));

                            textView.setTextAppearance(getContext(), R.style.textStyle);
                            layout.addView(textView);
                            /*
                             * מאתחלים אובייקט של textView חדש ומכניסים לתוכו טקסט וגם מעצבים אותו קצת
                             * ולאחר מכן מכניסים אותו לתוך הלייאוט באמצעות
                             * layout.addView(textView)
                             * */
                        }
                    }

        });



    }
}