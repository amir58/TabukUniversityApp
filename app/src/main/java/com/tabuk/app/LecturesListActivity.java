package com.tabuk.app;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.os.Bundle;

import com.tabuk.app.databinding.ActivityLecturesListBinding;

public class LecturesListActivity extends AppCompatActivity {
    ActivityLecturesListBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_lectures_list);

        binding.lecturesRv.setAdapter(new LecturesAdapter());
    }

}