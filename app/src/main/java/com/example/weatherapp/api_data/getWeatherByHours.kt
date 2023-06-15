package com.example.weatherapp.api_data

import com.example.weatherapp.models.WeatherModel
import org.json.JSONArray
import org.json.JSONObject

 fun getWeatherByHours(hours: String): List<WeatherModel> {

    if (hours.isEmpty()) return listOf()

    val hoursArray = JSONArray(hours)
    val list = ArrayList<WeatherModel>()
    for (i in 0 until hoursArray.length()) {
        val item = hoursArray[i] as JSONObject
        list.add(
            WeatherModel(
                city = "",
                time = item.getString("time"),
                currentTemp = item.getString("temp_c").toFloat().toInt().toString() + "°C",
                condition = item.getJSONObject("condition").getString("text"),
                icon = item.getJSONObject("condition").getString("icon"),
                maxTemp = "",
                minTemp = "",
                hours = "",
                avgHumidity = "",
                wind = "",
            )
        )
    }
    return list
}