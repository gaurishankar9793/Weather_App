package com.example.weatherapp.repository

import com.example.mvvmauth.network.CurrentApi
import com.example.mvvmauth.network.ForcastApi


class WeatherRepository(
    private val api: CurrentApi,
    private val forcastApi: ForcastApi
) : BaseRepository(){
    suspend fun current(
        lat: Int,
        lon: Int,
        appid: String
    ) = safeApiCall {

        api.current(lat, lon, appid)
    }
    suspend fun forcast(
        lat: Int,
        lon: Int,
        appid: String
    ) = safeApiCall {

        forcastApi.forcast(lat, lon, appid)
    }
}