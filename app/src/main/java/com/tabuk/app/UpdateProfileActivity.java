package com.tabuk.app;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.tabuk.app.databinding.ActivityUpdateProfileBinding;
import com.tabuk.app.model.User;

import java.util.HashMap;
import java.util.Map;

import es.dmoral.toasty.Toasty;

public class UpdateProfileActivity extends AppCompatActivity {
    ActivityUpdateProfileBinding binding;
    FirebaseAuth auth = FirebaseAuth.getInstance();
    FirebaseFirestore firestore = FirebaseFirestore.getInstance();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_update_profile);

        getUserData();
    }

    private void getUserData() {
        firestore.collection("users").document(auth.getUid()).get()
                .addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                        if (task.isSuccessful()) {
                            User user = task.getResult().toObject(User.class);
                            binding.etName.setText(user.getName());
                            binding.etPhone.setText(user.getPhone());
                        }
                    }
                });
    }


    public void updateProfile(View view) {
        String name = binding.etName.getText().toString().trim();
        String phone = binding.etPhone.getText().toString().trim();

        if (name.isEmpty() || phone.isEmpty()) {
            Toasty.error(this, "Fill all data", Toast.LENGTH_SHORT).show();
            return;
        }

        Map<String, Object> map = new HashMap<>();
        map.put("name", name);
        map.put("phone", phone);

        firestore.collection("users").document(auth.getUid()).update(map);
        Toasty.success(this, "Updated", Toast.LENGTH_SHORT).show();

    }

}
