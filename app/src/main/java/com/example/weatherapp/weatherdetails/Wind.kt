package com.example.weatherapp.weatherdetails

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Wind(
    @SerializedName("speed")
    var speed: Float? = null,

    @SerializedName("deg")
    var deg: Int? = null,

    @SerializedName("gust")
    var gust: Float? = null,
)

