package com.example.weatherapp.retrofitInterface

import com.example.weatherapp.weatherdetails.Weather
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface ApiCallInterface {

    @GET("weather")
    fun getWeather() : Call<List<Weather>>

    companion object {
        val BASE_URL = "https://api.openweathermap.org/data/2.5/"
        fun create() : ApiCallInterface {
            val retrofit = Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(BASE_URL)
                .build()
            return retrofit.create(ApiCallInterface::class.java)
        }
    }
}