package com.example.weatherapp

import android.annotation.SuppressLint
import android.content.pm.PackageManager
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.lifecycle.ViewModelProvider
import com.google.android.gms.location.LocationServices

class MainActivity : AppCompatActivity() {

   private lateinit var viewModel :MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {



        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
         viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        checkLocationPermission()
    }

    @SuppressLint("MissingPermission")
    private fun fetchLocation() {

        var fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this)
        val task = fusedLocationProviderClient.lastLocation

        task.addOnSuccessListener { it ->
            if (it != null) {

              var toastString  =( "Latitude is "+
                      it.latitude.toString() +"\n" + "Longitude is "
                      +it.longitude.toString())

                viewModel.setLatitude(it.latitude.toInt())
                viewModel.setLongitude(it.longitude.toInt())

                Toast.makeText(this, toastString, Toast.LENGTH_LONG).show()
            }
        }



    }
    private fun checkLocationPermission(){

        if (ActivityCompat.checkSelfPermission(
                this,
                android.Manifest.permission.ACCESS_FINE_LOCATION
            )
            != PackageManager.PERMISSION_GRANTED &&
            ActivityCompat.checkSelfPermission(
                this,
                android.Manifest.permission.ACCESS_COARSE_LOCATION
            )
            != PackageManager.PERMISSION_GRANTED
        ) {

            ActivityCompat.requestPermissions(
                this,
                arrayOf(android.Manifest.permission.ACCESS_FINE_LOCATION),
                101
            )

        }
    else
            fetchLocation()
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    )  {

        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        if (grantResults.size > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED)
        {
            Toast.makeText(this,"Thanks for the Permission",Toast.LENGTH_LONG).show()
            Log.d("perm","ew")
            fetchLocation()
        }
        else
        {
            Toast.makeText(this,"Please Grant Permission",Toast.LENGTH_LONG).show()

            checkLocationPermission()
        }


    }



}