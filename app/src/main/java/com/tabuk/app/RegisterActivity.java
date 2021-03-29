package com.tabuk.app;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import com.tabuk.app.databinding.ActivityRegisterBinding;
import com.tabuk.app.model.User;

import es.dmoral.toasty.Toasty;

public class RegisterActivity extends AppCompatActivity {
    ActivityRegisterBinding binding;
    FirebaseAuth auth = FirebaseAuth.getInstance();
    FirebaseFirestore firestore = FirebaseFirestore.getInstance();
    String[] faculties = {"Computer science", "Nursing", "Language and translations",
            "Mathematics", "Arabic Language", "Islamic studies"};

    String selectedFaculty = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_register);

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, faculties);
        binding.faculties.setAdapter(adapter);

        binding.faculties.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                selectedFaculty = faculties[position];
            }
        });
    }

    public void register(View view) {
        String name = binding.etName.getText().toString().trim();
        String id = binding.etId.getText().toString().trim();
        String phone = binding.etPhone.getText().toString().trim();
        String email = binding.etEmail.getText().toString().trim();
        String password = binding.etPassword.getText().toString().trim();

        if (name.isEmpty() || id.isEmpty() || phone.isEmpty() || email.isEmpty() || password.isEmpty() || selectedFaculty.isEmpty()) {
            Toasty.error(this, "Fill all data").show();
            return;
        }

        User user = new User(name, id, phone, "authId", selectedFaculty);

        auth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            addUserDataToCloudFirestore(user);

                        } else {
                            String errorMessage = task.getException().getLocalizedMessage();
                            Toasty.error(RegisterActivity.this, errorMessage).show();
                        }
                    }
                });
    }

    private void addUserDataToCloudFirestore(User user) {
        user.setAuthId(auth.getUid());

        firestore.collection("users")
                .document(auth.getUid())
                .set(user)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            Toasty.success(RegisterActivity.this, "Account created").show();
                            finish();

                        } else {
                            String errorMessage = task.getException().getLocalizedMessage();
                            Toasty.error(RegisterActivity.this, errorMessage).show();
                        }
                    }
                });
    }

}