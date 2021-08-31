package com.example.fab1

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.fab1.api.Details
import com.example.fab1.api.Repository
import com.example.fab1.api.Response.Response
import retrofit2.Call
import retrofit2.Callback

class ViewModel :ViewModel()  {
    val errorMessage = MutableLiveData<String>()
    private val _currentResponse: MutableLiveData<Response> = MutableLiveData()
    val currentResponse: LiveData<Response>
        get() = _currentResponse

    fun current(details: Details) {


        val response = Repository.postData(details)
        response.enqueue(object : Callback<Response> {
            override fun onResponse(call: Call<Response>, response: retrofit2.Response<Response>) {
                _currentResponse.postValue(response.body())
            }

            override fun onFailure(call: Call<Response>, t: Throwable) {
                errorMessage.postValue(t.message)
            }
        })

    }
}