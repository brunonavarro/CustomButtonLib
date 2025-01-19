package com.apps.custombuttonlib.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material.Button
import androidx.compose.material.ButtonColors
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun CustomButton(
    name: String = "",
    color: ButtonColors = ButtonDefaults.buttonColors()
){
    Button(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight(),
        colors = color,
        onClick = { /*TODO*/ }
    ) {
        Text(text = name)
    }
}

@Composable
@Preview
fun Preview(){
    MaterialTheme {
        Column {
            CustomButton("Button", color = ButtonDefaults.buttonColors(
                backgroundColor = Color.Magenta
            ))
            CustomButton("Login")
        }
    }
}