package com.tabuk.app;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.tabuk.app.adatper.LecturesAdapter;
import com.tabuk.app.databinding.ActivityLecturesListBinding;

public class LecturesListActivity extends AppCompatActivity {
    ActivityLecturesListBinding binding;
    private String level;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_lectures_list);

        level = getIntent().getStringExtra("level");

        binding.lecturesRv.setAdapter(new LecturesAdapter());
    }

    public void openAddLecture(View view) {
        Intent intent = new Intent(this, AddLectureActivity.class);
        intent.putExtra("level", level);
        startActivity(intent);
    }

}