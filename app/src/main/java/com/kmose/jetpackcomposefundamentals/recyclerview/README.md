# Jetpack Compose - Recycler View
기존의 XML로 `RecyclerView`를 생성하기 위해서는 item의 xml과 recyclerView가 들어갈 activity의 xml이 필요했습니다. 그리고 `RecyclerViewAdpater`를 생성하고 `ViewHolder`를 추가하며 아주 복잡하게 생성되었습니다. 

Jetpack Compose를 이용하여 Recycler View의 생성을 간단하게 작성해보겠습니다. 

## Basic List
`Column`으로 100개의 `Text`를 생성해보겠습니다.
```kotlin 
class RecyclerViewActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ScrollableColumnDemo()
        }
    }
}

@Composable
fun ScrollableColumnDemo() {
    Column {
        for (i in 1 .. 100) {
            Text(text = "Card No.$i",
                style = MaterialTheme.typography.h3,
                modifier = Modifier.padding(10.dp))
            Divider(color = Color.Black, thickness = 5.dp )
        }
    }
}
```

이렇게 생성하면 `Text`는 100개가 생성이 되긴 하지만 스크롤이 되진 않습니다. 
Jetpack Compose의 Layout에서 스크롤이 되기 위해선 아래의 조건이 필요합니다. 
- ScrollState
- Vertical/Horizontal Scroll modifier
`ScrollState`는 변경되는 스크롤의 위치와 현재 스크롤의 상태를 저장하고, `[Vertical|Horizontal]Scroll` modifier는 Layout에 스크롤을 추가시킵니다. 

```kotlin
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
……
@Composable
fun ScrollableColumnDemo() {
    val scrollState = rememberScrollState()
    Column (
        modifier = Modifier.verticalScroll(scrollState)
    ) {
      ……
    }
}
```
이제 생성된 `Column` Layout에서 스크롤리 가능해졌습니다. 


## Ref. 
https://developer.android.com/jetpack/compose/gestures#scrolling <br>
