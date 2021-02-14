package com.tabuk.app;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Intent;
import android.os.Bundle;

import com.tabuk.app.databinding.ActivityLecturesBinding;

public class LecturesActivity extends AppCompatActivity {
    ActivityLecturesBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_lectures);

        binding.levelOne.setOnClickListener(v -> navigate());
        binding.levelTwo.setOnClickListener(v -> navigate());
        binding.levelThree.setOnClickListener(v -> navigate());
        binding.levelFour.setOnClickListener(v -> navigate());
        binding.levelFive.setOnClickListener(v -> navigate());
        binding.levelSix.setOnClickListener(v -> navigate());
        binding.levelSeven.setOnClickListener(v -> navigate());
        binding.levelEight.setOnClickListener(v -> navigate());


    }

    private void navigate() {
        startActivity(new Intent(this, LecturesListActivity.class));
    }


}