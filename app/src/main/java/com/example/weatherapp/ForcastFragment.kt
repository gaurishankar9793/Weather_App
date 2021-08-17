package com.example.weatherapp

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.Observer
import com.example.mvvmauth.network.CurrentApi
import com.example.mvvmauth.network.ForcastApi
import com.example.weatherapp.databinding.FragmentForcastBinding
import com.example.weatherapp.networkcalls.Resource
import com.example.weatherapp.repository.WeatherRepository


class ForcastFragment : BaseFragment<MainViewModel, FragmentForcastBinding, WeatherRepository>() {
    private val appid: String = "7ebaa839de68b76f9dfa66d483132d8a"

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        var forcastText :String = ""

        val forcast: TextView = binding.longtext
        viewModel.forcastResponse.observe(viewLifecycleOwner, Observer { it ->
            when (it) {

                is Resource.Success -> {
                    //Toast.makeText(requireContext(), it.toString(), Toast.LENGTH_SHORT).show()
                   it.value.list.forEach{
                      forcastText+=  "temp :" + it.main.temp + " weather :" + it.weather[0].description +"\n"
                    }
                    forcast.text = forcastText
                }
                is Resource.Failure -> {
                    Toast.makeText(requireContext(), "Failure", Toast.LENGTH_LONG).show()
                }
            }
        })

        val sharedPref = activity?.getSharedPreferences(
            getString(R.string.sharedpref), Context.MODE_PRIVATE
        )
        val lat = sharedPref?.getInt("lat", 23)
        val lon = sharedPref?.getInt("lon", 80)
        if (lat != null && lon != null) {
            viewModel.forcast(lat, lon, appid)
        }
    }

    override fun getViewModel() = MainViewModel::class.java

    override fun getFragmentBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ) = FragmentForcastBinding.inflate(inflater, container, false)

    override fun getFragmentRepository() = WeatherRepository(
        remoteDataSource.buildApi(
            CurrentApi::class.java
        ),
        remoteDataSource.buildApi(
            ForcastApi::class.java
        )
    )


}