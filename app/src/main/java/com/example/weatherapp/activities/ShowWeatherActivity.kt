package com.example.weatherapp.activities

import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.weatherapp.R
import java.math.RoundingMode
import java.text.DecimalFormat
import java.time.ZoneId
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter


class ShowWeatherActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_show_weather)

        findViewById<ImageView>(R.id.back_to_search)
            .setOnClickListener{
                goToSearchCityActivity()
            }

        setCurrentTimeAndData()
        setExtrasToTextViews()

    }
    private fun goToSearchCityActivity(){
        val intent = Intent(this, SearchCityActivity::class.java)
        startActivity(intent)
    }

    private fun setCurrentTimeAndData() {
        val z = ZoneId.of("Poland")
        val zdt = ZonedDateTime.now(z)
        val formatter = DateTimeFormatter.ofPattern(" HH:mm, dd/MM/yyyy")
        val formatted = zdt.format(formatter)

        findViewById<TextView>(R.id.updated_at).apply {
            text = "updated at: $formatted"
        }
    }

    private fun setExtrasToTextViews() {
        findViewById<TextView>(R.id.text_cityname).apply {
            text = intent.getStringExtra("city_name")
        }
        findViewById<TextView>(R.id.text_temperature).apply {
            var tempInK = intent.getFloatExtra("temperature", 0.0f)
            text = convertKelwinsToCelsius(tempInK)
        }
        findViewById<TextView>(R.id.text_main_weather).apply {
            text = intent.getStringExtra("weather")
        }
        findViewById<TextView>(R.id.sunrise).apply {
            val timeInMSeconds = intent.getIntExtra("sunrise", 0).toLong()
            text = convertTimeInMillisToHourAndMinute(timeInMSeconds)
        }

        findViewById<TextView>(R.id.sunset).apply {
            val timeInMSeconds = intent.getIntExtra("sunset", 0).toLong()
            text = convertTimeInMillisToHourAndMinute(timeInMSeconds)
        }

        findViewById<TextView>(R.id.pressure).apply {
            text = intent.getIntExtra("pressure", 0).toString() + " hPa"
        }
        findViewById<TextView>(R.id.humidity).apply {
            text = intent.getIntExtra("humidity", 0).toString() + "%"
        }
    }

    private fun convertTimeInMillisToHourAndMinute(timeInMillis: Long): String {
        val m: Long = timeInMillis / 60 % 60
        val h: Long = timeInMillis / (60 * 60) % 24

        return String.format("%d:%02d", h, m)
    }

    private fun convertKelwinsToCelsius(tempInK: Float): String {
        var tempInC = tempInK - 273.15

        val df = DecimalFormat("#")
        df.roundingMode = RoundingMode.CEILING

        return df.format(tempInC).toString() + "°C"
    }
}