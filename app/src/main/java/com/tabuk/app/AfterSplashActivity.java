package com.tabuk.app;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;

import com.google.firebase.firestore.FirebaseFirestore;
import com.tabuk.app.databinding.ActivityAfterSplashActivityBinding;

import es.dmoral.toasty.Toasty;

public class AfterSplashActivity extends AppCompatActivity {
    ActivityAfterSplashActivityBinding binding;
    FirebaseFirestore firestore = FirebaseFirestore.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_after_splash_activity);
        isPaid();

        binding.joinAsGuest.setOnClickListener(v -> navigateToMapsVisitor());
        binding.btnRegister.setOnClickListener(v -> navigateToRegister());
        binding.btnLogin.setOnClickListener(v -> getDataFromUi());
    }

    private void getDataFromUi() {
        String name = binding.etEmail.getText().toString().trim();

        if (name.isEmpty()) {
            Toasty.error(AfterSplashActivity.this, "Email required").show();
            return;
        }

        if (name.equals("student@tabuk.com")) {
            navigateToStudentScreen();
        }

    }

    private void navigateToStudentScreen() {
        Intent intent = new Intent(AfterSplashActivity.this, MainStudentActivity.class);
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
        Intent intent = new Intent(AfterSplashActivity.this, VisitorMapsActivity.class);
        startActivity(intent);
    }

    private void navigateToRegister() {
        Intent intent = new Intent(AfterSplashActivity.this, RegisterActivity.class);
        startActivity(intent);
    }

}