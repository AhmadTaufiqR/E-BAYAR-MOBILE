package com.taufiq.e_bayar.AllMethod;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.taufiq.e_bayar.Activities.DashboardActivity;
import com.taufiq.e_bayar.Activities.LoginActivity;
import com.taufiq.e_bayar.Model.UserLogin.Data;
import com.taufiq.e_bayar.Model.UserLogin.User;
import com.taufiq.e_bayar.Request.Services;
import com.taufiq.e_bayar.Utils.ApiMethod;

import java.io.IOException;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginMethod {
    ApiMethod apiMethod;
//    SharedpreferencesMethod sharedpreferencesMethod;

    public void getUserLogin(TextView txtemail, TextView txtpassword, Context context, SharedPreferences sharedPreferences) {
        apiMethod = Services.getRetrofit().create(ApiMethod.class);
        String username = txtemail.getText().toString().trim();
//        Log.e("tag", username);
//        Data dataUser = new Data();
        Call<User> call = apiMethod.login(username, txtpassword.getText().toString());

        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if (response.isSuccessful()) {
                    User userResponse = response.body();
                    Data data = userResponse.getData();
                    Toast.makeText(context, "Anda Berhasil Login", Toast.LENGTH_LONG).show();

                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putString("nama", data.getNama());
                    editor.putString("email", data.getEmail());
                    editor.putString("no_hp", data.getNoTelephone());
                    editor.apply();

                    context.startActivity(new Intent(context, DashboardActivity.class));
                } else {
                    try {
                        String errorBody = response.errorBody().string();
                        Toast.makeText(context, "Silahkan Isikan kembali \n Username Dan Password Anda", Toast.LENGTH_LONG).show();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                Log.e("Log Error", t.getLocalizedMessage());
            }
        });
    }

    public boolean isLoggedIn(SharedPreferences sharedPreferences) {
        String nama = sharedPreferences.getString("nama", "");
        return !nama.isEmpty();
    }
}
