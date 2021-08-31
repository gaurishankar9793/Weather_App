package com.example.fab1.api

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
private const val BASE_URL = "http://10.0.2.2:4000"

class Client {

    var apiCall: ApiInterface? = null
    fun getInstance() : ApiInterface {

        if (apiCall == null) {
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
            apiCall =   retrofit.create(ApiInterface::class.java)
        }
        return apiCall!!
    }
}
