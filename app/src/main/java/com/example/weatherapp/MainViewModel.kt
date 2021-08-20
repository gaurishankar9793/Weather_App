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

    private val _lat: MutableLiveData<Int> = MutableLiveData()
    val lat: LiveData<Int>
        get() = _lat

    private val _lon: MutableLiveData<Int> = MutableLiveData()
    val lon: LiveData<Int>
        get() = _lon
    fun setLatitude(field : Int)
    {
        _lat.value = field
    }

    fun setLongitude(field : Int)
    {
        _lon.value = field
    }




    fun current() {


            val response = ApiRepository.getCurrent(_lat.value!!, _lon.value!!, appid)
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



        val response = ApiRepository.getForcast(_lat.value!!, _lon.value!!, appid)
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