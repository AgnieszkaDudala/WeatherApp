package com.example.weatherapp.activities

import android.os.Build
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.weatherapp.R
import java.math.RoundingMode
import java.text.DecimalFormat
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.format.DateTimeFormatter


class ShowWeatherActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_show_weather)

        title = ""

        setCurrentTimeAndData()
        setExtrasToTextViews()
    }

    private fun setCurrentTimeAndData(){
        val currentDateTime = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            LocalDateTime.now()
        } else {
            TODO("VERSION.SDK_INT < O")
        }
        val updatedAt = findViewById<TextView>(R.id.updated_at).apply {
            text = "Updated at: " + currentDateTime.format(DateTimeFormatter.ofPattern("HH:mm, dd/MM/yyyy"))
        }
    }

    private fun setExtrasToTextViews(){
        val cityname = findViewById<TextView>(R.id.text_cityname).apply {
            text = intent.getStringExtra("cityname").toString()
        }
        val temperature = findViewById<TextView>(R.id.text_temperature).apply {
            var tempInK = intent.getFloatExtra("temperature", 0.0f)
            text = convertKelwinsToCelsius(tempInK)
        }
        val weather = findViewById<TextView>(R.id.text_main_weather).apply {
            text = intent.getStringExtra("weather").toString()
        }
        val sunriseTime = findViewById<TextView>(R.id.sunrise).apply {
            val timeInMSeconds = intent.getIntExtra("sunrise", 0).toLong()
            text = convertTimeInMillisToHourAndMinute(timeInMSeconds)
        }

        val sunsetTime = findViewById<TextView>(R.id.sunset).apply {
            val timeInMSeconds = intent.getIntExtra("sunset", 0).toLong()
            text = convertTimeInMillisToHourAndMinute(timeInMSeconds)
        }

        val pressure = findViewById<TextView>(R.id.pressure).apply {
            text = intent.getIntExtra("pressure", 0).toString() + " hPa"
        }
        val humadity = findViewById<TextView>(R.id.humadity).apply {
            text = intent.getIntExtra("humadity", 0).toString() + "%"
        }
    }

    private fun convertTimeInMillisToHourAndMinute(timeInMillis: Long): String {
        val m: Long = timeInMillis / 60 % 60
        val h: Long = timeInMillis / (60 * 60) % 24

        return String.format("%d:%02d", h, m)
    }

    private fun convertKelwinsToCelsius(tempInK: Float): String{
        var tempInC = tempInK - 273.15

        val df = DecimalFormat("#")
        df.roundingMode = RoundingMode.CEILING

        return df.format(tempInC).toString() + "°C"
    }
}