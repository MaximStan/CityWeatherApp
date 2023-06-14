package com.example.weatherapp.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.weatherapp.R
import com.example.weatherapp.api_data.getWeatherByHours
import com.example.weatherapp.ui.WeatherMainList
import com.example.weatherapp.models.WeatherModel
import com.example.weatherapp.ui.theme.Shapes
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.pagerTabIndicatorOffset
import com.google.accompanist.pager.rememberPagerState
import kotlinx.coroutines.launch

@Composable
fun WeatherMainCard(
    currentDay: MutableState<WeatherModel>,
    onClickRefresh: () -> Unit,
    onClickSearch: () -> Unit

) {

    Column(
        modifier = Modifier
            .background(color = MaterialTheme.colors.background)

    ) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(9.dp),
            backgroundColor = MaterialTheme.colors.surface,
            elevation = 5.dp,
            shape = Shapes.medium

        ) {

            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween

                ) {
                    Text(
                        modifier = Modifier.padding(top = 10.dp, start = 10.dp),
                        text = currentDay.value.time,
                        style = TextStyle(fontSize = 15.sp),
                        color = MaterialTheme.colors.onSurface
                    )
                    AsyncImage(
                        model = "https:${currentDay.value.icon}",
                        contentDescription = null,
                        modifier = Modifier
                            .size(40.dp)
                            .padding(top = 5.dp, end = 8.dp)
                    )

                }

                Text(
                    text = currentDay.value.city,
                    style = TextStyle(fontSize = 25.sp),
                    color = MaterialTheme.colors.onSurface
                )

                Text(
                    text = if(currentDay.value.currentTemp.isNotEmpty()){
                        "${currentDay.value.currentTemp.toFloat().toInt()}°C"
                    } else
                    "${currentDay.value.maxTemp.toFloat().toInt()}°C/" +
                            "${currentDay.value.minTemp.toFloat().toInt()}°C" ,

                    style = TextStyle(fontSize = 55.sp),
                    color = MaterialTheme.colors.onSurface
                )

                Text(
                    modifier = Modifier.padding(top = 8.dp),
                    text = currentDay.value.condition,
                    style = TextStyle(fontSize = 15.sp),
                    color = MaterialTheme.colors.onSurface
                )

                Row() {
                    Image(painter = painterResource(id = R.drawable.ic_water_drop_24),
                        contentDescription = null,
                        modifier = Modifier.padding(vertical = 8.dp)
                    )

                    Text(text = currentDay.value.avgHumidity + " %",
                        style = TextStyle(fontSize = 15.sp),
                        color = MaterialTheme.colors.onSurface,
                        modifier = Modifier.padding(vertical = 8.dp)
                    )

                    Spacer(modifier = Modifier.padding(horizontal = 10.dp))

                    Image(painter = painterResource(id = R.drawable.wind_svgrepo_com),
                        contentDescription = null,
                        modifier = Modifier.padding(vertical = 8.dp).size(23.dp)
                    )

                    Spacer(modifier = Modifier.padding(horizontal = 3.dp))

                    Text(text = currentDay.value.wind + " km/h",
                        style = TextStyle(fontSize = 15.sp),
                        color = MaterialTheme.colors.onSurface,
                        modifier = Modifier.padding(vertical = 6.dp,)
                    )
                }

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {

                    IconButton(
                        onClick = {
                           onClickSearch.invoke()
                        }
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_search_24),
                            contentDescription = "search icon",
                            tint = MaterialTheme.colors.onSurface
                        )
                    }

                    Text(
                        modifier = Modifier.padding(top = 5.dp),
                        text = "${currentDay.value.maxTemp.toFloat().toInt()}°C" +
                                "/${currentDay.value.minTemp.toFloat().toInt()}°C",
                        style = TextStyle(fontSize = 16.sp),
                        color = MaterialTheme.colors.onSurface
                    )

                    IconButton(
                        onClick = {
                            onClickRefresh.invoke()
                        }
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_refresh_24),
                            contentDescription = "refresh icon",
                            tint = MaterialTheme.colors.onSurface
                        )
                    }

                }
            }
        }


    }

}
