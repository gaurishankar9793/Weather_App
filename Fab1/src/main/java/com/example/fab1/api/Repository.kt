package com.example.fab1.api

import com.example.fab1.api.Response.Response


object Repository {
    private val client = Client().getInstance()
    fun postData(details: Details) = client.sendData(details)

}