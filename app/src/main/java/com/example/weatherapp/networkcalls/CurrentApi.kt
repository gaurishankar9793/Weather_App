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

private const val BASE_URL = "http://api.openweathermap.org"

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

        var currentApi: CurrentApi? = null
        fun getInstance() : CurrentApi {

            if (currentApi == null) {
                val retrofit = Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .client(
                        OkHttpClient.Builder().also { client ->
                            val logging = HttpLoggingInterceptor()
                            logging.setLevel(HttpLoggingInterceptor.Level.HEADERS)
                            client.addInterceptor(logging)
                        }.build()
                    ).addConverterFactory(GsonConverterFactory.create())
                    .build()
                  currentApi =   retrofit.create(CurrentApi::class.java)
            }
            return currentApi!!
        }

    }

}
