package com.example.weatherapp.api_data

import android.content.Context
import android.util.Log
import androidx.compose.runtime.MutableState
import com.android.volley.Request
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.weatherapp.API_KEY
import com.example.weatherapp.models.WeatherModel
import org.json.JSONObject


fun getWeatherData(
    city: String,
    context: Context,
    daysList: MutableState<List<WeatherModel>>,
    currentDay: MutableState<WeatherModel>
) {

    val BASE_URL =
        "https://api.weatherapi.com/v1/forecast.json?key=$API_KEY" +
                "&q=$city" +
                "&days=" +
                "14" +
                "&aqi=no&alerts=no"

    val queue = Volley.newRequestQueue(context)
    val stringRequest = StringRequest(
        Request.Method.GET,
        BASE_URL,
        { response ->
            val list = getWeatherDaily(response)
            currentDay.value = list[0]
            daysList.value = list

        },
        { error ->
            Log.d("MyLog", "Error: $error")
        }
    )

    queue.add(stringRequest)
}
