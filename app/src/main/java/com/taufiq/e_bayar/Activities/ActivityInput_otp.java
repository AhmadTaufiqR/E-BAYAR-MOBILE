package com.taufiq.e_bayar.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.goodiebag.pinview.Pinview;
import com.taufiq.e_bayar.R;

public class ActivityInput_otp extends AppCompatActivity {
    Pinview pinview;
    Button btnVerifikasi;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input_otp);
        Intent intent = getIntent();
        btnVerifikasi = findViewById(R.id.btn_verifikasi);
        String otp = intent.getStringExtra("otp");
        pinview = findViewById(R.id.pinview);

        btnVerifikasi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(otp.equals(pinview.getValue().toString())){
                    Toast.makeText(getApplicationContext(),"Kode Otp Benar",Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(ActivityInput_otp.this, ResetPasswordActivity.class);
                    startActivity(intent);
                    finish();
                } else{
                    Toast.makeText(getApplicationContext(),"Kode Otp Salah",Toast.LENGTH_LONG).show();
                }
//                startActivity(new Intent(ActivityInput_otp.this, ResetPasswordActivity.class));
            }
        });

    }
}