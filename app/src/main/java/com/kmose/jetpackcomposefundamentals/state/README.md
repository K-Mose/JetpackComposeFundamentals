# Jetpack Compose - State 
앱 안에서 상태는 시간이 지남에 따라 변하는 모든 값 입니다. 

Jetpack Compose에서 상태의 변화에 따라 UI 업테이트를 해보겠습니다. <br>
아래 코드는 기본적인 counter 앱으로 버튼을 클릭 할 때 마다 `Int` 타입의 `count` 변수의 값을 증가시킵니다. 

<details id="dt1">
  <summary> <b>CODE</b> </summary>
  
```kotlin 
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
```  
</details>

![no_increasing_number](https://user-images.githubusercontent.com/55622345/173469732-43ebe380-1b02-4841-824e-6bf3e385d429.png)

하지만 코드를 실행시키면 `Toast`메시지에서는 값이 증가하지만 `Button`의 `Text`의 `$count` 값은 증가하지 않습니다. 

Compose에서 State 값의 변화를 UI에 업데이트 하기 위해서는 변화된 값이 `mutableStateOf`, `LiveData`, `Flow`, `RxJava`, `Coroutine` 등으로 관찰되어야 하고 
`@Composable`함수는 [**Recomposition**](https://developer.android.com/jetpack/compose/mental-model#recomposition) 되어야 합니다. 

## State in Jetpack Compose

Jetpack Compose 안에서의 상태변화를 감지하고 Recompose를 하기 위한 기본적인 방법은 변수를 [`mutableStateOf`](https://developer.android.com/reference/kotlin/androidx/compose/runtime/package-summary#mutableStateOf(kotlin.Any,androidx.compose.runtime.SnapshotMutationPolicy))로 설정하는 것 입니다. <br>
위 [코드](#user-content-dt1)의 `var count`를 `Int`에서 `mutableStateOf`로 변경 후 값을 사용하는 코드에서 `count.value`로 값을 사용 하면 됩니다. 
*mutableStateOf 안에는 변수에 할당할 초깃값을 넣어주면 됩니다.*
<details id="dt2">
  <summary> <b>CODE - MutableStateOf</b> </summary>
  
```kotlin 
var count = mutableStateOf(0)

@Preview
@Composable
fun stateButton() {
    val context = LocalContext.current
    Button(onClick = {
        count.value = count.value + 1
        Toast.makeText(context, "Count is : ${count.value}", Toast.LENGTH_LONG).show()
    },
        contentPadding = PaddingValues(16.dp),
        border = BorderStroke(3.dp, Color.Black),
        colors = ButtonDefaults.textButtonColors(
            backgroundColor = Color.DarkGray,
            contentColor = Color.White
        )
    ) {
        Text(text = "Count is : ${count.value}",
            style = MaterialTheme.typography.h3,
            modifier = Modifier.padding(5.dp)
        )

    }
}
```  
</details>

![increasing_value](https://user-images.githubusercontent.com/55622345/173471670-dc46849d-3a4c-4416-9327-46422287d347.png)


## Remember 
`count` 변수가 전역으로 사용 되고 있는데, Composable 함수들이 독립적으로 여러 파일에 존재하면서 `count` 변수를 사용하게 된다면 `Unexpected Error`가 발생하게 됩니다. 그렇기 때문에 `count`를 Composable 함수 내에 지역변수로 변경하겠습니다. 

![image](https://user-images.githubusercontent.com/55622345/173473002-ff43f570-b108-4849-92dc-30a813732038.png) <br>
단순히 `mutableStateOf`를 그대로 지역변수로 추가하게 되면 오류가 발생하게 됩니다. 
이것은 Composable 함수가 Recomposition 될 때마다 새롭게 호출되므로 지역변수인 `count`값도 같이 새로 호출되면서 값이 초기화되기 때문입니다. 

그래서 [`Remember`](https://developer.android.com/reference/kotlin/androidx/compose/runtime/package-summary#remember(kotlin.Function0))로 변수를 호출하게 되면 현재 상태값을 기억(보존)하여 Recomposable이 일어날 때에도 같은 값을 사용할 수 있게 합니다. 

```kotlin 
@Preview
@Composable
fun stateButton() {
    val context = LocalContext.current
    val count = remember {mutableStateOf(0)}
    ……
 }   
```

또안 `by` Delegate를 사용해서 선언할 수 있습니다. Delegate로 선언하기 위해서는 아래의 코드를 import 해야 합니다. 
```kotlin
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
```
```koltin
var count by remember {mutableStateOf(0)}
……
Text(text = "Count is : ${count.value}", -> Text(text = "Count is : $count",
```
delegate로 선언하면 `getValue`와 `setValue`를 사용하게 되어 변수로 직접 접근이 가능합니다. 




*three way to declare mutablestate object* <br>
[![image](https://user-images.githubusercontent.com/55622345/173713529-662cbe12-fe63-4101-88d4-150210cbc806.png)
](https://developer.android.com/jetpack/compose/state#state-in-composables)
