package com.example.weatherapp.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.weatherapp.R
import com.example.weatherapp.models.WeatherModel
import com.example.weatherapp.screens.WeatherMainCard
import com.example.weatherapp.ui.theme.DarkBlue
import com.example.weatherapp.ui.theme.Shapes

@Composable
fun WeatherListItem(weatherItem: WeatherModel, currentDay: MutableState<WeatherModel>) {

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 5.dp)
            .clickable {

                if (weatherItem.hours.isEmpty()) {
                    return@clickable
                }
                currentDay.value = weatherItem
            },

        backgroundColor = MaterialTheme.colors.surface,
        elevation = 5.dp,
        shape = Shapes.medium

    ) {
        
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            
            Column(
                modifier = Modifier
                    .padding(start = 8.dp, top = 6.dp, bottom = 6.dp)
            ) {
                Text(text = weatherItem.time,  color = MaterialTheme.colors.onSecondary)
                Text(text = weatherItem.condition,  color = MaterialTheme.colors.onSurface)
            }
            
            Text(

                text = weatherItem.currentTemp
                    .ifEmpty {
                                weatherItem.maxTemp.toFloat().toInt().toString() + "°C/" +
                                weatherItem.minTemp.toFloat().toInt().toString() + "°C"
                    },
                color = MaterialTheme.colors.onSurface,
                style = TextStyle( fontSize = 20.sp)
            )

            AsyncImage(
                model = "https:${weatherItem.icon}",
                contentDescription = null,
                modifier = Modifier
                    .size(40.dp)
                    .padding(top = 5.dp, end = 8.dp)
            )
            
        }

    }

}
