package com.example.weatherapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText

class SearchCityActivity : AppCompatActivity() {
    val apiKey: String = "7dc17897c83e4cfa1c7b81336e0628a5"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        title = "Search city"
        val insertCityname = findViewById<EditText>(R.id.insert_cityname)

        val btnFinder = findViewById<Button>(R.id.btn_find_weather)
        btnFinder.setOnClickListener {
            val intent = Intent(this, ShowWeatherActivity::class.java)
            startActivity(intent)
        }

    }
}