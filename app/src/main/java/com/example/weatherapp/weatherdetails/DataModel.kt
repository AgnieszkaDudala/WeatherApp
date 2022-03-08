package com.example.weatherapp.weatherdetails

import com.google.gson.annotations.SerializedName

data class DataModel(
    @SerializedName("coord")
    private var coord: Coord? = null,

    @SerializedName("weather")
    private val weather: List<Weather>? = null,

    @SerializedName("base")
    private val base: String? = null,

    @SerializedName("main")
    private val main: Main? = null,

    @SerializedName("visibility")
    private val visibility: Int? = null,

    @SerializedName("wind")
    private val wind: Wind? = null,

    @SerializedName("clouds")
    private val clouds: Clouds? = null,

    @SerializedName("dt")
    private val dt: Int? = null,

    @SerializedName("sys")
    private val sys: Sys? = null,

    @SerializedName("timezone")
    private val timezone: Int? = null,

    @SerializedName("id")
    private val id: Int? = null,

    @SerializedName("name")
    private val name: String? = null,

    @SerializedName("cod")
    private var cod: Int? = null
)