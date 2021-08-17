package com.example.weatherapp

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weatherapp.jsontype.CurrentResponse
import com.example.weatherapp.jsontype.ForcastResponse
import com.example.weatherapp.networkcalls.Resource
import com.example.weatherapp.repository.WeatherRepository
import kotlinx.coroutines.launch

class MainViewModel(private val repository: WeatherRepository) :ViewModel() {

    private val _currentResponse: MutableLiveData<Resource<CurrentResponse>> = MutableLiveData()
    val currentResponse: LiveData<Resource<CurrentResponse>>
        get() = _currentResponse

    fun current( lat:Int,lon :Int,appid: String) = viewModelScope.launch{
        _currentResponse.value = repository.current(lat,lon, appid)
    }

    //For getting forcast
    private val _forcastResponse: MutableLiveData<Resource<ForcastResponse>> = MutableLiveData()
    val forcastResponse: LiveData<Resource<ForcastResponse>>
        get() = _forcastResponse

    fun forcast( lat:Int,lon :Int,appid: String) = viewModelScope.launch{
        _forcastResponse.value = repository.forcast(lat,lon, appid)
    }

}