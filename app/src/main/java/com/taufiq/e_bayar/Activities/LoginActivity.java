package com.taufiq.e_bayar.Activities;

<<<<<<< HEAD
import static android.content.ContentValues.TAG;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.session.MediaSessionManager;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
=======
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationView;
>>>>>>> 61f641e52fbd2c76558319d641740a4c7914150c
import com.taufiq.e_bayar.R;

public class LoginActivity extends AppCompatActivity {

<<<<<<< HEAD
    TextView txtemail, txtpassword;
    Button btnmasuk;
    String email;
    String password;

=======
>>>>>>> 61f641e52fbd2c76558319d641740a4c7914150c
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

<<<<<<< HEAD
        txtemail = findViewById(R.id.txtemail);
        txtpassword = findViewById(R.id.txtpassword);

        btnmasuk = findViewById(R.id.btnmasuk);
        btnmasuk.setOnClickListener((View.OnClickListener) this);

//Temukan button dengan id
        TextView button = findViewById(R.id.btnlupasandi);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch (view.getId()){
                    case R.id.btnmasuk:
                     email = txtemail.getText().toString();
                     password = txtpassword.getText().toString();
                     
                }

//                startActivity(new Intent(LoginActivity.this,LupaSandiActivity.class));
                Log.d(TAG, "onClick: bisa");
            }
        });
    }

=======
//Temukan button dengan id
        TextView button = findViewById(R.id.btnlupasandi);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(new Intent(LoginActivity.this,ResetPasswordActivity.class));
            }
        });
    }
>>>>>>> 61f641e52fbd2c76558319d641740a4c7914150c
}