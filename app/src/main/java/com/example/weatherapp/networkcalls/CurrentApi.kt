package com.example.weatherapp.networkcalls

import com.example.weatherapp.jsontype.CurrentResponse
import com.example.weatherapp.jsontype.ForcastResponse
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query



interface CurrentApi {

    @GET("/data/2.5/weather")
    fun getCurrent(
        @Query("lat") lat: Int,
        @Query("lon") lon: Int,
        @Query("appid") appid: String,
    ): Call<CurrentResponse>


    @GET("/data/2.5/forecast")
    fun getForcast(
        @Query("lat") lat: Int,
        @Query("lon") lon: Int,
        @Query("appid") appid: String,
    ): Call<ForcastResponse>

    companion object {



    }

}
