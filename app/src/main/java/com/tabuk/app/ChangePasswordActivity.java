package com.tabuk.app;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.EmailAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.tabuk.app.databinding.ActivityChangePasswordBinding;

import es.dmoral.toasty.Toasty;

public class ChangePasswordActivity extends AppCompatActivity {
    ActivityChangePasswordBinding binding;
    FirebaseAuth auth = FirebaseAuth.getInstance();
    FirebaseUser user;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_change_password);

        user = FirebaseAuth.getInstance().getCurrentUser();
    }


    public void changePassword(View view) {
        String oldPassword = binding.etPassword.getText().toString().trim();
        String newPassword = binding.etConfirmPassword.getText().toString().trim();

        if (oldPassword.isEmpty() || newPassword.isEmpty()) {
            Toasty.error(this, "Fill all data", Toast.LENGTH_SHORT).show();
            return;
        }

        AuthCredential credential = EmailAuthProvider
                .getCredential(auth.getCurrentUser().getEmail(), oldPassword);

        user.reauthenticate(credential).addOnSuccessListener(aVoid -> {
            user.updatePassword(newPassword).addOnSuccessListener(aVoid1 ->
                    Toasty.success(this, "Password changed", Toast.LENGTH_SHORT).show());
        });


    }

}