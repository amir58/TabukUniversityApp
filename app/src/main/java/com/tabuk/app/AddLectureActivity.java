package com.tabuk.app;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.tabuk.app.databinding.ActivityAddLectureBinding;
import com.tabuk.app.databinding.ActivityAddLocationBinding;
import com.tabuk.app.model.MyLecture;

import es.dmoral.toasty.Toasty;

public class AddLectureActivity extends AppCompatActivity {
    FirebaseFirestore firestore = FirebaseFirestore.getInstance();
    ActivityAddLectureBinding binding;
    private String level;

    String[] faculties = {"Computer science", "Nursing", "Language and translations",
            "Mathematics", "Arabic Language", "Islamic studies"};

    String selectedFaculty = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_add_lecture);
        level = getIntent().getStringExtra("level");

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, faculties);
        binding.faculties.setAdapter(adapter);

        binding.faculties.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                selectedFaculty = faculties[position];
            }
        });
    }

    public void addLecture(View view) {
        String id = String.valueOf(System.currentTimeMillis());
        String courseName = binding.etCourseName.getText().toString().trim();
        String courseDay = binding.etCourseDay.getText().toString().trim();
        String courseTime = binding.etCourseTime.getText().toString().trim();

        if (courseName.isEmpty() || courseDay.isEmpty() || courseTime.isEmpty() || selectedFaculty.isEmpty()) {
            Toasty.error(this, "Fill All Data").show();
            return;
        }

        MyLecture myLecture = new MyLecture(id, courseName, courseDay, courseTime, selectedFaculty, level);

        firestore.collection("lectures").document(id).set(myLecture)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            Toasty.success(AddLectureActivity.this, "Lecture added").show();
                            finish();

                        } else {
                            String errorMessage = task.getException().getLocalizedMessage();
                            Toasty.error(AddLectureActivity.this, errorMessage).show();
                        }
                    }
                });

    }

}