# JetpackComposeFundamentals
[Jetpack Compose](https://developer.android.com/jetpack/compose/why-adopt)는 navtie UI를 쉽고 빠르게 작성할 수 있도록 도와주는 클래스입니다. <br>
jetpack compose는 기존에 XML에서 작성하던 UI를 Kotlin 코드로 작성함으로 아래와 같은 이점을 가져옵니다. 
- Less code
- Intuitive
- Accelerate develpoment
- Powerful

## Basic 
```kotlin
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            JetpackComposeFundamentalsTheme {
                // A surface container using the 'background' color from the theme
                Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colors.background) {
                    Greeting("Android")
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    JetpackComposeFundamentalsTheme {
        Greeting("Android")
    }
}
```
Jetpack Compose에서는 XML 대신 Kotlin 코드로 UI를 작성합니다. 위에서 `<TextVivew/>`를 사용하는 것을 `Text()`composable function으로 대신 사용합니다.<br>
`Greeting`과 같은 새로운 Composable function을 정의함으로 데이터를 받고 UI로 나타내는 함수를 만들 수 있습니다. 


`@Preview`어노테이션을 사용함으로 화면 내에서 코드 디자인을 미리볼 수 있습니다. 

![image](https://user-images.githubusercontent.com/55622345/167359133-b105299f-166e-48a4-b61a-2f53d097d748.png)

여기서는 `DefaultPreview`함수 속 `Geeting`메서드의 인자 값을 바꾸면 오른쪽 design 화면의 텍스트가 바로 바뀌게 됩니다. 

## Modifier And Parameters
일반적인 `<TextView/>`xml은 아래와 같습니다. 
```xml
<TextView
      android:layout_width="wrap_content"    
      android:layout_height="wrap_content"
      android:text="Hello World!"
      android:testSize="20sp"
      ……
/>
```
Composable function에서도 xml의 속성을 인자 값으로 넘겨줌으로써 해당 값들을 추가할 수 있습니다. 
```kotlin
    Text(
        text = "Hello $name!",
        fontSize = 32.sp,
        fontWeight = FontWeight.Bold,
        color = Color.Red,
        textAlign = TextAlign.Center
    )
```

[Modifier](https://developer.android.com/jetpack/compose/tutorial#tut-step-5)는 size, layout등으로  composable function가 어떻게 보여질것인지 정의할 수 있고, clickable, zoomable, draggable, scrollable 등의 UI의 상호작용을 추가할 수 있습니다. 

Modifier를 추가할 떄는 Modifier에 추가되는 순서에 따라서 적용되는 UI가 달라집니다. 
예를들어 `border`를 추가한 후 `padding`을 추가한 것과 `padding`을 추가한 후 `border`를 추가한 UI는 다릅니다. 
전자는 `border`안에 `padding`이 들어가지만 후자는 `padding`이 적용된 후 `border`가 적용됩니다. 

![image](https://user-images.githubusercontent.com/55622345/167362632-f6493e5f-ba8f-4b5a-8278-3cc65d3b1975.png)

![image](https://user-images.githubusercontent.com/55622345/167362777-e94499f1-5f87-4b4d-98e1-6483df9ab4b4.png)

```kotlin
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
```
`fillMaxWidth`와 `fillMaxHeight`로 화면 전체를 덮을 수도 있고, 인자 값을 `float`값으로 넘겨줌으로 화면에서 차지할 비율을 설정 할 수 있습니다. 


## Ref.
https://developer.android.com/jetpack/compose <br>
https://developer.android.com/jetpack/compose/tutorial <br>
https://developer.android.com/jetpack/compose/mental-model <br>
