package com.example.weatherapp.jsontype

data class ForcastResponse(
    val city: City,
    val cnt: Int,
    val cod: String,
    val list: List<ForcastX>,
    val message: Int
)