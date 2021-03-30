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
import com.tabuk.app.adatper.LecturesAdapter;
import com.tabuk.app.databinding.ActivityLecturesListBinding;
import com.tabuk.app.model.MyLecture;
import java.util.ArrayList;
import java.util.List;

public class LecturesListActivity extends AppCompatActivity {
    FirebaseFirestore firestore = FirebaseFirestore.getInstance();
    ActivityLecturesListBinding binding;
    private String level;
    private String faculty;
    private String rule;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_lectures_list);

        level = getIntent().getStringExtra("level");

        SharedPreferences preferences = getSharedPreferences("user", MODE_PRIVATE);
        faculty = preferences.getString("faculty", "");
        rule = preferences.getString("rule", "");

        if (rule.equals("management")) {
            binding.btnAddLecture.setVisibility(View.VISIBLE);
        } else {
            binding.btnAddLecture.setVisibility(View.GONE);
        }

        getLectures();
    }

    private void getLectures() {
        firestore.collection("lectures").whereEqualTo("faculty", faculty).addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {
                List<MyLecture> myLectures = new ArrayList<>();
                for (DocumentSnapshot snapshot : value.getDocuments()) {
                    MyLecture myLecture = snapshot.toObject(MyLecture.class);
                    if (myLecture.getLevel().equals(level)) myLectures.add(myLecture);
                }
                binding.lecturesRv.setAdapter(new LecturesAdapter(myLectures, rule));
            }
        });
    }

    public void openAddLecture(View view) {
        Intent intent = new Intent(this, AddLectureActivity.class);
        intent.putExtra("level", level);
        startActivity(intent);
    }

}