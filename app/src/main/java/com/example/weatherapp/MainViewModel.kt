package com.example.weatherapp

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.weatherapp.jsontype.CurrentResponse
import com.example.weatherapp.jsontype.ForcastResponse
import com.example.weatherapp.repository.ApiRepository
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainViewModel(application: Application) :AndroidViewModel(application) {
    private val appid: String = "7ebaa839de68b76f9dfa66d483132d8a"
    val errorMessage = MutableLiveData<String>()
    private val _currentResponse: MutableLiveData<CurrentResponse> = MutableLiveData()
    val currentResponse: LiveData<CurrentResponse>
        get() = _currentResponse

    var lat :Int = 0
    fun setLatitude(field : Int)
    {
        this.lat = field
    }

    var lon :Int = 0
    fun setLongitude(field : Int)
    {
        this.lon = field
    }



    fun current() {
            val response = ApiRepository.getCurrent(lat, lon, appid)
            response.enqueue(object : Callback<CurrentResponse> {
                override fun onResponse(call: Call<CurrentResponse>, response: Response<CurrentResponse>) {
                  _currentResponse.postValue(response.body())
                }

                override fun onFailure(call: Call<CurrentResponse>, t: Throwable) {
                    errorMessage.postValue(t.message)
                }
            })

    }

    //For getting forcast
    private val _forcastResponse: MutableLiveData<ForcastResponse> = MutableLiveData()
    val forcastResponse: LiveData<ForcastResponse>
        get() = _forcastResponse

    fun forecast() {



        val response = ApiRepository.getForcast(lat, lon, appid)
        response.enqueue(object : Callback<ForcastResponse> {
            override fun onResponse(call: Call<ForcastResponse>, response: Response<ForcastResponse>) {
                _forcastResponse.postValue(response.body())
            }

            override fun onFailure(call: Call<ForcastResponse>, t: Throwable) {
                errorMessage.postValue(t.message)
            }
        })

    }

}