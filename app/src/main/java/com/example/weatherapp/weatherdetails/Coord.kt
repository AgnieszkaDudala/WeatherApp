package com.example.weatherapp.weatherdetails

import com.google.gson.annotations.SerializedName

data class Coord(
    @SerializedName("lon")
    var lon: Float? = null,

    @SerializedName("lat")
    var lat: Float? = null
)
