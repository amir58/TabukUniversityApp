package com.tabuk.app;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.os.Bundle;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;
import com.tabuk.app.adatper.UsersAdapter;
import com.tabuk.app.databinding.ActivityUsersBinding;
import com.tabuk.app.model.User;

import java.util.ArrayList;
import java.util.List;

public class ManageUsersActivity extends AppCompatActivity {
    ActivityUsersBinding binding;
//    FirebaseAuth auth = FirebaseAuth.getInstance();
    FirebaseFirestore firestore = FirebaseFirestore.getInstance();
    UsersAdapter adapter;
    List<User> users = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_users);

        getUsers();
    }

    private void getUsers() {
        firestore.collection("users").addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {
                users.clear();
                for (DocumentSnapshot snapshot : value.getDocuments()) {
                    User user = snapshot.toObject(User.class);
                    users.add(user);
                }
                adapter = new UsersAdapter(users);
                binding.usersRv.setAdapter(adapter);
            }
        });
    }


}