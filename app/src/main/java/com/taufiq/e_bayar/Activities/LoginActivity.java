package com.taufiq.e_bayar.Activities;

import static android.content.ContentValues.TAG;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.session.MediaSessionManager;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.taufiq.e_bayar.R;

public class LoginActivity extends AppCompatActivity {

    TextView txtemail, txtpassword;
    Button btnmasuk;
    String email;
    String password;
    CheckBox show;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        txtemail = findViewById(R.id.txtemail);
        txtpassword = findViewById(R.id.txtpassword);

        btnmasuk = findViewById(R.id.btnmasuk);
        show = findViewById(R.id.showpass);

        btnmasuk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this,DashboardActivity.class));
            }
        });

        show.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    txtpassword.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                } else {
                    txtpassword.setTransformationMethod(PasswordTransformationMethod.getInstance());
                }
            }
        });



        TextView button = findViewById(R.id.btnlupasandi);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch (view.getId()){
                    case R.id.btnmasuk:
                     email = txtemail.getText().toString();
                     password = txtpassword.getText().toString();

                }

                startActivity(new Intent(LoginActivity.this,EditEmailActivity.class));
            }
        });
    }
    }

