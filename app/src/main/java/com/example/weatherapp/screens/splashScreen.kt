package com.example.weatherapp.screens

import android.view.animation.OvershootInterpolator
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.weatherapp.R
import kotlinx.coroutines.delay

@Composable
fun WeatherSplashScreen(navController: NavController){

    val scaleImg = remember{
        Animatable(2f)

    }
    LaunchedEffect(key1 = true){
        scaleImg.animateTo(
            targetValue = 5f,
            animationSpec = tween(
                durationMillis = 500,
                easing = {
                    OvershootInterpolator(2f).getInterpolation(it)
                }
            )
        )
        delay(1000)
        navController.navigate("weather_main_screen")
    }

    Box(contentAlignment = Alignment.Center,
        modifier = Modifier
            .fillMaxSize()
            .background(color = MaterialTheme.colors.background)
    ) {

        Image(painter = painterResource(id = R.drawable.weather_svgrepo_com__sun_cloud_),
            contentDescription = "App logo",
            modifier = Modifier.scale(scaleImg.value)
        )

        Text(text = "City Weather",
            modifier = Modifier.padding(top = 200.dp),
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colors.onSurface,
            fontSize = 15.sp
        )

    }

}