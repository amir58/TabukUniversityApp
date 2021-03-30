package com.tabuk.app;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.SharedPreferences;
import android.os.Bundle;

import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;
import com.tabuk.app.adatper.NotificationsAdapter;
import com.tabuk.app.databinding.ActivityNotificationsBinding;
import com.tabuk.app.model.MyNotification;

import java.util.ArrayList;
import java.util.List;

public class NotificationsActivity extends AppCompatActivity {
    ActivityNotificationsBinding binding;
    FirebaseFirestore firestore = FirebaseFirestore.getInstance();

    private String faculty;
    private String level;

    List<MyNotification> myNotifications = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_notifications);

        SharedPreferences preferences = getSharedPreferences("user", MODE_PRIVATE);
        faculty = preferences.getString("faculty", "");
        level = preferences.getString("level", "");

        getNotifications();
    }

    private void getNotifications() {
        firestore.collection("notifications").whereEqualTo("faculty", faculty).addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {
                myNotifications.clear();
                for (DocumentSnapshot snapshot : value.getDocuments()) {
                    MyNotification myNotification = snapshot.toObject(MyNotification.class);
                    if (myNotification.getLevel().equals(level))
                        myNotifications.add(myNotification);
                }
                NotificationsAdapter adapter = new NotificationsAdapter(myNotifications);
                binding.notificationsRv.setAdapter(adapter);

            }
        });
    }


}