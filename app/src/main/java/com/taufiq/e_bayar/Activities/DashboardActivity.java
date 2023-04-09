package com.taufiq.e_bayar.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.taufiq.e_bayar.R;

public class DashboardActivity extends AppCompatActivity {
    private BottomNavigationView btnNavView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        btnNavView = findViewById(R.id.bottomNavigationView);
        btnNavView.setBackground(null);
    }
}