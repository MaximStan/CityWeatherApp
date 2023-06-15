package com.example.weatherapp.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.R
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.weatherapp.ui.theme.DeepBlue

@Composable
fun DialogSearch(
    showSearchDialog: MutableState<Boolean>,
    onSubmit: (String) -> Unit
    ){

    var city by remember { mutableStateOf("") }

    AlertDialog(
        onDismissRequest = { showSearchDialog.value = false },

        confirmButton = {

            TextButton(onClick = {
                onSubmit(city)
                showSearchDialog.value = false
            }) {
                Text(text = "OK", color = MaterialTheme.colors.onSecondary,)
            }
        },

        dismissButton = {
            TextButton(onClick = { showSearchDialog.value = false }) {
                Text(text = "Cancel", color = MaterialTheme.colors.onSecondary,)
            }
        },

        title = {
            Column(modifier = Modifier.fillMaxWidth()) {

                Text(text = "Enter city name",
                    fontWeight = FontWeight.Bold,
                fontSize = 20.sp
                    )

                OutlinedTextField(
                    value = city,
                    onValueChange = { city = it },
                    label = {
                        Text(
                             text = "City",
                             color = MaterialTheme.colors.onSurface
                        )
                    },
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Text,
                    ),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 40.dp, bottom = 20.dp),
                    colors = TextFieldDefaults.outlinedTextFieldColors(
                        focusedBorderColor = MaterialTheme.colors.onSecondary,
                        unfocusedBorderColor = MaterialTheme.colors.onSurface,
                        cursorColor = MaterialTheme.colors.onSecondary,
                        textColor = MaterialTheme.colors.onSurface,
                    ),
                    singleLine = true,
                )
            }
           
        }
    )

}
