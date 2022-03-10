package com.example.weatherapp.retrofitInterface

import com.example.weatherapp.weatherdetails.DataModel
import com.example.weatherapp.weatherdetails.Weather
import retrofit2.Call
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiCallInterface {

    @GET("weather")
    suspend fun getWeather(@Query("q") cityname: String, @Query("appid") appid: String): Response<DataModel>

   /* companion object {
        private const val BASE_URL = "https://api.openweathermap.org/data/2.5/"
        fun create(): ApiCallInterface {
            val retrofit = Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(BASE_URL)
                .build()
            return retrofit.create(ApiCallInterface::class.java)
        }
    }*/
}