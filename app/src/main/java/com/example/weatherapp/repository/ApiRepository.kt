package com.example.weatherapp.repository

import com.example.weatherapp.networkcalls.CurrentApi

class ApiRepository(private  val currentApi: CurrentApi) {


    fun getCurrent(lat :Int,lon :Int,appid : String) = currentApi.getCurrent(lat, lon, appid)

    fun getForcast(lat :Int,lon :Int,appid : String)  = currentApi.getForcast(lat,lon, appid)
}