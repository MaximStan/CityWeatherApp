package com.example.weatherapp.ui

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.weatherapp.models.WeatherModel

@Composable
fun WeatherMainList(list: List<WeatherModel>, currentDay: MutableState<WeatherModel>){

    LazyColumn(
        modifier = Modifier.fillMaxSize().padding(bottom = 8.dp)
    ) {
        itemsIndexed(list) { _, weatherModelItem ->

            WeatherListItem(weatherModelItem, currentDay)
        }

    }
}
