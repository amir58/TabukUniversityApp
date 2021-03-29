package com.tabuk.app;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Intent;
import android.os.Bundle;

import com.tabuk.app.databinding.ActivityLecturesBinding;

public class LecturesActivity extends AppCompatActivity {
    ActivityLecturesBinding binding;

    String[] levels = {
            "Level one",
            "Level Two",
            "Level Three",
            "Level Four",
            "Level Five",
            "Level Six",
            "Level Seven",
            "Level Eight"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_lectures);

        binding.levelOne.setOnClickListener(v -> navigate(0));
        binding.levelTwo.setOnClickListener(v -> navigate(1));
        binding.levelThree.setOnClickListener(v -> navigate(2));
        binding.levelFour.setOnClickListener(v -> navigate(3));
        binding.levelFive.setOnClickListener(v -> navigate(4));
        binding.levelSix.setOnClickListener(v -> navigate(5));
        binding.levelSeven.setOnClickListener(v -> navigate(6));
        binding.levelEight.setOnClickListener(v -> navigate(7));

    }

    private void navigate(int position) {
        startActivity(new Intent(this, LecturesListActivity.class)
                .putExtra("level", levels[position]));
    }


}