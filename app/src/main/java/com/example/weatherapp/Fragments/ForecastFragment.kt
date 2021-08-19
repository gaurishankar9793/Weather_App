package com.example.weatherapp.Fragments

import ViewModelFactory
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.weatherapp.MainViewModel
import com.example.weatherapp.R
import com.example.weatherapp.networkcalls.CurrentApi
import com.example.weatherapp.repository.ApiRepository


class ForecastFragment : Fragment() {
    private val appid: String = "7ebaa839de68b76f9dfa66d483132d8a"
    private val currentApi = CurrentApi.getInstance()
    private lateinit var viewModel : MainViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
      val  forcastView: TextView = view.findViewById(R.id.longtext)
    var forcastText = ""
        viewModel =  ViewModelProvider(this, ViewModelFactory(ApiRepository(currentApi ))).get(MainViewModel::class.java)

        viewModel.forcastResponse.observe(viewLifecycleOwner, Observer {
            Toast.makeText(requireContext(),"Success", Toast.LENGTH_SHORT).show()
            it.list.forEach{
                forcastText+=  "temp :" + it.main.temp + " weather :" + it.weather[0].description +"\n"
            }
            forcastView.text = forcastText

        }
        )

        val sharedPref = activity?.getSharedPreferences(
            getString(R.string.sharedpref), Context.MODE_PRIVATE
        )
        val lat = sharedPref?.getInt("lat",23)
        val lon =sharedPref?.getInt("lon",80)
        if (lat != null && lon!=null) {
            viewModel.forecast(lat, lon, appid)
        }

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_forecast, container, false)
    }


}