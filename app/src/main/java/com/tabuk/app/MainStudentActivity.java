package com.tabuk.app;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Intent;
import android.os.Bundle;

import com.tabuk.app.databinding.ActivityMainStudentBinding;

public class MainStudentActivity extends AppCompatActivity {
    ActivityMainStudentBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main_student);

        binding.mapLayout.setOnClickListener(v -> startActivity(new Intent(this, VisitorMapsActivity.class)));
        binding.mapLayout.setOnClickListener(v -> startActivity(new Intent(this, VisitorMapsActivity.class)));
        binding.examLayout.setOnClickListener(v -> startActivity(new Intent(this, ExamsActivity.class)));
        binding.lecturesLayout.setOnClickListener(v -> startActivity(new Intent(this, LecturesActivity.class)));
        binding.updateProfileLayout.setOnClickListener(v -> startActivity(new Intent(this, UpdateProfileActivity.class)));
        binding.changePasswordLayout.setOnClickListener(v -> startActivity(new Intent(this, ChangePasswordActivity.class)));
    }

}