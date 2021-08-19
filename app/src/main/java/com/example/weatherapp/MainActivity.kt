package com.example.weatherapp

import android.content.Context
import android.content.SharedPreferences
import android.content.pm.PackageManager
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import com.google.android.gms.location.LocationServices

class MainActivity : AppCompatActivity() {


    //  private lateinit var fusedLocationClient: FusedLocationProviderClient


    override fun onCreate(savedInstanceState: Bundle?) {



        super.onCreate(savedInstanceState)
        fetchLocation()
        setContentView(R.layout.activity_main)
    }

    private fun fetchLocation() {

        var fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this)

        checkLocationPermission()
        val task = fusedLocationProviderClient.lastLocation

        task.addOnSuccessListener { it ->
            if (it != null) {

              var toastString  =( "Latitude is "+
                      it.latitude.toString() +"\n" + "Longitude is "
                      +it.longitude.toString())
                save(it.latitude.toInt(),it.longitude.toInt())
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
        }
        else
        {
            Toast.makeText(this,"Please Grant Permission",Toast.LENGTH_LONG).show()

            checkLocationPermission()
        }


    }

    private fun save(lat: Int, lon: Int) {
        val sharedPrefFile = getString(R.string.sharedpref)
        val sharedPreferences: SharedPreferences = this.getSharedPreferences(sharedPrefFile,
            Context.MODE_PRIVATE)
        val editor: SharedPreferences.Editor =  sharedPreferences.edit()
        editor.putInt("lat",lat)
        editor.putInt("lon",lon)
        editor.apply()
        editor.commit()

    }


}