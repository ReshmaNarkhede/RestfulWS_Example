package com.example.restful_ws;

import com.example.restful_ws.model.SongModel;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiInterface {
    @GET("search?term=Michael+jackson")
    Call<SongModel> getSongList();
}
