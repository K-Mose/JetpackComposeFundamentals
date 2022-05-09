package com.kmose.jetpackcomposefundamentals

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
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
            Greeting("Android \n\t 여기는 실제 화면")
        }
    }
}

// Package level function - allow us to reuse them effectively.
@Composable
fun Greeting(name: String) {
    // Jetpack Compose Function - like UI Widget in xml
    Text(
        text = "Hello $name!",
        fontSize = 32.sp,
        fontWeight = FontWeight.Bold,
        color = Color.Red,
        textAlign = TextAlign.Center,
        modifier = Modifier
            .background(color = Color.Yellow)
            .padding(10.dp)
            .border(2.dp, color = Color.Green)
            .fillMaxWidth(0.5f)
            .fillMaxHeight(0.3f)
    )
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    JetpackComposeFundamentalsTheme {
        Greeting("Android")
    }
}