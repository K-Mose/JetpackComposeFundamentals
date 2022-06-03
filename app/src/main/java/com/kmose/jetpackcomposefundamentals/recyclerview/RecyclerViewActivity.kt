package com.kmose.jetpackcomposefundamentals.recyclerview

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.kmose.jetpackcomposefundamentals.recyclerview.ui.theme.JetpackComposeFundamentalsTheme
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.focusModifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import com.kmose.jetpackcomposefundamentals.recyclerview.compose.TvShowListItem
import com.kmose.jetpackcomposefundamentals.recyclerview.data.TvShowList
import com.kmose.jetpackcomposefundamentals.recyclerview.model.TvShow
import kotlinx.coroutines.selects.select

class RecyclerViewActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
//            ScrollableColumnDemo()
//            LazyColumnDemo()
//            LazyColumnDemo2 {
//                Toast.makeText(this, it, Toast.LENGTH_SHORT).show()
//            }
            DisplayingTvShows {
                Toast.makeText(this, it.name, Toast.LENGTH_SHORT).show()
            }
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

/*
Item Click Listener
1. Add lambda expression as a parameter to the composable.
2. Use clicakble modifier to pass the selected item.
 */
@Composable
fun LazyColumnDemo2(selectedItem: (String)->Unit) {
        LazyColumn {
        items(100) { // instead of using a for loop
            Text(text = "Card No.$it",
                style = MaterialTheme.typography.h3,
                modifier = Modifier
                    .padding(10.dp)
                    .clickable {
                        selectedItem("$it Selected")
                    }
            )
            Divider(color = Color.Black, thickness = 5.dp )
        }
    }
}


// Displaying TvShow List
@Composable
fun DisplayingTvShows(selectedItem: (TvShow) -> Unit) {
    val tvShows = remember {
        TvShowList.tvShows
    }
    LazyColumn(contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp)) {
        items(
            items = tvShows,
            itemContent = {
                TvShowListItem(tvShow = it, selectedItem)
            }
        )
    }
}


