package com.example.weatherapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText

class SearchCityActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val insertCityname = findViewById<EditText>(R.id.insert_cityname).apply {

        }
        val btnFinder = findViewById<Button>(R.id.btn_find_weather)
        btnFinder.setOnClickListener {
            val intent = Intent(this, ShowWeatherActivity::class.java)
            startActivity(intent)
        }

    }
}