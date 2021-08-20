package com.example.weatherapp.repository

import com.example.weatherapp.networkcalls.DataClass

object ApiRepository {

    private  val dataClass =DataClass().getInstance()
    fun getCurrent(lat :Int,lon :Int,appid : String) = dataClass.getCurrent(lat, lon, appid)

    fun getForcast(lat :Int,lon :Int,appid : String)  = dataClass.getForcast(lat,lon, appid)
}