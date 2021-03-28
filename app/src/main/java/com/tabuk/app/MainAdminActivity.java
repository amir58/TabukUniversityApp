package com.tabuk.app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainAdminActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_admin);
    }

    public void openManageUsers(View view) {
        Intent intent = new Intent(this, ManageUsersActivity.class);
        startActivity(intent);
    }


}