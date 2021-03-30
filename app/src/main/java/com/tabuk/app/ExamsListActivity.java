package com.tabuk.app;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;

import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;
import com.tabuk.app.adatper.ExamsAdapter;
import com.tabuk.app.databinding.ActivityExamsListBinding;
import com.tabuk.app.model.MyExam;

import java.util.ArrayList;
import java.util.List;

public class ExamsListActivity extends AppCompatActivity {
    ActivityExamsListBinding binding;
    FirebaseFirestore firestore = FirebaseFirestore.getInstance();
    private String level;
    private String faculty;
    private String rule;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_exams_list);

        level = getIntent().getStringExtra("level");

        SharedPreferences preferences = getSharedPreferences("user", MODE_PRIVATE);
        faculty = preferences.getString("faculty", "");
        rule = preferences.getString("rule", "");

        if (rule.equals("management")) {
            binding.btnAddExam.setVisibility(View.VISIBLE);
        } else {
            binding.btnAddExam.setVisibility(View.GONE);
        }

        getExams();
    }

    private void getExams() {
        firestore.collection("exams").whereEqualTo("faculty", faculty).addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {
                List<MyExam> myLectures = new ArrayList<>();
                for (DocumentSnapshot snapshot : value.getDocuments()) {
                    MyExam myLecture = snapshot.toObject(MyExam.class);
                    if (myLecture.getLevel().equals(level)) myLectures.add(myLecture);
                }
                binding.examsRv.setAdapter(new ExamsAdapter(myLectures, rule));
            }
        });
    }

    public void openAddExam(View view) {
        Intent intent = new Intent(this, AddExamActivity.class);
        intent.putExtra("level", level);
        startActivity(intent);
    }
}