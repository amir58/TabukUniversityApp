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
import com.tabuk.app.databinding.ActivityAddNotificationBinding;
import com.tabuk.app.model.MyNotification;
import es.dmoral.toasty.Toasty;

public class AddNotificationActivity extends AppCompatActivity {
    ActivityAddNotificationBinding binding;
    FirebaseFirestore firestore = FirebaseFirestore.getInstance();

    String[] faculties = {"Computer science", "Nursing", "Language and translations",
            "Mathematics", "Arabic Language", "Islamic studies"};

    String selectedFaculty = "";

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

    String selectedLevel = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_add_notification);

        initFaculties();
        initLevels();
    }

    private void initFaculties() {
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, faculties);
        binding.faculties.setAdapter(adapter);

        binding.faculties.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                selectedFaculty = faculties[position];
            }
        });
    }

    private void initLevels() {
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, levels);
        binding.levels.setAdapter(adapter);

        binding.levels.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                selectedLevel = levels[position];
            }
        });
    }

    public void addNotification(View view) {
        String notificationContent = binding.etNotification.getText().toString().trim();

        if (notificationContent.isEmpty() || selectedFaculty.isEmpty() || selectedLevel.isEmpty()) {
            Toasty.error(this, "Fill All Data").show();
            return;
        }

        MyNotification myNotification =
                new MyNotification(notificationContent, selectedFaculty, selectedLevel);

        String id = String.valueOf(System.currentTimeMillis());

        firestore.collection("notifications").document(id)
                .set(myNotification).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()) {
                    Toasty.success(AddNotificationActivity.this, "Notification added").show();
                    finish();

                } else {
                    String errorMessage = task.getException().getLocalizedMessage();
                    Toasty.error(AddNotificationActivity.this, errorMessage).show();
                }
            }
        });

    }

}