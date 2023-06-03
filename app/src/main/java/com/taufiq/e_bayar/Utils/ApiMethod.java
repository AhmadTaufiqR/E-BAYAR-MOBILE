package com.taufiq.e_bayar.Utils;

import com.taufiq.e_bayar.Model.ModelTahun.DataTahun;
import com.taufiq.e_bayar.Model.ModelTahun.ModelTahun;
import com.taufiq.e_bayar.Model.UserLogin.Data;
import com.taufiq.e_bayar.Model.UserLogin.User;
import com.taufiq.e_bayar.Model.spp.Tagihan;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface ApiMethod {


//    http://192.168.1.14/api/e-bayar_api.php
    @GET("pembayaran/all_tahun_spp/{tahun}")
    Call<Tagihan> getTagihanSPP(@Path("tahun") int tahun);

    @GET("pembayaran/all_ug/{tahun}")
    Call<Tagihan> getTagihanUG(@Path("tahun") int tahun);

    @GET("pembayaran")
    Call<Tagihan> getAllTagihanSPP();

    @FormUrlEncoded
    @POST("siswa/login")
    Call<User> login(@Field("username") String username, @Field("password") String passsword);

    @GET("pembayaran/getAllSpp")
    Call<ModelTahun> getTahunTagihan();
}
