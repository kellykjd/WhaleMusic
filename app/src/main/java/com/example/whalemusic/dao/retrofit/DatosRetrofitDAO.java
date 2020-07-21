package com.example.whalemusic.dao.retrofit;

import com.example.whalemusic.utils.ApiService;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class DatosRetrofitDAO {

    private Retrofit retrofit;
    protected ApiService apiService;

    public DatosRetrofitDAO(String baseURL) {

        retrofit = new Retrofit.Builder()
                .baseUrl(baseURL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        apiService = retrofit.create(ApiService.class);
        }
    }
