package com.kmose.jetpackcomposefundamentals.state

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.kmose.jetpackcomposefundamentals.state.ui.theme.JetpackComposeFundamentalsTheme

class ComposeStateActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            JetpackComposeFundamentalsTheme {
                Column(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    stateHoisting()
                }
            }
        }
    }
}

// https://youtu.be/mymWGMy9pYI?t=300
@Composable
fun stateHoisting() {
    var count by remember {mutableStateOf(0)}
    stateButton(count) {
        count = it + 1
    }
}

@Composable
// Unidirectional Data Flow
// count -> state 전달 top to bottom
// clickListener -> 클릭 이벤트 전달 bottom to top
fun stateButton(count: Int, buttonClickListener: (Int) -> Unit) {
    Button(onClick = {
        buttonClickListener(count)
    },
        contentPadding = PaddingValues(16.dp),
        border = BorderStroke(3.dp, Color.Black),
        colors = ButtonDefaults.textButtonColors(
            backgroundColor = Color.DarkGray,
            contentColor = Color.White
        )
    ) {
        Text(text = "Count is : $count",
            style = MaterialTheme.typography.h3,
            modifier = Modifier.padding(5.dp)
        )

    }
}