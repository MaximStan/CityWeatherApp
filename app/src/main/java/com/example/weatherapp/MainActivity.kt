package com.example.weatherapp

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.android.volley.Request
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.weatherapp.api_data.getWeatherData
import com.example.weatherapp.models.WeatherModel
import com.example.weatherapp.ui.theme.DeepBlue
import com.example.weatherapp.ui.theme.WeatherAppTheme
import org.json.JSONObject
import com.example.weatherapp.screens.WeatherMainCard
import com.example.weatherapp.screens.WeatherSplashScreen
import com.example.weatherapp.screens.WeatherTabLayout
import com.example.weatherapp.ui.DialogSearch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            WeatherAppTheme() {

                val navController = rememberNavController()

                NavHost(
                    navController = navController,
                    startDestination = "weather_splash_screen"
                ) {
                    composable("weather_splash_screen") { WeatherSplashScreen(navController = navController) }
                    composable("weather_main_screen") {

                        val daysList = remember {
                            mutableStateOf(listOf<WeatherModel>())
                        }

                        val showSearchDialog = remember { mutableStateOf(false) }


                        val currentDay = remember {
                            mutableStateOf(
                                WeatherModel(
                                    "",
                                    "",
                                    "0.0",
                                    "",
                                    "",
                                    "0.0",
                                    "0.0",
                                    "",
                                    "",
                                    ""

                                )
                            )
                        }

                        if(showSearchDialog.value){
                            DialogSearch(
                                showSearchDialog,
                                onSubmit = { city ->
                                    getWeatherData( city, this@MainActivity, daysList, currentDay)
                                }
                            )
                        }


                        getWeatherData("New York", this@MainActivity, daysList, currentDay)

                        Column(
                            modifier = Modifier
                                .background(color = MaterialTheme.colors.background)
                                .fillMaxSize()
                        ) {

                            WeatherMainCard(
                                currentDay,
                                onClickRefresh = {
                                    getWeatherData(
                                        city = currentDay.value.city , this@MainActivity, daysList, currentDay
                                    )
                                },
                                onClickSearch = {
                                    showSearchDialog.value = true
                                }
                            )
                            WeatherTabLayout(daysList, currentDay)

                        }

                    }
                }
            }
        }
    }
}








