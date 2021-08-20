package com.example.weatherapp.Fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.example.weatherapp.MainViewModel
import com.example.weatherapp.R


class CurrFragment : Fragment() {

    private val viewModel: MainViewModel by activityViewModels()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val forcastButton: Button = view.findViewById(R.id.button)
        val temperatureView: TextView = view.findViewById(R.id.textView2)



        viewModel.currentResponse.observe(viewLifecycleOwner, Observer {
            Toast.makeText(requireContext(), it.toString(), Toast.LENGTH_SHORT).show()
            temperatureView.text = ("Current Temp:" + ktoC(it.main.temp) + "\n"
                    + "Min temp :" + ktoC(it.main.temp_min) + "\n" + "Max temp :" + ktoC(it.main.temp_max)
                    + "\n" + "Description " + it.weather[0].description + "\n")
//
        }
        )


     viewModel.lon.observe(viewLifecycleOwner, Observer {
         viewModel.current()
     })



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