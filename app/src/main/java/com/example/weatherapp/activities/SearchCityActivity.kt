package com.example.weatherapp.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.KeyEvent
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.weatherapp.R
import com.example.weatherapp.retrofitInterface.ApiCallInterface
import com.example.weatherapp.weatherdetails.DataModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class SearchCityActivity : AppCompatActivity() {
    companion object {
        private const val apiKey = "7dc17897c83e4cfa1c7b81336e0628a5"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search_city)

        val insertCityName = findViewById<EditText>(R.id.insert_cityname)

        findViewById<Button>(R.id.btn_find_weather)
            .setOnClickListener {
                if (insertCityName.text.isEmpty()) {
                    Toast.makeText(this, "Please fill the city name field.", Toast.LENGTH_SHORT)
                        .show()
                } else {
                    retrofitCall(insertCityName.text.toString())
                }
            }
    }

    private fun retrofitCall(cityName: String) {
        val service = buildRetrofitService()

        CoroutineScope(Dispatchers.IO).launch {
            val response = service.getWeather(cityName, apiKey)
            withContext(Dispatchers.Main) {
                if (response.isSuccessful) {
                    sendIntentExtras(response)
                }
            }
        }
    }

    private fun buildRetrofitService(): ApiCallInterface {
        val retrofit = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl("https://api.openweathermap.org/data/2.5/")
            .build()

        return retrofit.create(ApiCallInterface::class.java)
    }

    private fun sendIntentExtras(response: Response<DataModel>) {
        val items = response.body()
        val intent = Intent(this, ShowWeatherActivity::class.java)
        intent.putExtra("city_name", items?.name)
        intent.putExtra("temperature", items?.main?.temp)
        intent.putExtra("weather", items?.weather?.get(0)?.description)
        intent.putExtra("sunrise", items?.sys?.sunrise)
        intent.putExtra("sunset", items?.sys?.sunset)
        intent.putExtra("pressure", items?.main?.pressure)
        intent.putExtra("humidity", items?.main?.humidity)
        startActivity(intent)
    }
}
