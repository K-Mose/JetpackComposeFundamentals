package com.kmose.jetpackcomposefundamentals

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.kmose.jetpackcomposefundamentals.ui.theme.JetpackComposeFundamentalsTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Row (
                modifier = Modifier
                    .background(color = Color.LightGray)
                    .fillMaxSize()
                    .fillMaxHeight(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Greeting("Android")
                Greeting("Kotlin")
                Greeting("Mose")
            }
        }
    }
}

// Package level function - allow us to reuse them effectively.
@Composable
fun Greeting(name: String) {
    // Jetpack Compose Function - like UI Widget in xml
    Text(
        text = name,
        fontSize = 32.sp,
        fontWeight = FontWeight.Bold,
        color = Color.Red,
        textAlign = TextAlign.Center,
        modifier = Modifier
            .background(color = Color.Yellow)
            .padding(10.dp)
            .border(2.dp, color = Color.Black)
    )
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    JetpackComposeFundamentalsTheme {
        Greeting("Android")
    }
}