package com.taufiq.e_bayar.Utils;

import com.taufiq.e_bayar.Model.spp.Tagihan;

import retrofit2.Call;
import retrofit2.http.GET;

public interface TagihanApi {


//    http://192.168.1.14/api/e-bayar_api.php
    @GET("pembayaran")
    Call<Tagihan> getAllTagihan();
}
