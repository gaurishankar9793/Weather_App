package com.example.weatherapp.networkcalls

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RemoteDataSource {
    companion object {
        private const val BASE_URL = "http://api.openweathermap.org"

    }

    fun<Api> buildApi(
        api :Class<Api>
    ): Api {

        return  Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(
                OkHttpClient.Builder().also { client ->
                    val logging = HttpLoggingInterceptor()
                    logging.setLevel(HttpLoggingInterceptor.Level.HEADERS)
                    client.addInterceptor(logging)
                }.build()
            ).addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(api)

    }


}