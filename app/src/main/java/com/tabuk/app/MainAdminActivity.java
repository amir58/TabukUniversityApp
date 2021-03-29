package com.tabuk.app;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.tabuk.app.databinding.ActivityMainAdminBinding;

public class MainAdminActivity extends AppCompatActivity {
    ActivityMainAdminBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main_admin);

        binding.mapLayout.setOnClickListener(v -> startActivity(new Intent(this, AddMapsActivity.class)));
        binding.examLayout.setOnClickListener(v -> startActivity(new Intent(this, ExamsActivity.class)));
        binding.lecturesLayout.setOnClickListener(v -> startActivity(new Intent(this, LecturesActivity.class)));
        binding.updateProfileLayout.setOnClickListener(v -> startActivity(new Intent(this, UpdateProfileActivity.class)));
        binding.changePasswordLayout.setOnClickListener(v -> startActivity(new Intent(this, ChangePasswordActivity.class)));

    }

    public void openManageUsers(View view) {
        Intent intent = new Intent(this, ManageUsersActivity.class);
        startActivity(intent);
    }


}