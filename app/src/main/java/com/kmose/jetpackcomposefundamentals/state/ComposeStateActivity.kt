package com.kmose.jetpackcomposefundamentals.state

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
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
                    stateButton()
                }
            }
        }
    }
}

var count = 0

@Preview
@Composable
fun stateButton() {
    val context = LocalContext.current
    Button(onClick = {
        count += 1
        Toast.makeText(context, "Count is : $count", Toast.LENGTH_LONG).show()
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