package com.example.weatherapp.networkcalls

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
private const val BASE_URL = "http://api.openweathermap.org"
class DataClass {

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