package com.tabuk.app;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Intent;
import android.os.Bundle;

import com.tabuk.app.databinding.ActivityMainManagementActiivtyBinding;

public class MainManagementActivity extends AppCompatActivity {
    ActivityMainManagementActiivtyBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main_management_actiivty);

        binding.mapLayout.setOnClickListener(v -> startActivity(new Intent(this, VisitorsMapsActivity.class)));
        binding.sendNotificationLayout.setOnClickListener(v -> startActivity(new Intent(this, AddNotificationActivity.class)));
        binding.examLayout.setOnClickListener(v -> startActivity(new Intent(this, ExamsActivity.class)));
        binding.lecturesLayout.setOnClickListener(v -> startActivity(new Intent(this, LecturesActivity.class)));
        binding.updateProfileLayout.setOnClickListener(v -> startActivity(new Intent(this, UpdateProfileActivity.class)));
        binding.changePasswordLayout.setOnClickListener(v -> startActivity(new Intent(this, ChangePasswordActivity.class)));

    }

}
