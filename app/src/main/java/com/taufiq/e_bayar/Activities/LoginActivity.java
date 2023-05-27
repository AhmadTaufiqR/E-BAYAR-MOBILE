package com.taufiq.e_bayar.Activities;

import androidx.appcompat.app.AppCompatActivity;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
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

import com.taufiq.e_bayar.AllMethod.LoginMethod;
import com.taufiq.e_bayar.Model.UserLogin.Data;
import com.taufiq.e_bayar.Model.UserLogin.User;
import com.taufiq.e_bayar.R;
import com.taufiq.e_bayar.Request.Services;
import com.taufiq.e_bayar.Utils.ApiMethod;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {

   private EditText txtemail, txtpassword;
    private Button btnmasuk;
    private TextView buttonlupa;
    private CheckBox show;
    private SharedPreferences sharedPreferences;
    private LoginMethod loginMethod;

    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        txtemail = findViewById(R.id.txtemail);
        txtpassword = findViewById(R.id.txtpassword);
        btnmasuk = findViewById(R.id.btnmasuk);
        show = findViewById(R.id.showpass);
        buttonlupa = findViewById(R.id.btnlupasandi);
        sharedPreferences = getSharedPreferences("Ebayar", MODE_PRIVATE);
        loginMethod = new LoginMethod();

        if (loginMethod.isLoggedIn(sharedPreferences)){
            startActivity(new Intent(LoginActivity.this, DashboardActivity.class));
            finish();
        }

        btnmasuk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                loginMethod = new LoginMethod();
                loginMethod.getUserLogin(txtemail, txtpassword, getApplicationContext(), sharedPreferences);
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


        buttonlupa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LoginActivity.this, EditEmailActivity.class));
            }
        });
    }


}
