package com.example.mvvmauth.network

import com.example.weatherapp.jsontype.ForcastResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface ForcastApi {
    @GET("/data/2.5/forecast")
    suspend fun forcast(
        @Query("lat") lat: Int,
        @Query("lon") lon: Int,
        @Query("appid") appid: String,
    ) : ForcastResponse
}
