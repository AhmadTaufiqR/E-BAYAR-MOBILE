package com.taufiq.e_bayar.Utils;

import com.taufiq.e_bayar.Model.UserLogin.Data;
import com.taufiq.e_bayar.Model.UserLogin.User;
import com.taufiq.e_bayar.Model.spp.Tagihan;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface ApiMethod {


//    http://192.168.1.14/api/e-bayar_api.php
    @GET("pembayaran")
    Call<Tagihan> getAllTagihan();

    @POST("siswa/login")
    Call<User> login(@Body Data user);
}
