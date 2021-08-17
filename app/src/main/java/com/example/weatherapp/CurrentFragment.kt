package com.example.weatherapp

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.example.mvvmauth.network.CurrentApi
import com.example.mvvmauth.network.ForcastApi
import com.example.weatherapp.databinding.FragmentCurrentBinding
import com.example.weatherapp.networkcalls.Resource
import com.example.weatherapp.repository.WeatherRepository
import com.google.android.gms.location.FusedLocationProviderClient


class CurrentFragment : BaseFragment<MainViewModel, FragmentCurrentBinding, WeatherRepository>() {
    private var lat: Int = 0
    private var lon: Int = 0
    private val appid: String = "7ebaa839de68b76f9dfa66d483132d8a"
    private lateinit var fusedLocationClient: FusedLocationProviderClient



    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {


        val forcastButton: Button = view.findViewById(R.id.button)
        val temperatureView: TextView = binding.textView2


        viewModel.currentResponse.observe(viewLifecycleOwner, Observer {
            when (it) {

                is Resource.Success -> {
                    //Toast.makeText(requireContext(), it.toString(), Toast.LENGTH_SHORT).show()
                    temperatureView.text = ("Current Temp:" +it.value.main.temp.toString() +"\n"
                     +"Min temp :" + it.value.main.temp_min +"\n"+"Max temp :" +it.value.main.temp_min
                   +"\n"+ "Description" + it.value.weather[0].description+"\n")
                }
                is Resource.Failure -> {
                    Toast.makeText(requireContext(), "Failure", Toast.LENGTH_LONG).show()
                }
            }
        })
        temperatureView.text = lat.toString()
        val sharedPref = activity?.getSharedPreferences(
            getString(R.string.sharedpref), Context.MODE_PRIVATE
        )
        val lat = sharedPref?.getInt("lat",23)
        val lon =sharedPref?.getInt("lon",80)
        if (lat != null && lon!=null) {
            viewModel.current(lat,lon,appid)
        }



        forcastButton.setOnClickListener {
            findNavController().navigate(R.id.toForcast)
        }


    }






    override fun getViewModel() = MainViewModel::class.java

    override fun getFragmentBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ) = FragmentCurrentBinding.inflate(inflater, container, false)

    override fun getFragmentRepository() = WeatherRepository(
        remoteDataSource.buildApi(
            CurrentApi::class.java
        ),
        remoteDataSource.buildApi(
            ForcastApi::class.java
        )
    )


}