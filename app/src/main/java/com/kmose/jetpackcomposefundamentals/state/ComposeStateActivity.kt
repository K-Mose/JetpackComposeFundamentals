package com.kmose.jetpackcomposefundamentals.state

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
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
    val viewModel = viewModel<StateViewModel>()
//    var count by remember {mutableStateOf(0)}
    // Configuration 변경으로 인해(rotatin, language, keyboard 등등) Activity가 재생성되면 remeber만으로는 상태가 저장이 되지 않는다.
//    var count by rememberSaveable {mutableStateOf(0)}
    val count = viewModel.count
    stateButton(count) {
        viewModel.increaseCount()
    }
}

@Composable
// Unidirectional Data Flow #https://developer.android.com/jetpack/compose/architecture#udf
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