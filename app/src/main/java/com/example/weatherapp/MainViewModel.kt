package com.example.weatherapp

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.weatherapp.jsontype.CurrentResponse
import com.example.weatherapp.jsontype.ForcastResponse
import com.example.weatherapp.repository.ApiRepository
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainViewModel( private val rep :ApiRepository) :ViewModel() {

    val errorMessage = MutableLiveData<String>()
    private val _currentResponse: MutableLiveData<CurrentResponse> = MutableLiveData()
    val currentResponse: LiveData<CurrentResponse>
        get() = _currentResponse

    fun current( lat:Int,lon :Int,appid: String) {



            val response = rep.getCurrent(lat, lon, appid)
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

    fun forecast( lat:Int,lon :Int,appid: String) {



        val response = rep.getForcast(lat, lon, appid)
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