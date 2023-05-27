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

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginMethod {
    ApiMethod apiMethod;
//    SharedpreferencesMethod sharedpreferencesMethod;

    public void getUserLogin(TextView txtemail, TextView txtpassword, Context context, SharedPreferences sharedPreferences) {
        apiMethod = Services.getRetrofit().create(ApiMethod.class);
        Data dataUser = new Data(txtemail.getText().toString(), txtpassword.getText().toString());
        Call<User> call = apiMethod.login(dataUser);

        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if (response.isSuccessful()) {
                    User userResponse = response.body();
                    Toast.makeText(context, "Anda Berhasil Login", Toast.LENGTH_LONG).show();

//                     Simpan data ke dalam SharedPreferences
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putString("nama", userResponse.getData().getNama());
                    editor.putString("email", userResponse.getData().getEmail());
                    editor.putString("no_hp", userResponse.getData().getNoTelephone());
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
