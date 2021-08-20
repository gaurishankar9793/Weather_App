package com.example.weatherapp.Fragments


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import com.example.weatherapp.MainViewModel
import com.example.weatherapp.R


class ForecastFragment : Fragment() {

    private val viewModel: MainViewModel by activityViewModels()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val forcastView: TextView = view.findViewById(R.id.longtext)
        var forcastText = ""


        viewModel.forcastResponse.observe(viewLifecycleOwner, Observer {
            Toast.makeText(requireContext(), "Success", Toast.LENGTH_SHORT).show()
            it.list.take(10).forEach {
                forcastText += "temp :" + ktoC(it.main.temp) + " weather :" + it.weather[0].description + "\n"
            }
            forcastView.text = forcastText

        }
        )

        viewModel.lat.observe(viewLifecycleOwner, Observer {
            viewModel.forecast()
        })
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_forecast, container, false)
    }

    private fun ktoC(temp: Double): String {
        return (temp - 273.15).toFloat().toString().trim()
    }

}