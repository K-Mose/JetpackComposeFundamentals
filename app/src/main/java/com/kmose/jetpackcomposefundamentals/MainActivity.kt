package com.kmose.jetpackcomposefundamentals

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CutCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material.icons.materialIcon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.kmose.jetpackcomposefundamentals.ui.theme.JetpackComposeFundamentalsTheme
//import androidx.compose.material.Button as ButtonDefaults

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Column(
                verticalArrangement = Arrangement.SpaceEvenly,
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.fillMaxSize()
            ) {
                ButtonDemo()
            }
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
        Button(
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

@Composable
fun ButtonDemo() {
    val context = LocalContext.current


    Button(onClick = {
        Toast.makeText(context, "Clicked on Button", Toast.LENGTH_SHORT).show()
    }) {
        Text("Add To Cart")
    }

    Button(onClick = {
        Toast.makeText(context, "Clicked on Button2", Toast.LENGTH_SHORT).show()
                     },
        enabled = false
    ) {
        Text("Add To Cart")
    }

    TextButton(onClick = { // button without a background shape or border
        Toast.makeText(context, "Clicked on TextButton", Toast.LENGTH_SHORT).show()
    }) {
        Text("Add To Cart")
    }

    OutlinedButton(onClick = { //
        Toast.makeText(context, "Clicked on OutlineButton", Toast.LENGTH_SHORT).show()
    }) {
        Text("Add To Cart")
    }

    IconButton(onClick = { // like clickable icon
        Toast.makeText(context, "Clicked on IconButton", Toast.LENGTH_SHORT).show()
    }) {
        // 기본적으로 painter로 이미지나 백터이미지를 넣을 수 있다
        Icon( imageVector = Icons.Filled.Refresh,
            contentDescription = "Refresh Button",
            tint = Color.Green,
            modifier = Modifier.size(80.dp)
        )
    }

    Button(onClick = {
        Toast.makeText(context, "Clicked on Button", Toast.LENGTH_SHORT).show()
    },
        contentPadding = PaddingValues(16.dp),
        border = BorderStroke(10.dp, Color.Black),
        colors = ButtonDefaults.textButtonColors(
            backgroundColor = Color.DarkGray,
            contentColor =  Color.White
        )
    ) {
        Text("Add To Cart",
            style = MaterialTheme.typography.h3,
            modifier = Modifier.padding(5.dp)
        )
    }

    Button(onClick = {
        Toast.makeText(context, "Clicked on Button", Toast.LENGTH_SHORT).show()
    },
        shape = CutCornerShape(10.dp),
        contentPadding = PaddingValues(16.dp),
        border = BorderStroke(10.dp, Color.Black),
        colors = ButtonDefaults.textButtonColors(
            backgroundColor = Color.DarkGray,
            contentColor =  Color.White
        )
    ) {
        Text("Add To Cart",
            style = MaterialTheme.typography.h3,
            modifier = Modifier.padding(5.dp)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    JetpackComposeFundamentalsTheme {

    }
}