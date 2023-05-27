package com.taufiq.e_bayar.Request;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Services {
    private static final String BASE_URL = "https://taufiq-ridhoi.my.id/api/";
    private static Retrofit retrofit;


    public static Retrofit getRetrofit(){
        if (retrofit == null){
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }




//    private static Retrofit retrofit =
//                new Retrofit.Builder()
//                .baseUrl(BASE_URL)
//                .addConverterFactory(GsonConverterFactory.create())
//                .build();
//    private static TagihanApi tagihanApi = retrofit.create(TagihanApi.class);
//
//    public TagihanApi getTagihanApi(){
//        return tagihanApi;
//    }


}
