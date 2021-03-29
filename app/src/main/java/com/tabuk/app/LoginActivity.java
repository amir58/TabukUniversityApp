package com.tabuk.app;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.app.AlertDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.tabuk.app.databinding.ActivityLoginBinding;
import com.tabuk.app.model.User;

import es.dmoral.toasty.Toasty;

public class LoginActivity extends AppCompatActivity {
    ActivityLoginBinding binding;
    FirebaseFirestore firestore = FirebaseFirestore.getInstance();
    FirebaseAuth auth = FirebaseAuth.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_login);
        isPaid();

        binding.joinAsGuest.setOnClickListener(v -> navigateToMapsVisitor());
        binding.btnRegister.setOnClickListener(v -> navigateToRegister());
        binding.btnLogin.setOnClickListener(v -> getDataFromUi());
    }

    private void getDataFromUi() {
        String email = binding.etEmail.getText().toString().trim();
        String password = binding.etPassword.getText().toString().trim();

        if (email.isEmpty()) {
            Toasty.info(LoginActivity.this, "Email required").show();
            return;
        }

        if (password.isEmpty()) {
            Toasty.info(LoginActivity.this, "Password required").show();
            return;
        }

//        if (name.equals("student@tabuk.com") && password.equals("123456")) {
//            navigateToStudentScreen();
//        } else {
//            Toasty.error(LoginActivity.this, "Email or password wrong").show();
//        }
        auth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            getUserData();

                        } else {
                            String errorMessage = task.getException().getLocalizedMessage();
                            Toasty.error(LoginActivity.this, errorMessage).show();
                        }
                    }
                });

    }

    private void getUserData() {


        firestore.collection("users").document(auth.getUid())
                .get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    User user = task.getResult().toObject(User.class);
                    saveRule(user.getRule());
                    checkUserRule(user.getRule());

                } else {
                    String errorMessage = task.getException().getLocalizedMessage();
                    Toasty.error(LoginActivity.this, errorMessage).show();
                }
            }
        });
    }

    private void saveRule(String rule) {
        SharedPreferences preferences = getSharedPreferences("user", MODE_PRIVATE);
        preferences.edit().putString("rule", rule).apply();
    }

    private void checkUserRule(String rule) {
        Intent intent;

        switch (rule) {
            case "user":
                intent = new Intent(LoginActivity.this, MainStudentActivity.class);
                break;

            case "admin":
                intent = new Intent(LoginActivity.this, MainAdminActivity.class);
                break;

            case "management":
                intent = new Intent(LoginActivity.this, MainActivity.class);
                break;

            default:
                throw new IllegalStateException("Unexpected value: " + rule);
        }

        startActivity(intent);
    }

    private void isPaid() {
        firestore.collection("paid").document("paid")
                .get().addOnSuccessListener(documentSnapshot -> {
            boolean isPaid = documentSnapshot.getBoolean("paid");

            if (isPaid) return;

            new AlertDialog.Builder(this)
                    .setMessage("Some thing wrong please contact with developer")
                    .setCancelable(false)
                    .show();
        });
    }

    private void navigateToMapsVisitor() {
        Intent intent = new Intent(LoginActivity.this, VisitorsMapsActivity.class);
        startActivity(intent);
    }

    private void navigateToRegister() {
        Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
        startActivity(intent);
    }

}