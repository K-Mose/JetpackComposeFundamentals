package com.kmose.jetpackcomposefundamentals.recyclerview

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.kmose.jetpackcomposefundamentals.recyclerview.ui.theme.JetpackComposeFundamentalsTheme
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll

class RecyclerViewActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
//            ScrollableColumnDemo()
            LazyColumnDemo()
        }
    }
}

/*
Scroll 조건
1. Get an instacne of ScrollState
2. Add VerticalScroll modifier
 */
@Composable
fun ScrollableColumnDemo() {
    val scrollState = rememberScrollState()
    Column (
        modifier = Modifier.verticalScroll(scrollState)
    ) {
        for (i in 1 .. 100) {
            Text(text = "Card No.$i",
                style = MaterialTheme.typography.h3,
                modifier = Modifier.padding(10.dp))
            Divider(color = Color.Black, thickness = 5.dp )
        }
    }
}

@Composable
fun LazyColumnDemo() {
        LazyColumn {
        items(100) { // instead of using a for loop
            Text(text = "Card No.$it",
                style = MaterialTheme.typography.h3,
                modifier = Modifier.padding(10.dp))
            Divider(color = Color.Black, thickness = 5.dp )
        }
    }
}
