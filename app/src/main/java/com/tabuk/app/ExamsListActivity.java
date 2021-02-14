package com.tabuk.app;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.os.Bundle;

import com.tabuk.app.databinding.ActivityExamsListBinding;

public class ExamsListActivity extends AppCompatActivity {
    ActivityExamsListBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_exams_list);

        binding.examsRv.setAdapter(new ExamsAdapter());
    }
}