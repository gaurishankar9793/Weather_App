package com.example.weatherapp.Fragments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.weatherapp.MainViewModel
import com.example.weatherapp.R


class CurrFragment : Fragment() {
    private var lat: Int = 232
    private var lon: Int = 0
    private val appid: String = "7ebaa839de68b76f9dfa66d483132d8a"
    //  private lateinit var fusedLocationClient: FusedLocationProviderClient
    private lateinit var viewModel :MainViewModel



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val forcastButton: Button = view.findViewById(R.id.button)
        val temperatureView: TextView = view.findViewById(R.id.textView2)
        val headerView : TextView = view.findViewById(R.id.textView)
        viewModel =  ViewModelProvider(this).get(MainViewModel::class.java)

        viewModel.currentResponse.observe(viewLifecycleOwner, Observer {
            Toast.makeText(requireContext(), it.toString(), Toast.LENGTH_SHORT).show()
                   temperatureView.text = ("Current Temp:" +ktoC(it.main.temp) +"\n"
                           +"Min temp :" + ktoC(it.main.temp_min) +"\n"+"Max temp :" +ktoC(it.main.temp_max)
              +"\n"+ "Description " + it.weather[0].description+"\n")
//
        }
        )
        temperatureView.text = lat.toString()
        val sharedPref = activity?.getSharedPreferences(
            getString(R.string.sharedpref), Context.MODE_PRIVATE
        )
        val lat = sharedPref?.getInt("lat",23)
        val lon =sharedPref?.getInt("lon",80)
        var toastString  =( "Latitude is "+
                lat.toString() +"\n" + "Longitude is "
                +lon.toString())
        headerView.text = toastString
        if (lat != null && lon!=null) {
            viewModel.current(lat, lon, appid)
        }



        forcastButton.setOnClickListener {
            findNavController().navigate(R.id.to_forecast)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_curr, container, false)
    }
    private fun ktoC(temp: Double): String {
        return (temp - 273.15).toFloat().toString().trim()
    }

}