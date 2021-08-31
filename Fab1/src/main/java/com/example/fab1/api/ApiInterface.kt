package com.example.fab1.api

import com.example.fab1.api.Response.Response
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.Field
import retrofit2.http.POST

interface ApiInterface {
    @POST("/users")
    fun sendData(
        @Body details: Details
    ) :Call<Response>
}