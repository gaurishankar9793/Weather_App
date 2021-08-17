package com.example.mvvmauth.network

import com.example.weatherapp.jsontype.CurrentResponse
import retrofit2.http.GET
import retrofit2.http.Query


interface CurrentApi {

    @GET("/data/2.5/weather")
    suspend fun current(
        @Query("lat") lat: Int,
        @Query("lon") lon: Int,
        @Query("appid") appid: String,
    ) : CurrentResponse


}
