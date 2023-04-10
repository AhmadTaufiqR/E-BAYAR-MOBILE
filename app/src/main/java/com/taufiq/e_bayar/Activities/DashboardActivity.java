package com.taufiq.e_bayar.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.WindowManager;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.taufiq.e_bayar.R;

public class DashboardActivity extends AppCompatActivity {
    private BottomNavigationView btnNavView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        setContentView(R.layout.activity_dashboard);
        btnNavView = findViewById(R.id.bottomNavigationView);
        btnNavView.setBackground(null);
    }
}