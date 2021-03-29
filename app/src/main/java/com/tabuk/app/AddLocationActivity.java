package com.tabuk.app;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.os.Bundle;
import android.view.View;

import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.tabuk.app.databinding.ActivityAddLocationBinding;
import com.tabuk.app.model.MyLocation;

import es.dmoral.toasty.Toasty;

public class AddLocationActivity extends AppCompatActivity {
    ActivityAddLocationBinding binding;
    LatLng latLng;

    FirebaseFirestore firestore = FirebaseFirestore.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_add_location);

        latLng = getIntent().getParcelableExtra("latLng");
    }

    public void addLocation(View view) {
        String locationName = binding.etLocationName.getText().toString().trim();

        if (locationName.isEmpty()) {
            Toasty.error(this, "Write location name").show();
            return;
        }

        MyLocation myLocation =
                new MyLocation(locationName, latLng.latitude, latLng.longitude);

        firestore.collection("locations").document().set(myLocation)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            Toasty.success(AddLocationActivity.this, "Location added").show();

                        } else {
                            String errorMessage = task.getException().getLocalizedMessage();
                            Toasty.error(AddLocationActivity.this, errorMessage).show();
                        }
                    }
                });
    }

}