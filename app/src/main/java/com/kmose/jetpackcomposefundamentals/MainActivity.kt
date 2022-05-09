package com.kmose.jetpackcomposefundamentals

import android.os.Bundle
import android.widget.Button
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.kmose.jetpackcomposefundamentals.ui.theme.JetpackComposeFundamentalsTheme
import androidx.compose.material.Button as ButtonDefaults

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BoxExample3()
        }
    }
}

@Composable
fun BoxExample1() {
    Box(
        modifier = Modifier
            .background(color = Color.Green)
            .size(180.dp, 300.dp)
    ) {
        Box (
            modifier = Modifier
                .background(color = Color.Yellow)
                .size(120.dp, 100.dp)
                .align(Alignment.TopEnd)
        ) {

        }

        Text(
            text = "Hello Box!",
            style = MaterialTheme.typography.h3,
            modifier = Modifier
                .background(color = Color.White)
                .size(90.dp, 50.dp)
                .align(Alignment.BottomCenter)
        )
    }
}

@Composable
fun BoxExample2() {
    Box (
        modifier = Modifier
            .background(color = Color.LightGray)
            .fillMaxSize()
    ){
        Text(
            text = "TopStart",
            style = MaterialTheme.typography.h5,
            color = Color.White,
            modifier = Modifier
                .background(color = Color.Black)
                .border(color = Color.Red, width = 4.dp)
                .padding(10.dp)
                .align(Alignment.TopStart)
        )
        Text(
            text = "TopCenter",
            style = MaterialTheme.typography.h5,
            color = Color.White,
            modifier = Modifier
                .background(color = Color.Black)
                .border(color = Color.Red, width = 4.dp)
                .padding(10.dp)
                .align(Alignment.TopCenter)
        )
        Text(
            text = "TopEnd",
            style = MaterialTheme.typography.h5,
            color = Color.White,
            modifier = Modifier
                .background(color = Color.Black)
                .border(color = Color.Red, width = 4.dp)
                .padding(10.dp)
                .align(Alignment.TopEnd)
        )
        Text(
            text = "CenterStart",
            style = MaterialTheme.typography.h5,
            color = Color.White,
            modifier = Modifier
                .background(color = Color.Black)
                .border(color = Color.Red, width = 4.dp)
                .padding(10.dp)
                .align(Alignment.CenterStart)
        )
        Text(
            text = "Center",
            style = MaterialTheme.typography.h5,
            color = Color.White,
            modifier = Modifier
                .background(color = Color.Black)
                .border(color = Color.Red, width = 4.dp)
                .padding(10.dp)
                .align(Alignment.Center)
        )
        Text(
            text = "CenterEnd",
            style = MaterialTheme.typography.h5,
            color = Color.White,
            modifier = Modifier
                .background(color = Color.Black)
                .border(color = Color.Red, width = 4.dp)
                .padding(10.dp)
                .align(Alignment.CenterEnd)
        )
        Text(
            text = "BtmStart",
            style = MaterialTheme.typography.h5,
            color = Color.White,
            modifier = Modifier
                .background(color = Color.Black)
                .border(color = Color.Red, width = 4.dp)
                .padding(10.dp)
                .align(Alignment.BottomStart)
        )
        Text(
            text = "BtmCenter",
            style = MaterialTheme.typography.h5,
            color = Color.White,
            modifier = Modifier
                .background(color = Color.Black)
                .border(color = Color.Red, width = 4.dp)
                .padding(10.dp)
                .align(Alignment.BottomCenter)
        )
        Text(
            text = "BtmEnd",
            style = MaterialTheme.typography.h5,
            color = Color.White,
            modifier = Modifier
                .background(color = Color.Black)
                .border(color = Color.Red, width = 4.dp)
                .padding(10.dp)
                .align(Alignment.BottomEnd)
        )
    }
}

@Composable
fun BoxExample3() {
    Box() {
        Image(
            painter = painterResource(id = R.drawable.sok),
            contentDescription = "속초"
        )
        Text(
            text = "속초 해수욕장",
            style = MaterialTheme.typography.h4,
            color = Color.White,
            modifier = Modifier.align(Alignment.BottomStart)
        )
        ButtonDefaults(
            onClick = {},
            colors = ButtonDefaults.textButtonColors(
                backgroundColor = Color.White,
                contentColor = Color.Gray
            ),
            modifier = Modifier
                .align(Alignment.TopEnd)
                .padding(10.dp)
                .border(5.dp, Color.DarkGray, RectangleShape)
        ) {
            Text("Back To List")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    JetpackComposeFundamentalsTheme {

    }
}