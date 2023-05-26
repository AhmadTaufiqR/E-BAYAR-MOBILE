package com.taufiq.e_bayar.Activities;

import androidx.appcompat.app.AppCompatActivity;


import android.app.Dialog;
import android.os.Build;
import android.os.Bundle;
import android.widget.Button;

import com.taufiq.e_bayar.R;

public class EditTeleponActivity extends AppCompatActivity {

    private Dialog dialog;
    private Button Lanjutkan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_telepon);

        Lanjutkan = findViewById(R.id.lanjut_btn);

        /*
          Membuat kotak dialog
        dialog = new Dialog(this);
        dialog.setContentView((R.layout.custom_dialog_layout));
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            dialog.getWindow().setBackgroundDrawable(getDrawable(R.drawable.));
        }
         */

    }
}