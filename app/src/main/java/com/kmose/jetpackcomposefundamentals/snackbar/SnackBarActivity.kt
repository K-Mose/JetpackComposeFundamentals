package com.kmose.jetpackcomposefundamentals.snackbar

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.kmose.jetpackcomposefundamentals.snackbar.ui.theme.JetpackComposeFundamentalsTheme
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

// https://developer.android.com/jetpack/compose/layouts/material#snackbar
class SnackBarActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            JetpackComposeFundamentalsTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    DisplaySnackBar()
                }
            }
        }
    }
}

@Composable
fun DisplaySnackBar() {
    // SnackBar
    // 1. A Scaffold State - layout which implements the basic material design layout structure
    // 2. A Coroutine Scope

    val scaffoldState: ScaffoldState = rememberScaffoldState()
    val coroutineScope: CoroutineScope = rememberCoroutineScope()

    Scaffold(scaffoldState = scaffoldState) {
        Button(onClick = {
            coroutineScope.launch {
                val snackBarResult = scaffoldState.snackbarHostState.showSnackbar(
                    message = "This is the message",
                    actionLabel = "Undo",
                    duration = SnackbarDuration.Long
                    // duration = SnackbarDuration.Indefinite // 액션 레이블 클릭 할 때 까지 떠있음
                )
                when (snackBarResult) {
                    SnackbarResult.ActionPerformed -> Log.i("MYTAG::", "ActionPerformed")
                    SnackbarResult.Dismissed -> Log.i("MYTAG::", "Dismissed")
                }
            }
        }) {
            Text(text = "Display SnackBar")
        }
    }
}

@Preview
@Composable
fun SnackBarPreview() {
    DisplaySnackBar()
}